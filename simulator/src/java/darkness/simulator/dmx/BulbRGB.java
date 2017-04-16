package darkness.simulator.dmx;

import com.jme3.scene.Node;
import darkness.simulator.graphics.Bulb;

import java.awt.Color;
import java.util.List;

public class BulbRGB {
    private final Channel channelRed;
    private final Channel channelGreen;
    private final Channel channelBlue;
    private final Bulb bulb;
    private final ChannelController controller = new ChannelController();

    BulbRGB(Channel channelRed, Channel channelGreen, Channel channelBlue,
            float positionX, float positionY, List<Float> perimeterX, List<Float> perimeterY, Node parentNode) {
        this.channelRed = channelRed;
        this.channelRed.setOwner(controller);
        this.channelGreen = channelGreen;
        this.channelGreen.setOwner(controller);
        this.channelBlue = channelBlue;
        this.channelBlue.setOwner(controller);
        this.bulb = new Bulb(positionX, positionY, perimeterX, perimeterY, parentNode, toString());
    }

    public Channel getChannelRed() {
        return channelRed;
    }

    public Channel getChannelGreen() {
        return channelGreen;
    }

    public Channel getChannelBlue() {
        return channelBlue;
    }

    public void set(int red, int green, int blue) {
        channelRed.setValue(red);
        channelGreen.setValue(green);
        channelBlue.setValue(blue);
    }

    public void update() {
        bulb.update(getRed(), getGreen(), getBlue());
    }

    public void set(Color color) {
        set(color.getRed(), color.getGreen(), color.getBlue());
    }

    public void set(String hexColor) {
        set(Color.decode(hexColor));
    }

    /**
     * Set a HSB color to this bulb
     * @param hue The floor of this number is subtracted from it to create a fraction between 0 and 1. This fractional number is then multiplied by 360 to produce the hue angle in the HSB color model.
     * @param saturation In the range 0.0..1.0
     * @param brightness In the range 0.0..1.0
     */
    public void setHSB(float hue, float saturation, float brightness) {
        set(Color.getHSBColor(hue, saturation, brightness));
    }

    public Color getColor() {
        return new Color(getRed(), getGreen(), getBlue());
    }

    public int getRed() {
        return channelRed.getValue();
    }

    public int getGreen() {
        return channelGreen.getValue();
    }

    public int getBlue() {
        return channelBlue.getValue();
    }

    @Override
    public String toString() {
        return "Bulb{R:"+getChannelRed()+",G:"+getChannelGreen()+"B:"+getChannelBlue()+"}";
    }

    // TODO wrong naming...
    private class ChannelController implements ChannelOwner {

        @Override
        public void onChannelUpdated(int newValue) {
            update();
        }
    }
}

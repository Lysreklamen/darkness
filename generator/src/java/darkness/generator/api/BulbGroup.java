package darkness.generator.api;

import java.awt.Color;
import java.util.*;
import java.util.function.Consumer;
import java.lang.IllegalArgumentException;

/**
 * A group of one or more bulbs, that can mostly be treated as one "big" bulb through the {@link BulbSet} interface.
 */
public class BulbGroup implements BulbSet, Iterable<BulbRGB> {
	private final List<BulbRGB> bulbs;
	public final int numBulbs;

	/**
	 * Create a bulb group that consists of the given bulbs.
	 */
	public BulbGroup(BulbRGB... bulbs) {
		if (bulbs == null || bulbs.length == 0) {
			throw new IllegalArgumentException("bulbs must be non-null and contain elements");
		}
		for (int i = 0; i < bulbs.length; i++) {
			if (bulbs[i] == null) {
				throw new IllegalArgumentException("The bulb at index " + i + " is null");
			}
		}
		this.bulbs = Arrays.asList(bulbs);
		this.numBulbs = bulbs.length;
	}

	/**
	 * Get a single bulb
	 * @param idx Bulb index
	 * @return
	 */
	public BulbRGB getBulb(int idx) {
		return bulbs.get(idx);
	}

	/**
	 * Set all bulbs in the group to the given RGB color.
	 */
	@Override
	public void set(int red, int green, int blue, ScriptBase setter) {
		for (BulbRGB bulb : bulbs) {
			bulb.set(red, green, blue, setter);
		}
	}

	/**
	 * Set all bulbs in the group to the given RGB color.
	 */
	@Override
	public void set(Color color, ScriptBase setter) {
		for (BulbRGB bulb : bulbs) {
			bulb.set(color, setter);
		}
	}

	/**
	 * Set all bulbs in the group to the given HSB color.
	 */
	@Override
	public void setHSB(float hue, float saturation, float brightness, ScriptBase setter) {
		for (BulbRGB bulb : bulbs) {
			bulb.setHSB(hue, saturation, brightness, setter);
		}
	}

	@Override
	public void relinquish(ScriptBase setter) {
		for (BulbRGB bulb : bulbs) {
			bulb.relinquish(setter);
		}
	}

	/**
	 * @return The red component of the first bulb in the group.
	 */
	@Override
	public int getRed() {
		return bulbs.get(0).getRed();
	}

	/**
	 * @return The green component of the first bulb in the group.
	 */
	@Override
	public int getGreen() {
		return bulbs.get(0).getGreen();
	}

	/**
	 * @return The blue component of the first bulb in the group.
	 */
	@Override
	public int getBlue() {
		return bulbs.get(0).getBlue();
	}

	/**
	 * @return A string describing the channels and current values of the bulbs in this group.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("BulbSet{");
		for (BulbRGB bulb : bulbs) {
			sb.append(bulb);
			sb.append(',');
		}
		sb.setCharAt(sb.length() - 1, '}'); // Overwrite trailing comma
		return sb.toString();
	}

	/**
	 * @return The RGB color of the first bulb in the group.
	 */
	@Override
	public Color getColor() {
		return bulbs.get(0).getColor();
	}

	@Override
	public float[] getPosition() {
		float[] averagePosition = {0.0f, 0.0f, 0.0f};
		int count = 0;
		for(BulbRGB bulb: bulbs) {
			float[] pos = bulb.getPosition();
			averagePosition[0] += pos[0];
			averagePosition[1] += pos[1];
			averagePosition[2] += pos[2];
			count++;
		}

		averagePosition[0] /= (float)count;
		averagePosition[1] /= (float)count;
		averagePosition[2] /= (float)count;
		return averagePosition;

	}

	@Override
	public Iterator<BulbRGB> iterator() {
		return bulbs.iterator();
	}

	@Override
	public void forEach(Consumer<? super BulbRGB> action) {
		bulbs.forEach(action);
	}

	@Override
	public Spliterator<BulbRGB> spliterator() {
		return bulbs.spliterator();
	}

	public List<BulbRGB> getAllBulbs() {
		return bulbs;
	}
}

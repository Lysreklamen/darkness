package darkness.generator.scripts.uka17;

import darkness.generator.api.BulbGroup;

public class SequentialTest extends BaseScript {
	@Override
	public void run() {
		super.run();
        var SKIP_FRAMES = 100;
        for (bulbId in 0..BulbManager.allBulbs.size) {
            val bulb = bulb(bulbId);
            set(bulb, 255, 0, 0);
            
            set(bulb, 0, 255, 0);
            skip(SKIP_FRAMES);

            set(bulb, 0, 0, 255);
            skip(SKIP_FRAMES);
        }    
	}
}

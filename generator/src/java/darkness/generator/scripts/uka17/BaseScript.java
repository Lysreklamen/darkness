package darkness.generator.scripts.uka17;

import darkness.generator.api.BulbGroup;
import darkness.generator.api.ScriptBase;

public class BaseScript extends ScriptBase {
	protected BulbGroup A;

	@Override
	public void run() {
		A = group(0, 1, 2, 3, 4, 5, 6);
	}
}

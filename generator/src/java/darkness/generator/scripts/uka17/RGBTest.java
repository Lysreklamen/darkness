package darkness.generator.scripts.uka17;

public class RGBTest extends BaseScript {
	@Override
	public void run() {
		super.run();
		set(A, 255, 0, 0);
		skip(20);
		set(A, 0, 255, 0);
		skip(20);
		set(A, 0, 0, 255);
		skip(20);
	}
}

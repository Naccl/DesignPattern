/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class SwitcherAdapter implements IStandardSwitchable{
	private Light light;

	public SwitcherAdapter(Light light) {
		this.light = light;
	}

	@Override
	public void connectElectricCurrent() {
		System.out.println("connectElectricCurrent...");
	}
}

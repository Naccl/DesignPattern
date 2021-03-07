/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class Test {
	public static void main(String[] args) {
		Light light = new Light();
		SwitcherAdapter switcherAdapter = new SwitcherAdapter(light);
		switcherAdapter.connectElectricCurrent();
		light.turnOn();
		light.turnOff();
	}
}

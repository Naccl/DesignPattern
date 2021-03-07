/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-20
 */
public class Test {
	public static void main(String[] args) {
		TV tv = new TV();
		WashingMachine washingMachine = new WashingMachine();
		ThreeElectricOutlet threeElectricOutlet = washingMachine;
		threeElectricOutlet.power();

		ElectricOutletAdapter electricOutletAdapter = new ElectricOutletAdapter(tv);
		electricOutletAdapter.power();
	}
}

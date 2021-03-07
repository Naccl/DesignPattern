public class ElectricOutletAdapter implements ThreeElectricOutlet {

	private TwoElectricOutlet electricOutlet;

	public ElectricOutletAdapter(TwoElectricOutlet electricOutlet) {
		this.electricOutlet = electricOutlet;
	}

	public void power() {
		electricOutlet.power();
	}

}

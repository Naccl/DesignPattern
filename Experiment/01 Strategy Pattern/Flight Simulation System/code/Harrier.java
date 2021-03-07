public class Harrier extends Plane {

	public Harrier() {
		super.flightCharacteristics = new SuperSonicFly();
		super.takeoffCharacteristics = new VerticalTakeOff();
	}

}

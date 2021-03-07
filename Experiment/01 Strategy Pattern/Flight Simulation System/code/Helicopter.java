public class Helicopter extends Plane {

	public Helicopter() {
		super.flightCharacteristics = new SubSonicFly();
		super.takeoffCharacteristics = new VerticalTakeOff();
	}

}

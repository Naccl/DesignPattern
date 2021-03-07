public class AirPlane extends Plane {

	public AirPlane() {
		super.flightCharacteristics = new SubSonicFly();
		super.takeoffCharacteristics = new LongDistanceTakeOff();
	}

}

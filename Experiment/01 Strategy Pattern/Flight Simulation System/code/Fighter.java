public class Fighter extends Plane {

	public Fighter() {
		super.flightCharacteristics = new SuperSonicFly();
		super.takeoffCharacteristics = new LongDistanceTakeOff();
	}

}

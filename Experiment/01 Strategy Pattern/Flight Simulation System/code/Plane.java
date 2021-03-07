public class Plane {

	public TakeoffCharacteristics takeoffCharacteristics;

	public FlightCharacteristics flightCharacteristics;

	public void fly() {
		flightCharacteristics.fly();
	}

	public void takeOff() {
		takeoffCharacteristics.takeOff();
	}

}

public class Test {
	public static void main(String[] args) {
		Plane plane = new Helicopter();
		System.out.println("Helicopter:");
		plane.fly();
		plane.takeOff();

		plane = new AirPlane();
		System.out.println("\nAirPlane:");
		plane.fly();
		plane.takeOff();

		plane = new Fighter();
		System.out.println("\nFighter:");
		plane.fly();
		plane.takeOff();

		plane = new Harrier();
		System.out.println("\nHarrier:");
		plane.fly();
		plane.takeOff();
	}
}

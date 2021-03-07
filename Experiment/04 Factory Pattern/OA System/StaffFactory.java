public class StaffFactory implements UserFactory {

	public Users create() {
		System.out.println("Create Staff...");
		return new Staff(1,"staff","staff");
	}

}

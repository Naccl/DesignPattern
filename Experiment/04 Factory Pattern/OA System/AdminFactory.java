public class AdminFactory implements UserFactory {

	public Users create() {
		System.out.println("Create Admin...");
		return new Admin(0,"admin","admin");
	}

}

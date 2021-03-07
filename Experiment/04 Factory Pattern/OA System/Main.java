public class Main {
	public static void main(String[] args) {
		UserFactory adminFactory = new AdminFactory();
		Users admin = adminFactory.create();
		admin.login();

		UserFactory staffFactory = new StaffFactory();
		Users staff = staffFactory.create();
		staff.login();
	}
}

public class Admin implements Users {

	private int permission;
	private String username;
	private String password;

	public Admin(int permission, String username, String password) {
		this.permission = permission;
		this.username = username;
		this.password = password;
	}

	public void login() {
		System.out.println("Admin login... permission = " + permission);
	}

}

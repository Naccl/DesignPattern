public class Staff implements Users {

	private int permission;
	private String username;
	private String password;

	public Staff(int permission, String username, String password) {
		this.permission = permission;
		this.username = username;
		this.password = password;
	}

	public void login() {
		System.out.println("Staff login... permission = " + permission);
	}

}

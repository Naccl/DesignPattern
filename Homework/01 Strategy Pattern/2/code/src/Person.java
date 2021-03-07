public class Person {
	private Role role;

	public Person() {
		this.role = Role.STUDENT;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void action() {
		role.action();
	}
}

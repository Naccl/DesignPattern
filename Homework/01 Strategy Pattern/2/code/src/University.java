public class University {
	public static void main(String[] args) {
		Person tom = new Person();
		tom.action();

		tom.setRole(Role.ASSISTANT);
		tom.action();

		tom.setRole(Role.CASHIER);
		tom.action();
	}
}

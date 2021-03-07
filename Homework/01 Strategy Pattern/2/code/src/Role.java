public interface Role {
	Role STUDENT = new Student();
	Role CASHIER = new Cashier();
	Role ASSISTANT = new Assistant();

	void action();
}

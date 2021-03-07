public class Test {
	public static void main(String[] args) {
		Headmaster_Lazy headmaster_lazy1 = Headmaster_Lazy.getHeadmaster();
		Headmaster_Lazy headmaster_lazy2 = Headmaster_Lazy.getHeadmaster();
		headmaster_lazy1.teach();
		headmaster_lazy2.teach();
		System.out.println(headmaster_lazy1 == headmaster_lazy2);

		System.out.println("--------------------------------------");

		Headmaster_Early headmaster_early1 = Headmaster_Early.getHeadmaster();
		Headmaster_Early headmaster_early2 = Headmaster_Early.getHeadmaster();
		headmaster_early1.teach();
		headmaster_early2.teach();
		System.out.println(headmaster_early1 == headmaster_early2);
	}
}

public class Test {
	public static void main(String[] args) {
		Headmaster headmaster1 = Headmaster.getHeadmaster();
		Headmaster headmaster2 = Headmaster.getHeadmaster();
		headmaster1.teach();
		headmaster2.teach();
		System.out.println(headmaster1 == headmaster2);
	}
}

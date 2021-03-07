public class Test {
	public static void main(String[] args) {
		SimpleRoom simpleRoom = new SimpleRoom();
		simpleRoom.add();

		ColorDecorator colorDecorator = new ColorDecorator(simpleRoom);
		colorDecorator.add();

		CurtainsDecorator curtainsDecorator = new CurtainsDecorator(simpleRoom);
		curtainsDecorator.add();
	}
}

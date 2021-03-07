public class CurtainsDecorator extends RoomDecorator {

	public CurtainsDecorator(Room room) {
		super(room);
	}

	public void add() {
		System.out.println("add CurtainsDecorator...");
	}

}

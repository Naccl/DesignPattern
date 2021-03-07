public class RoomDecorator implements Room {

	private Room room;

	public RoomDecorator(Room room) {
		this.room = room;
	}

	public void add() {
		System.out.println("Create RoomDecorator...");
	}

}

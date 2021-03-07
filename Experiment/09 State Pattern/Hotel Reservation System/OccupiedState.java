public class OccupiedState implements State {
	private HotelRoom hotelRoom;

	public OccupiedState(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public void reserve() {
		System.out.println("房间已满");
	}

	public void cancelReservation() {
		System.out.println("取消预定");
	}

	public void checkIn() {
		System.out.println("登记");
	}

	public void checkOut() {
		System.out.println("结束");
	}

}

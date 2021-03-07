public class ReservedState implements State {
	private HotelRoom hotelRoom;

	public ReservedState(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public void reserve() {
		System.out.println("房间已被预定");
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

public class AvailableState implements State {

	private HotelRoom hotelRoom;

	public AvailableState(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public void reserve() {
		System.out.println("预定");
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

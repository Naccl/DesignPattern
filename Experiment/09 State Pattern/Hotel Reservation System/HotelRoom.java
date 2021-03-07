public class HotelRoom {

	private State occupiedState;

	private State reservedState;

	private State availableState;

	private State hotelState;

	private int count;

	public HotelRoom(int count) {
		this.occupiedState = new OccupiedState(this);
		this.reservedState = new ReservedState(this);
		this.availableState = new AvailableState(this);
		this.count = count;
		if (count > 0) {
			hotelState = availableState;
		} else {
			hotelState = occupiedState;
		}
	}

	public void reserve() {
		hotelState.reserve();
	}

	public void cancelReservation() {
		hotelState.cancelReservation();
	}

	public void checkIn() {
		hotelState.checkIn();
	}

	public void checkOut() {
		hotelState.checkOut();
	}

}

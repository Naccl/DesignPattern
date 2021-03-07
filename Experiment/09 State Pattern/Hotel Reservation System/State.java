public interface State {

	public abstract void reserve();

	public abstract void cancelReservation();

	public abstract void checkIn();

	public abstract void checkOut();

}

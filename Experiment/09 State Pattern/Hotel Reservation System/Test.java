/**
 * @Description:
 * @Author: Naccl
 * @Date: 2020-04-27
 */
public class Test {
	public static void main(String[] args) {
		HotelRoom hotelRoom = new HotelRoom(5);
		hotelRoom.reserve();
		hotelRoom.checkIn();
		hotelRoom.checkOut();
	}
}

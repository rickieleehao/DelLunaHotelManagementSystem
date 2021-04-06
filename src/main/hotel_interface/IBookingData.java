package hotel_interface;

import java.util.List;

import hotel_entity.*;

public interface IBookingData {

	public int generateBookingID();
	public List<Room> findAvailableRoom(String checkInDate, String checkOutDate);
	public void addBooking(Booking booking);
	public void updateBookingList(Booking booking);
	public Booking getBooking(int bookingID);

}

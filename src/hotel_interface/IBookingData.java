package hotel_interface;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public interface IBookingData {

	public int generateBookingID();
	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate);
	public void addBooking(Booking booking);
	public void updateBookingList(Booking booking);
	public Booking getBooking(int bookingID);

}

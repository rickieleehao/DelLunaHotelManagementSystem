package hotel.domain.IEntity;

import java.util.List;

import hotel.domain.entity.*;

public interface IBookingData {

	public int generateBookingID();
	public List<Room> findAvailableRoom(String checkInDate, String checkOutDate);
	public void addBooking(Booking booking);
	public void updateBookingList(Booking booking);
	public Booking getBooking(int bookingID);

}

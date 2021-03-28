package hotel_interface;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public interface IBookingData {

	public void addBooking(Booking newBooking);
	
	public void cancelBooking (Booking theBooking);
	
	public void checkIn (Booking theBooking);
	
	
	public void checkOut (Booking theBooking);
	
	public Booking createBooking (int bookingID, ClientProfile clientProfile, 
					Date checkInDate, Date checkOutDate, Room room, int numOfGuest);
	
	public List<Room> findAvailableRoom (Date checkInDate, Date checkOutDate);
	
	public Booking generateBookingID () ;
	
	public Booking getBooking (Booking theBooking);
	
	public void updateBooking (Booking theBooking, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest);
	
	public void updateBooking (Booking theBooking, Room room);
	
	public void updateBooking (Booking theBooking, int numOfGuest);
	
}

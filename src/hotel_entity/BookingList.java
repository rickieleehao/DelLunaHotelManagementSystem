package hotel_entity;

import hotel_interface.Booking;
import hotel_interface.ClientProfile;
import hotel_interface.Date;
import hotel_interface.IBookingData;
import hotel_interface.Room;

public class BookingList implements IBookingData {
	
	List<Booking> bookingList= new List<Booking>;
	
	public BookingList(String fileName) {
		
	}

	@Override
	public void addBooking(Booking newBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(Booking theBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkIn(Booking theBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOut(Booking theBooking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Booking createBooking(int bookingID, ClientProfile clientProfile, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAvailableRoom(Date checkInDate, Date checkOutDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Booking generateBookingID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getBooking(Booking theBooking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uodateBooking(Booking theBooking, Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uodateBooking(Booking theBooking, int numOfGuest) {
		// TODO Auto-generated method stub
		
	}

}

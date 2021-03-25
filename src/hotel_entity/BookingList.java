package hotel_entity;

import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {
	
	List<Booking> bookingList;
	
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
	public Booking createBooking(int bookingID, hotel_entity.ClientProfile clientProfile, java.util.Date checkInDate,
			java.util.Date checkOutDate, hotel_entity.Room room, int numOfGuest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAvailableRoom(java.util.Date checkInDate, java.util.Date checkOutDate) {
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
	public void updateBooking(Booking theBooking, java.util.Date checkInDate, java.util.Date checkOutDate,
			hotel_entity.Room room, int numOfGuest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBooking(Booking theBooking, hotel_entity.Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBooking(Booking theBooking, int numOfGuest) {
		// TODO Auto-generated method stub
		
	}


}

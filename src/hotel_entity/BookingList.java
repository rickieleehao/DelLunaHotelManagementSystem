package hotel_entity;

import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {
	
	List<Booking> bookingList;
	
	public BookingList(String fileName) { // R
		
	}
	
	@Override
	public void addBooking(Booking newBooking) { // xz
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(Booking theBooking) { //xz
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkIn(Booking theBooking) { // april
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkOut(Booking theBooking) { //april
		// TODO Auto-generated method stub
		
	}

	@Override
	public Booking createBooking(int bookingID, hotel_entity.ClientProfile clientProfile, java.util.Date checkInDate,
			java.util.Date checkOutDate, hotel_entity.Room room, int numOfGuest) { //xz
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAvailableRoom(java.util.Date checkInDate, java.util.Date checkOutDate) { //yy
		// TODO Auto-generated method stub
		
	}

	@Override
	public Booking generateBookingID() { //ignore
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking getBooking(Booking theBooking) { //xz
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBooking(Booking theBooking, java.util.Date checkInDate, java.util.Date checkOutDate,
			hotel_entity.Room room, int numOfGuest) { //april
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBooking(Booking theBooking, hotel_entity.Room room) { //april
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBooking(Booking theBooking, int numOfGuest) { //april
		// TODO Auto-generated method stub
		
	}


}

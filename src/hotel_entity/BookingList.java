package hotel_entity;

import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {

	List<Booking> bookingList;

	public BookingList(String fileName) { // R

	}

	@Override
	// return type changed from List<Booking> to List<Room>
	public List<Room> findAvailableRoom(java.util.Date checkInDate, java.util.Date checkOutDate) { // yy
		RoomList availableRoomList = new RoomList();
		return null;
	}

	@Override
	public Booking generateBookingID() { // ignore
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBooking(Booking newBooking) { // xz
		// TODO Auto-generated method stub
		bookingList.add(newBooking);
	}

	@Override
	public void updateBooking(Booking theBooking, java.util.Date checkInDate, java.util.Date checkOutDate,
			hotel_entity.Room room, int numOfGuest) { // april
		// TODO Auto-generated method stub
	
	}

	@Override
	public void updateBooking(Booking theBooking, hotel_entity.Room room) { // april
		// TODO Auto-generated method stub
	
	}

	@Override
	public void updateBooking(Booking theBooking, int numOfGuest) { // april
		// TODO Auto-generated method stub
	
	}

	@Override
	public void cancelBooking(Booking theBooking) { // xz
		// TODO Auto-generated method stub
		theBooking.setStatus(Status.Cancelled);
		bookingList.remove(theBooking);
	}

	@Override
	public Booking getBooking(int bookingID) { // xz -parameter booking change to bookingID
		// TODO Auto-generated method stub
		Booking theBooking = null;
		for (int i = 0; i < bookingList.size(); i++) {
			if (bookingList.get(i).getBookingID() == bookingID) {
				theBooking = bookingList.get(i);
				break;
			}
		}
		return theBooking;
	}

	@Override
	public void checkIn(Booking theBooking) { // april
		// TODO Auto-generated method stub

	}

	@Override
	public void checkOut(Booking theBooking) { // april
		// TODO Auto-generated method stub

	}

	@Override
	public double getBill(Booking theBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber) {
		// TODO Auto-generated method stub
		
	}

}

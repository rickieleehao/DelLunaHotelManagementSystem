package hotel_entity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {

	List<Booking> bookingList;

	public BookingList(String fileName) { // R

	}

	@Override
	// return type changed from List<Booking> to List<Room>
	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // yy
		RoomList roomList = new RoomList();
		List<Room> availableRoomList = roomList.getRoomList();
		int counter;
		for (int i = 0; i < availableRoomList.size(); i++) {
			counter = 0;
			for (int j = 0; i < bookingList.size(); j++) {
				if (!(checkInDate.after(bookingList.get(j).getCheckOutDate()))
						|| !(checkOutDate.before(bookingList.get(i).getCheckInDate()))) {
					counter++;
				}
			}
			if (counter == bookingList.size()) {
				availableRoomList.remove(i);
			}
		}
		return availableRoomList;
	}

	@Override
	public int generateBookingID() { // ignore
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addBooking(Booking newBooking) {
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
	public void cancelBooking(Booking theBooking) {
		theBooking.setStatus(Status.Cancelled);
	}

	@Override
	public Booking getBooking(int bookingID) {
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
	public void checkIn(Booking theBooking) {
		theBooking.setStatus(Status.CheckedIn);
	}

	@Override
	public void checkOut(Booking theBooking) {
		theBooking.setStatus(Status.CheckedOut);
	}

	@Override
	public double getBill(Booking theBooking) {
		theBooking.computeBill();
		return theBooking.getBill();
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod) {
		theBooking.makePayment(paymentMethod);
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber) {
		theBooking.makePayment(paymentMethod, cardNumber);
	}

}

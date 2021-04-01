package hotel_entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {

	List<Booking> bookingList;

	public BookingList(String fileName) { // R

	}

	@Override
	// return type changed from List<Booking> to List<Room>
	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // yy still not complete
		RoomList roomList = new RoomList();
		List<Room> availableRoomList = roomList.getRoomList();
		ArrayList<Room> removedRoom = new ArrayList<Room>();
		
		for (int i = 0; i < bookingList.size(); i++) {
			if (!(checkInDate.after(bookingList.get(i).getCheckOutDate()))
					&& !(checkOutDate.before(bookingList.get(i).getCheckInDate()))) {
				removedRoom.add(bookingList.get(i).getRoom());
			}
		}
		
		for(int j = 0; j < availableRoomList.size(); j++) {
			for(int k = 0; k < removedRoom.size(); k++) {
				if(availableRoomList.get(j).getRoomID() == removedRoom.get(k).getRoomID()) {
					availableRoomList.remove(k);
				}
		}
		return null;
	}

	@Override
	public int generateBookingID() {
		int bookingID = bookingList.size() + 1;
		return bookingID;
	}

	@Override
	public void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
	}

	@Override
	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) {
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == theBooking.getBookingID()) {
				aBooking.setCheckInDate(checkInDate);
				aBooking.setCheckInDate(checkOutDate);
				aBooking.setRoom(room);
				aBooking.setNumOfGuest(numOfGuest);
				break;
			}
		}
	}

	@Override
	public void updateBooking(Booking theBooking, Room room) {
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == theBooking.getBookingID()) {
				aBooking.setRoom(room);
				break;
			}
		}
	}

	@Override
	public void updateBooking(Booking theBooking, int numOfGuest) {
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == theBooking.getBookingID()) {
				aBooking.setNumOfGuest(numOfGuest);
				break;
			}
		}

	}

	@Override
	public void cancelBooking(Booking theBooking) {
		for (int i = 0; i < bookingList.size(); i++) {
			if (bookingList.get(i).getBookingID() == (theBooking.getBookingID())) {
				bookingList.get(i).setStatus(Status.Cancelled);
				break;
			}
		}
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

		if (theBooking.equals(null)) {
			throw new IllegalArgumentException("Booking not found!");
		}

		return theBooking;
	}

	@Override
	public void checkIn(Booking theBooking) {

		if (theBooking.equals(null)) {
			throw new IllegalArgumentException("Check-in failed.");
		}

		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == theBooking.getBookingID()) {
				aBooking.setStatus(Status.CheckedIn);
				break;

			}
		}

	}

	@Override
	public void checkOut(Booking theBooking) {
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == theBooking.getBookingID()) {
				aBooking.setStatus(Status.CheckedOut);
				break;
			}
		}
	}

	@Override
	public double getBill(Booking theBooking) {
		double bill = 0;
		for (int i = 0; i < bookingList.size(); i++) {
			if (bookingList.get(i).getBookingID() == (theBooking.getBookingID())) {
				bookingList.get(i).computeBill();
				bill = bookingList.get(i).getBill();
				break;
			}
		}
		return bill;
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod) {
		Booking booking = null;
		for (int i = 0; i < bookingList.size(); i++) {
			if (theBooking.getBookingID() == bookingList.get(i).getBookingID()) {
				booking = bookingList.get(i);
				break;
			}
		}
		booking.makePayment(paymentMethod);
	}

	@Override
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber) {
		Booking booking = null;
		for (int i = 0; i < bookingList.size(); i++) {
			if (theBooking.getBookingID() == bookingList.get(i).getBookingID()) {
				booking = bookingList.get(i);
				break;
			}
		}
		booking.makePayment(paymentMethod, cardNumber);
	}

}

package hotel_entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {

	List<Booking> bookingList;

	public BookingList(String fileName) {

	}

	@Override
	public int generateBookingID() {
		int bookingID = bookingList.size() + 1;
		return bookingID;
	}

	@Override
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
		return availableRoomList;
	}

	@Override
	public void addBooking(Booking newBooking) {
		bookingList.add(newBooking);
	}

	@Override
	public void updateBookingList(Booking booking) {
		for(Booking aBooking : bookingList) {
			if(aBooking.getBookingID() == booking.getBookingID())
			{
				aBooking = booking;
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
		if(theBooking==null) {
			throw new IllegalArgumentException("Booking not found");
		}
		return theBooking;
	}
}

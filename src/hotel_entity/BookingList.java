package hotel_entity;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import hotel_interface.IBookingData;

public class BookingList implements IBookingData {

	List<Booking> bookingList;

	public BookingList(String fileName) {
		this.bookingList = new ArrayList<Booking>();
		String $filename = fileName;
		try {
			Scanner s = new Scanner(new File($filename));
			s.useDelimiter("(,|\r\n|\r|\n)");
			while (s.hasNext()) {
				this.bookingList.add(new Booking(s.nextInt(),
						new ClientProfile(s.next(),s.next(),s.next(),s.next(),s.next()),
						s.next(),s.next(),
						new Room(s.nextInt(),s.nextDouble(),s.nextInt()),
						s.nextInt()));
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int generateBookingID() {
		int bookingID = bookingList.size() + 1;
		return bookingID;
	}

	@Override
	public List<Room> findAvailableRoom(LocalDate checkInDate, LocalDate checkOutDate) { // yy still not complete
		RoomList roomList = new RoomList();
		List<Room> availableRoomList = roomList.getRoomList();
		ArrayList<Room> removedRoom = new ArrayList<Room>();

		for (int i = 0; i < bookingList.size(); i++) {
			if (!(checkInDate.isAfter(bookingList.get(i).getCheckOutDate()))
					&& !(checkOutDate.isBefore(bookingList.get(i).getCheckInDate()))) {
				removedRoom.add(bookingList.get(i).getRoom());
			}
		}

		for (int j = 0; j < availableRoomList.size(); j++) {
			for (int k = 0; k < removedRoom.size(); k++) {
				if (availableRoomList.get(j).getRoomID() == removedRoom.get(k).getRoomID()) {
					availableRoomList.remove(k);
				}
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
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == booking.getBookingID()) {
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
		if (theBooking == null) {
			throw new IllegalArgumentException("Booking not found");
		}
		return theBooking;
	}
}

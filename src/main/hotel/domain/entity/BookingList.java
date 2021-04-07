package hotel.domain.entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hotel.domain.IEntity.IBookingData;

public class BookingList implements IBookingData {

	private List<Booking> bookingList;
	private String fileName;

	public BookingList(String fileName, ClientProfileList CPL, RoomList RL) {
		this.bookingList = new ArrayList<Booking>();
		this.fileName = fileName;
		int bookingID;
		ClientProfile clientProfile = null;
		LocalDate checkInDate;
		LocalDate checkOutDate;
		Room room = null;
		int numOfGuest;
		Payment payment;
		Status status = null;
		String tempString;

		try {
			Scanner s = new Scanner(new File(fileName));
			s.useDelimiter("(#|\r\n|\r|\n)");
			while (s.hasNext()) {
				bookingID = s.nextInt();
				tempString = s.next();
				for (ClientProfile c : CPL.getClientProfileList()) {
					if (c.getNRIC().equals(tempString)) {
						clientProfile = c;
						break;
					}
				}
				checkInDate = LocalDate.parse(s.next());
				checkOutDate = LocalDate.parse(s.next());
				tempString = s.next();
				for (Room r : RL.getRoomList()) {
					if (r.getRoomID() == Integer.parseInt(tempString)) {
						room = r;
						break;
					}
				}
				numOfGuest = s.nextInt();
				payment = new Payment(s.next(), s.nextDouble(), s.next());
				tempString = s.next();
				if (tempString.equals("CheckedIn")) {
					status = Status.CheckedIn;
				} else if (tempString.equals("Cancelled")) {
					status = Status.Cancelled;
				} else if (tempString.equals("CheckedOut")) {
					status = Status.CheckedOut;
				} else if (tempString.equals("Confirmed")) {
					status = Status.Confirmed;
				}

				this.bookingList.add(new Booking(bookingID, clientProfile, checkInDate, checkOutDate, room, numOfGuest,
						payment, status));
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateToFile() {
		String fileName = this.fileName;
		try {
			FileWriter $fileEmptier = new FileWriter(fileName);
			FileWriter $writer = new FileWriter(fileName, true);
			$fileEmptier.write("");
			$fileEmptier.close();
			for (Booking b : this.bookingList) {
				$writer.write(b.getBookingID() + "#" + b.getClientProfile().getNRIC() + "#"
						+ b.getCheckInDate().toString() + "#" + b.getCheckOutDate().toString() + "#"
						+ b.getRoom().getRoomID() + "#" + b.getNumOfGuest() + "#"
						+ b.getPayment().getPaymentMethod().toString() + "#" + b.getPayment().getTotalPrice() + "#"
						+ b.getPayment().getCardNumber() + "#" + b.getStatus().toString() + "\r\n");
			}
			$writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int generateBookingID() {
		int bookingID = this.bookingList.get(this.bookingList.size() - 1).getBookingID() + 1; // last ID + 1 = new ID
		return bookingID;
	}

	@Override
	public List<Room> findAvailableRoom(String checkInString, String checkOutString) {
		LocalDate checkInDate;
		LocalDate checkOutDate;
		try {
			checkInDate = LocalDate.parse(checkInString);
			checkOutDate = LocalDate.parse(checkOutString);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Incorrect date format!");
		}

		RoomList roomList = new RoomList();
		List<Room> availableRoomList = roomList.getRoomList();
		ArrayList<Room> removedRoom = new ArrayList<Room>();

		for (int i = 0; i < bookingList.size(); i++) {
			if (!(checkInDate.isAfter(bookingList.get(i).getCheckOutDate()))
					&& !(checkOutDate.isBefore(bookingList.get(i).getCheckInDate()))
					&& (bookingList.get(i).getStatus() == Status.Confirmed
							|| bookingList.get(i).getStatus() == Status.CheckedIn)) {
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
		updateToFile();
	}

	@Override
	public void updateBookingList(Booking booking) {
		for (Booking aBooking : bookingList) {
			if (aBooking.getBookingID() == booking.getBookingID()) {
				aBooking = booking;
				break;
			}
		}
		updateToFile();
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
			throw new NullPointerException("Booking not found");
		}
		return theBooking;
	}
	
	public List<Booking>getBookingList(){
		return this.bookingList;
	}
}

package hotel_main;

import hotel_interface.*;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public class Controller {

	private IBookingData bookingList;
	private IClientData clientProfileList;
	private IUser user;

	public Controller(IBookingData bookingList, IClientData clientProfileList, IUser user) {

		this.bookingList = bookingList;
		this.clientProfileList = clientProfileList;
		this.user = user;
	}

	public void addBooking(Booking newBooking) { // april
		
	}

	public void cancelBooking(Booking theBooking) { // april

	}

	public void checkIn(Booking theBooking) { // april

	}

	public void checkOut(Booking theBooking) { // april

	}

	public Booking createBooking(int bookingID, ClientProfile clientProfile, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest) { // april
		return null;
	}

	public ClientProfile createClientProfile(String NRIC, String firstName, String lastName, Gender gender,
			String address) { // xz
		return null;
	}

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // xz
		return null;
	}

	public int generateBookingID() { // ignore
		return 0;
	}

	public Booking getBooking(Booking theBooking) { // xz
		return null;
	}

	public int getCardNumber(Booking theBooking) { // xz
		return 0;
	}

	public Date getCheckInDate(Booking theBooking) { // xz
		return null;
	}


	public ClientProfile getClientProfile(String NRIC) { //hy
		return null;

	}

	public double getDeposit(Booking theBooking) { // hy
		return 0;
	}

	public PaymentMethod getPaymentMethod(Booking theBooking) { // hy
		return null;
	}

	public Booking searchBooking(int bookingID) {
		return null;
	}

	public boolean searchClientProfile(String NRIC) { // hy
		return true;
	}

	public void updateBooking(Booking theBooking, Room room) { // yy

	}

	public void updateBooking(Booking theBooking, int numOfGuest) { // yy

	}

	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) { // yy

	}

	public boolean validatePolicy(Booking theBooking, Date dateToday) { // yy
		return true;
	}

	public UserType getUserType() { // yy
		return this.user.getType();
	}

}

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

	public void addBooking(Booking newBooking) {

	}

	public void cancelBooking(Booking theBooking) {

	}

	public void checkIn(Booking theBooking) {

	}

	public void checkOut(Booking theBooking) {

	}

	public Booking createBooking(int bookingID, ClientProfile clientProfile, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest) {
		return null;
	}

	public ClientProfile createClientProfile(String NRIC, String firstName, String lastName, Gender gender,
			String address) {
		return null;
	}

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) {
		return null;
	}

	public int generateBookingID() {
		return 0;
	}

	public Booking getBooking(Booking theBooking) {
		return null;
	}

	public int getCardNumber(Booking theBooking) {
		return 0;
	}

	public Date getCheckInDate(Booking theBooking) {
		return null;
	}

	public ClientProfile getClientProfile(String NRIC) {
		return null;
	}

	public double getDeposit(Booking theBooking) {
		return 0;
	}

	public PaymentMethod getPaymentMethod(Booking theBooking) {
		return null;
	}

	public Booking searchBooking(Booking theBooking) {
		return null;
	}

	public boolean searchClientProfile(String NRIC) {
		return true;
	}

	public void updateBooking(Booking theBooking, Room room) {

	}

	public void updateBooking(Booking theBooking, int numOfGuest) {

	}

	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) {

	}

	public boolean validatePolicy(Booking theBooking, Date dateToday) {
		return true;
	}

}

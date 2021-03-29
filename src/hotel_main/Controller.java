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
		
		ClientProfile newClientProfile = new ClientProfile(NRIC,firstName,lastName,gender,address);
		clientProfileList.addClientProfile(newClientProfile);
		
		return newClientProfile;
	}

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // xz
		
		List<Room>availableRoomList= bookingList.findAvailableRoom(checkInDate, checkOutDate);
		
		return availableRoomList;
	}

	public int generateBookingID() { // ignore
		return 0;
	}

	

	public int getCardNumber(Booking theBooking) { // xz
		Payment payment=theBooking.getPayment();
		int cardNumber = payment.getCardNumber();
		return cardNumber;
	}

	public Date getCheckInDate(Booking theBooking) { // xz
		Date checkInDate = theBooking.getCheckInDate();
		return checkInDate;
	}
	
	public Date getCheckOutDate(Booking theBooking) {
		Date checkOutDate = theBooking.getCheckOutDate();
		return checkOutDate;
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

	public Booking searchBooking(int bookingID) { //hy
		return null;
	}

	public boolean searchClientProfile(String NRIC) { // hy
		return true;
	}

	public void updateBooking(Booking theBooking, Room room) { // yy
		bookingList.updateBooking(theBooking, room);
	}

	public void updateBooking(Booking theBooking, int numOfGuest) { // yy
		bookingList.updateBooking(theBooking, numOfGuest);
	}

	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) { // yy
		bookingList.updateBooking(theBooking, checkInDate, checkOutDate, room, numOfGuest);
	}

	public boolean validatePolicy(Booking theBooking, Date dateToday) { // yy
		boolean isRefundable = theBooking.validatePolicy(dateToday);
		return isRefundable;
	}

	public UserType getUserType() { // yy
		return this.user.getType();
	}

}

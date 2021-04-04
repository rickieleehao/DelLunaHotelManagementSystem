package hotel_main;

import hotel_interface.*;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public class Controller {

	private IUser user;
	private IBooking booking;
	private IBookingData bookingList;
	private IClientProfile clientProfile;
	private IClientData clientProfileList;

	public Controller(IBookingData bookingList, IClientData clientProfileList, IUser user) {
		this.bookingList = bookingList;
		this.clientProfileList = clientProfileList;
		this.user = user;
	}

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // xz
		List<Room> availableRoomList = bookingList.findAvailableRoom(checkInDate, checkOutDate);
		return availableRoomList;
	}

	public void addBooking() {
		this.bookingList.addBooking((Booking)booking);
	}

	public void updateBookingList(IBooking booking) {
		this.bookingList.updateBookingList((Booking) this.booking);
	}

	public Booking getBooking(int bookingID) {
		return (Booking) this.booking; // this is add cast, meaning convert IBooking into Booking
	}

	public void createBooking() {
		this.booking = new Booking();
	}

	public void createBooking(Booking booking) {
		this.booking = new Booking();
		this.booking = booking;
	}

	public void setBookingID(int bookingID) {
		// TODO Auto-generated method stub

	}

	public void setClientProfile(ClientProfile clientProfile) {
		// TODO Auto-generated method stub

	}

	public void setCheckInDate(Date checkInDate) {
		// TODO Auto-generated method stub

	}

	public void setCheckOutDate(Date checkOutDate) {
		// TODO Auto-generated method stub

	}

	public void setRoom(Room room) {
		// TODO Auto-generated method stub

	}

	public void setNumOfGuest(int numOfGuest) {
		// TODO Auto-generated method stub

	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub

	}

	public void setTotalPrice(double totalPrice) {
		// TODO Auto-generated method stub

	}

	public void setStatus(Status status) {
		// TODO Auto-generated method stub

	}

	public void setCardNumber(int cardNumber) {
		// TODO Auto-generated method stub

	}

	public int getBookingID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Date getCheckInDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCheckOutDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Room getRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumOfGuest() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Payment getPayment() {
		// TODO Auto-generated method stub
		return null;
	}

	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getTotalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean validatePolicy(Date dateToday) {
		boolean isRefundable;
		isRefundable = booking.validatePolicy(dateToday);
		return isRefundable;
	}

	public void makePayment(PaymentMethod paymentMethod) {
		booking.makePayment(paymentMethod);

	}

	public void makePayment(PaymentMethod paymentMethod, int cardNumber) {
		booking.makePayment(paymentMethod, cardNumber);

	}

	public void setNRIC(String NRIC) {
		clientProfile.setNRIC(NRIC);

	}

	public void setFirstName(String firstName) {
		clientProfile.setFirstName(firstName);

	}

	public void setLastName(String lastName) {
		clientProfile.setLastName(lastName);

	}

	public void setGender(Gender gender) {
		clientProfile.setGender(gender);

	}

	public void setAddress(String address) {
		clientProfile.setAddress(address);

	}

	public void addClientProfile(ClientProfile clientProfile) {
		this.clientProfileList.addClientProfile((ClientProfile) this.clientProfile);
	}

	public void createClientProfile() {
		this.clientProfile = new ClientProfile();
	}

	public ClientProfile getClientProfile() {
		return (ClientProfile) this.clientProfile;
	}

	public String getNRIC() {
		return clientProfile.getNRIC();
	}

	public String getFirstName() {
		return clientProfile.getFirstName();
	}

	public String getLastName() {
		return clientProfile.getLastName();
	}

	public Gender getGender() {
		return clientProfile.getGender();
	}

	public String getAddress() {
		return clientProfile.getAddress();
	}

	public UserType getUserType() {
		return user.getUserType();
	}

	public void login(String password) {
		
	}
}

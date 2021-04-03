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

	public void updateBooking() {
		this.bookingList.updateBookingList((Booking) this.booking);
	}

	public Booking getBooking() {
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
		// TODO Auto-generated method stub
		return false;
	}

	public void makePayment(PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub

	}

	public void makePayment(PaymentMethod paymentMethod, int cardNumber) {
		// TODO Auto-generated method stub

	}

	public void setNRIC(String NRIC) {
		// TODO Auto-generated method stub

	}

	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub

	}

	public void setLastName(String lastName) {
		// TODO Auto-generated method stub

	}

	public void setGender(Gender gender) {
		// TODO Auto-generated method stub

	}

	public void setAddress(String address) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Gender getGender() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserType getUserType() {
		return null;
	}

	public void login(String password) {

	}
}

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

	public void setBookingID() {
		int bookingID=bookingList.generateBookingID();
		booking.setBookingID(bookingID);				
	}

	public void setClientProfile(ClientProfile clientProfile) {
		booking.setClientProfile(clientProfile);
	}

	public void setCheckInDate(Date checkInDate) {
		booking.setCheckInDate(checkInDate);
	}

	public void setCheckOutDate(Date checkOutDate) {
		booking.setCheckOutDate(checkOutDate);
	}

	public void setRoom(int room) {
		booking.setRoom(room);
	}

	public void setNumOfGuest(int numOfGuest) {
		booking.setNumOfGuest(numOfGuest);
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub

	}

	public void setTotalPrice(double totalPrice) {
		booking.setTotalPrice(totalPrice);
	}

	public void setStatus(Status status) {
		booking.setStatus(status);		
	}

	public void setCardNumber(int cardNumber) {		
		
	}

	public int getBookingID() {
		int bookingID=booking.getBookingID();
		return bookingID;
	}

	public Date getCheckInDate() {
		Date checkInDate = booking.getCheckInDate();
		return checkInDate;
	}

	public Date getCheckOutDate() {
		Date checkOutDate = booking.getCheckOutDate();
		return checkOutDate;
	}

	public Room getRoom() {
		Room room = booking.getRoom();
		return room;
	}

	public int getNumOfGuest() {
		int numOfGuest = booking.getNumOfGuest();
		return numOfGuest;
	}

	public Payment getPayment() {
		Payment payment=booking.getPayment();
		return payment;
	}

	public Status getStatus() {
		Status status=booking.getStatus();
		return status;
	}

	public double getTotalPrice() {
		double totalPrice=booking.getTotalPrice();
		return totalPrice;
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
	
	public void setClientProfile(String NRIC) {
		ClientProfile clientProfile=clientProfileList.getClientProfile(NRIC);
		booking.setClientProfile(clientProfile);
		this.clientProfile=clientProfile;
	}
	
	public PaymentMethod getPaymentMethod() {
		return booking.getPayment().getPaymentMethod();
	}
}

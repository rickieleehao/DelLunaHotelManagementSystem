package hotel.controller;

import java.time.LocalDate;
import java.util.List;

import hotel.domain.IEntity.*;
import hotel.domain.entity.*;

public class Controller {

	private IUser user;
	private IBooking booking;
	private IBookingData bookingList;
	private IClientProfile clientProfile;
	private IClientData clientProfileList;
	private RoomList availableRoomList;

	public Controller(IBookingData bookingList, IClientData clientProfileList, IUser user) {
		this.bookingList = bookingList;
		this.clientProfileList = clientProfileList;
		this.user = user;
	}

	public ClientProfile getClientProfile() {
		return (ClientProfile) this.clientProfile;
	}

	public Booking getBooking(int bookingID) {
		return this.bookingList.getBooking(bookingID);
	}

	/*
	 * <Room> Entity
	 */
	public void setAvailableRoom(String checkInDate, String checkOutDate) {
		this.availableRoomList = new RoomList();
		List<Room> roomList = this.bookingList.findAvailableRoom(checkInDate, checkOutDate);
		this.availableRoomList.setRoomList(roomList);
	}

	public List<Room> getAvailableRoomList() {
		return this.availableRoomList.getRoomList();
	}

	public int getRoomID(Room room) {
		return room.getRoomID();
	}

	public String getRoomType(Room room) {
		return room.getType();
	}
	
	public double getRoomRate(Room room) {
		return room.getRate();
	}

	public int getRoomNumOfBed(Room room) {
		return room.getNumOfBed();
	}

	public Room getRoom(int option) {
		return this.availableRoomList.getRoom(option);
	}

	/*
	 * <BookingList> Entity
	 */
	public void addBooking() {
		this.bookingList.addBooking((Booking) this.booking);
	}

	public void updateBookingList() {
		this.bookingList.updateBookingList((Booking) this.booking);
	}

	/*
	 * <Booking> Entity
	 */
	public void createBooking() {
		this.booking = new Booking();
	}

	public void createBooking(Booking booking) {
		this.booking = new Booking();
		this.booking = booking;
	}

	public void setBookingID() {
		int bookingID = this.bookingList.generateBookingID();
		this.booking.setBookingID(bookingID);
	}

	public void setClientProfile(ClientProfile clientProfile) {
		this.booking.setClientProfile(clientProfile);
	}

	public void setClientProfile(String NRIC) {
		ClientProfile clientProfile = this.clientProfileList.getClientProfile(NRIC);
		this.booking.setClientProfile(clientProfile);
		this.clientProfile = clientProfile;
	}

	public void setClientProfile() {
		this.clientProfile = new ClientProfile();
		this.clientProfile = this.booking.getClientProfile();
	}

	public void setCheckInDate(String checkInDate) {
		this.booking.setCheckInDate(checkInDate);
	}

	public void setCheckOutDate(String checkOutDate) {
		this.booking.setCheckOutDate(checkOutDate);
	}

	public void setRoom(Room selectedRoom) {
		this.booking.setRoom(selectedRoom);
	}

	public void setNumOfGuest(int numOfGuest) {
		this.booking.setNumOfGuest(numOfGuest);
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.booking.setPaymentMethod(paymentMethod);
	}

	public void setTotalPrice(double totalPrice) {
		this.booking.setTotalPrice(totalPrice);
	}

	public void setStatus(Status status) {
		this.booking.setStatus(status);
	}

	public void setCardNumber(String cardNumber) {
		this.booking.setCardNumber(cardNumber);
	}

	public int getBookingID() {
		int bookingID = this.booking.getBookingID();
		return bookingID;
	}

	public LocalDate getCheckInDate() {
		LocalDate checkInDate = this.booking.getCheckInDate();
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		LocalDate checkOutDate = this.booking.getCheckOutDate();
		return checkOutDate;
	}

	public Room getRoom() {
		Room room = this.booking.getRoom();
		return room;
	}

	public int getNumOfGuest() {
		int numOfGuest = this.booking.getNumOfGuest();
		return numOfGuest;
	}

	public PaymentMethod getPaymentMethod() {
		return this.booking.getPayment().getPaymentMethod();
	}

	public String getCardNumber() {
		return this.booking.getPayment().getCardNumber();
	}

	public double getDeposit() {
		return this.booking.getPayment().getDeposit();
	}

	public Status getStatus() {
		Status status = this.booking.getStatus();
		return status;
	}

	public double getTotalPrice() {
		double totalPrice = this.booking.getTotalPrice();
		return totalPrice;
	}

	public boolean validatePolicy() {
		boolean isRefundable;
		isRefundable = this.booking.validatePolicy();
		return isRefundable;
	}

	public void makePayment(PaymentMethod paymentMethod) {
		this.booking.makePayment(paymentMethod);
	}

	public void makePayment(PaymentMethod paymentMethod, String cardNumber) {
		this.booking.makePayment(paymentMethod, cardNumber);
	}

	/*
	 * <ClientProfileList> Entity
	 */
	public void addClientProfile() {
		this.clientProfileList.addClientProfile((ClientProfile) this.clientProfile);
	}

	/*
	 * <ClientProfile> Entity
	 */
	public void createClientProfile() {
		this.clientProfile = new ClientProfile();
	}

	public void setNRIC(String NRIC) {
		this.clientProfile.setNRIC(NRIC);
	}

	public void setFirstName(String firstName) {
		this.clientProfile.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		this.clientProfile.setLastName(lastName);
	}

	public void setGender(Gender gender) {
		this.clientProfile.setGender(gender);
	}

	public void setAddress(String address) {
		this.clientProfile.setAddress(address);
	}

	public String getNRIC() {
		return this.clientProfile.getNRIC();
	}

	public String getFirstName() {
		return this.clientProfile.getFirstName();
	}

	public String getLastName() {
		return this.clientProfile.getLastName();
	}

	public Gender getGender() {
		return this.clientProfile.getGender();
	}

	public String getAddress() {
		return this.clientProfile.getAddress();
	}

	/*
	 * <User> Entity
	 */
	public UserType getUserType() {
		return this.user.getUserType();
	}

	public void login(String password) {
		this.user.login(password);
	}

	/*
	 * <PaymentMethod> Enum
	 */
	public void printPaymentMethodOption() {
		PaymentMethod paymentMethod = PaymentMethod.Undefined;
		paymentMethod.printPaymentMethodOption();
	}

	public PaymentMethod selectPaymentMethod(int option) {
		PaymentMethod paymentMethod = PaymentMethod.Undefined;
		return paymentMethod.selectPaymentMethod(option);
	}
	
	/*
	 * <Gender> Enum
	 */
	public void printGenderOption() {
		Gender gender = Gender.Undefined;
		gender.printGenderOption();
	}
	
	public Gender selectGender(int choice) {
		Gender gender = Gender.Undefined;
		return gender.selectGender(choice);
	}
}

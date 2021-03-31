package hotel_main;

import hotel_interface.*;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public class Controller implements IEntityCtrl {

	private IBookingData bookingList;
	private IClientData clientProfileList;
	private IUser user;

	public Controller(IBookingData bookingList, IClientData clientProfileList, IUser user) {
		this.bookingList = bookingList;
		this.clientProfileList = clientProfileList;
		this.user = user;
	}

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate) { // xz
		List<Room> availableRoomList = bookingList.findAvailableRoom(checkInDate, checkOutDate);
		return availableRoomList;
	}

	public boolean searchClientProfile(String NRIC) { // hy
		return true;
	}

	public int generateBookingID() {
		return bookingList.generateBookingID();
	}

	public ClientProfile getClientProfile(String NRIC) { // hy
		return null;
	}

	public Booking createBooking(int bookingID, ClientProfile clientProfile, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest) { 
		Booking theBooking= new Booking(bookingID, clientProfile, checkInDate, checkOutDate, room,
				numOfGuest);
		return theBooking;
	}

	public void addBooking(Booking newBooking) { 
		bookingList.addBooking(newBooking);

	}

	public ClientProfile createClientProfile(String NRIC, String firstName, String lastName, Gender gender,
			String address) { // xz

		ClientProfile newClientProfile = new ClientProfile(NRIC, firstName, lastName, gender, address);
		clientProfileList.addClientProfile(newClientProfile);

		return newClientProfile;
	}

	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) { // yy
		bookingList.updateBooking(theBooking, checkInDate, checkOutDate, room, numOfGuest);
	}

	public void updateBooking(Booking theBooking, Room room) { // yy
		bookingList.updateBooking(theBooking, room);
	}

	public void updateBooking(Booking theBooking, int numOfGuest) { // yy
		bookingList.updateBooking(theBooking, numOfGuest);
	}

	public boolean validatePolicy(Booking theBooking, Date dateToday) { // yy
		boolean isRefundable = theBooking.validatePolicy(dateToday);
		return isRefundable;
	}

	public double getDeposit(Booking theBooking) { // hy
		return 0;
	}

	public PaymentMethod getPaymentMethod(Booking theBooking) { // hy
		return null;
	}

	public int getCardNumber(Booking theBooking) { // xz
		Payment payment = theBooking.getPayment();
		int cardNumber = payment.getCardNumber();
		return cardNumber;
	}

	public void cancelBooking(Booking theBooking) { 
		bookingList.cancelBooking(theBooking);
	}

	public Booking searchBooking(int bookingID) { // hy
		return null;
	}

	public void checkIn(Booking theBooking) { 
		bookingList.checkIn(theBooking);
	}

	public void checkOut(Booking theBooking) { 
		bookingList.checkOut(theBooking);
	}

	public void makePayment(Booking theBooking, PaymentMethod paymentMethod) {
		bookingList.makePayment(theBooking, paymentMethod);
	}

	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber) {
		bookingList.makePayment(theBooking, paymentMethod, cardNumber);
	}

	public UserType getUserType() { // yy
		return this.user.getUserType();
	}

	public boolean login(String password) { //r
		isAdministrator ia = new isAdministrator();
		return ia.verifyAdmin(password);				
	}

	@Override
	public int getBookingID(Booking theBooking) {
		int bookingID = theBooking.getBookingID();
		return bookingID;
	}

	@Override
	public ClientProfile getClientProfile(Booking theBooking) {
		ClientProfile clientProfile = theBooking.getClientProfile();
		return clientProfile;
	}

	@Override
	public Date getCheckInDate(Booking theBooking) {
		Date checkInDate = theBooking.getCheckInDate();
		return checkInDate;
	}

	@Override
	public Date getCheckOutDate(Booking theBooking) {
		Date checkOutDate = theBooking.getCheckOutDate();
		return checkOutDate;
	}

	@Override
	public Room getRoom(Booking theBooking) {
		Room room = theBooking.getRoom();
		return room;
	}

	@Override
	public int getNumOfGuest(Booking theBooking) {
		int numOfGuest = theBooking.getNumOfGuest();
		return numOfGuest;
	}

	@Override
	public Payment getPayment(Booking theBooking) {
		Payment payment = theBooking.getPayment();
		return payment;
	}

	@Override
	public Status getStatus(Booking theBooking) {
		Status status = theBooking.getStatus();
		return status;
	}

	@Override
	public double getBill(Booking theBooking) {
		double bill = theBooking.getBill();
		return bill;
	}

	@Override
	public String getNRIC(ClientProfile clientProfile) {
		String NRIC = clientProfile.getNRIC();
		return NRIC;
	}

	@Override
	public String getFirstName(ClientProfile clientProfile) {
		String firstName = clientProfile.getFirstName();
		return firstName;
	}

	@Override
	public String getLastName(ClientProfile clientProfile) {
		String lastName = clientProfile.getLastName();
		return lastName;
	}

	@Override
	public Gender getGender(ClientProfile clientProfile) {
		Gender gender = clientProfile.getGender();
		return gender;
	}

	@Override
	public String getAddress(ClientProfile clientProfile) {
		String address = clientProfile.getAddress();
		return address;
	}
}

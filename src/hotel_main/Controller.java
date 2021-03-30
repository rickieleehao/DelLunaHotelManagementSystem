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

	public int generateBookingID() { // ignore
		return 0;
	}

	public ClientProfile getClientProfile(String NRIC) { // hy
		return null;
	}

	public Booking createBooking(int bookingID, ClientProfile clientProfile, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest) { // april
		return null;
	}

	public void addBooking(Booking newBooking) { // april

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

	public void cancelBooking(Booking theBooking) { // april

	}

	public Booking searchBooking(int bookingID) { // hy
		return null;
	}

	public void checkIn(Booking theBooking) { // april

	}

	public void checkOut(Booking theBooking) { // april

	}

	public void makePayment(Booking theBooking, PaymentMethod paymentMethod) {

	}

	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber) {

	}

	public UserType getUserType() { // yy
		return this.user.getUserType();
	}

	public boolean login(String password) { //r
		return true;
	}

	@Override
	public int getBookingID(Booking theBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClientProfile getClientProfile(Booking theBooking) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfGuest(Booking theBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Payment getPayment(Booking theBooking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getStatus(Booking theBooking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBill(Booking theBooking) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNRIC(ClientProfile clientProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName(ClientProfile clientProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName(ClientProfile clientProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gender getGender(ClientProfile clientProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAddress(ClientProfile clientProfile) {
		// TODO Auto-generated method stub
		return null;
	}
}

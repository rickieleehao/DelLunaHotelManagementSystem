package hotel_entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import hotel_interface.IBooking;

public class Booking implements IBooking {
	private int bookingID;
	private ClientProfile client;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Room room;
	private int numOfGuest;
	private Payment payment;
	private Status status;

	public Booking(int bookingID, ClientProfile client, LocalDate checkInDate, LocalDate checkOutDate, Room room,
			int numOfGuest) {
		this.bookingID = bookingID;
		this.client = client;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		this.numOfGuest = numOfGuest;
	}

	public Booking(int bookingID, ClientProfile client, String checkInDate, String checkOutDate, Room room,
			int numOfGuest) {
		this.bookingID = bookingID;
		this.client = client;
		LocalDate checkIn = LocalDate.parse(checkInDate);
		this.checkInDate = checkIn;
		LocalDate checkOut = LocalDate.parse(checkOutDate);
		this.checkOutDate = checkOut;
		this.room = room;
		this.numOfGuest = numOfGuest;
	}

	public Booking() {

	}

	private void computeBill() {
		long stayingDay = ChronoUnit.DAYS.between(this.checkInDate, this.checkOutDate);
		double computedBill = room.getRate() * stayingDay;
	}

	@Override
	public boolean validatePolicy() {
		boolean isRefundable;
		LocalDate now = LocalDate.now();
		long difference = ChronoUnit.DAYS.between(now, this.checkInDate);
		int minRefundDay = 3;
		if (difference >= minRefundDay)
			isRefundable = true;
		else
			isRefundable = false;
		return isRefundable;
	}

	@Override
	public void makePayment(PaymentMethod paymentMethod) {
		payment.setPaymentMethod(paymentMethod);
	}

	@Override
	public void makePayment(PaymentMethod paymentMethod, int cardNumber) {
		payment.setPaymentMethod(paymentMethod);
		payment.setcardNumber(cardNumber);
	}

	@Override
	public void setBookingID(int bookingID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCheckInDate(String checkInString) {
		LocalDate checkInDate;
		try {
			checkInDate = LocalDate.parse(checkInString);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Incorrect date format!");
		}
		this.checkInDate = checkInDate;
	}

	@Override
	public void setCheckOutDate(String checkOutString) {
		LocalDate checkOutDate;
		try {
			checkOutDate = LocalDate.parse(checkOutString);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Incorrect date format!");
		}
		this.checkOutDate = checkOutDate;
	}

	@Override
	public void setClientProfile(ClientProfile clientProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public void setNumOfGuest(int numOfGuest) {
		this.numOfGuest = numOfGuest;
	}

	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTotalPrice(double totalPrice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCardNumber(int cardNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int getBookingID() {
		return bookingID;
	}

	@Override
	public ClientProfile getClientProfile() {
		return client;
	}

	@Override
	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	@Override
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	@Override
	public Room getRoom() {
		return room;
	}

	@Override
	public int getNumOfGuest() {
		return numOfGuest;
	}

	@Override
	public Payment getPayment() {
		return payment;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public double getTotalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		// TODO Auto-generated method stub
		return null;
	}
}

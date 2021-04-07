package hotel.domain.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import hotel.domain.IEntity.IBooking;

public class Booking implements IBooking {
	private int bookingID;
	private ClientProfile client;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Room room;
	private int numOfGuest;
	private Payment payment;
	private Status status;

	public Booking() {
		this.payment = new Payment();
	}

	public Booking(int bookingID, ClientProfile client, LocalDate checkInDate, LocalDate checkOutDate, Room room,
			int numOfGuest, Payment payment, Status status) {
		this.bookingID = bookingID;
		this.client = client;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		this.numOfGuest = numOfGuest;
		this.payment = payment;
		this.status = status;
	}

	private void computeTotalPrice() {
		long stayingDay = ChronoUnit.DAYS.between(this.checkInDate, this.checkOutDate);
		double computedBill = room.getRate() * stayingDay;
		payment.setTotalPrice(computedBill);
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
	public void makePayment(PaymentMethod paymentMethod, String cardNumber) {
		payment.setPaymentMethod(paymentMethod);
		payment.setcardNumber(cardNumber);
	}

	@Override
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	@Override
	public void setCheckInDate(String checkInString) {
		LocalDate checkInDate;
		LocalDate today = LocalDate.now();

		try {
			checkInDate = LocalDate.parse(checkInString);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Incorrect date format!");
		}
		if (checkInDate.isBefore(today)) {
			throw new IllegalArgumentException("You cannot make a booking for the past!");
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
		if (checkOutDate.isBefore(this.checkInDate)) {
			throw new IllegalArgumentException("You cannot checkout before you check in!");
		}else if (checkOutDate.equals(checkInDate)) {
			throw new IllegalArgumentException("You cannot checkout on the day you checked in!");
		}
		this.checkOutDate = checkOutDate;
	}

	@Override
	public void setClientProfile(ClientProfile clientProfile) {
		this.client = clientProfile;
	}

	@Override
	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public void setNumOfGuest(int numOfGuest) {
		if (numOfGuest < 0 || numOfGuest > 4)
			throw new IllegalArgumentException("Number of guest should in the range of 1-4.");
		else
			this.numOfGuest = numOfGuest;
	}

	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.payment.setPaymentMethod(paymentMethod);
	}

	@Override
	public void setTotalPrice(double totalPrice) {
		this.payment.setTotalPrice(totalPrice);
	}

	@Override
	public void setCardNumber(String cardNumber) {
		this.payment.setcardNumber(cardNumber);
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
		computeTotalPrice();
		return this.payment.getTotalPrice();
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		return this.payment.getPaymentMethod();
	}
}

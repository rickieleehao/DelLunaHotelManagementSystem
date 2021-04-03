package hotel_entity;

import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import hotel_interface.IBooking;

public class Booking implements IBooking {
	private int bookingID;
	private ClientProfile client;
	private Date checkInDate;
	private Date checkOutDate;
	private Room room;
	private int numOfGuest;
	private Payment payment;
	private Status status;

	public Booking(int bookingID, ClientProfile client, Date checkInDate, Date checkOutDate, Room room,
			int numOfGuest) {
		this.bookingID = bookingID;
		this.client = client;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		this.numOfGuest = numOfGuest;
	}

	public Booking() {

	}

	private void computeBill() {
		long timeDifference = checkOutDate.getTime() - checkInDate.getTime();
		long dayDifference = TimeUnit.MILLISECONDS.toDays(timeDifference) % 365;
		double computedBill = room.getRate() * dayDifference;
		// payment.setTotalPrice(computedBill);
		// missing setTotalPrice in payment even after I pull and refresh
	}

	@Override
	public boolean validatePolicy(Date dateToday) {
		boolean isRefundable;
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkInDate);
		cal.add(Calendar.DATE, -3);
		Date minRefundDate = cal.getTime();
		if (dateToday.before(minRefundDate))
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
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	@Override
	public void setCheckOutDate(Date checkOutDate) {
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
	public Date getCheckInDate() {
		return checkInDate;
	}

	@Override
	public Date getCheckOutDate() {
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
}

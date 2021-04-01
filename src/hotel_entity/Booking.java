package hotel_entity;

import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Booking {
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

	public void computeBill() {
		long timeDifference = checkOutDate.getTime() - checkInDate.getTime();
		long dayDifference = TimeUnit.MILLISECONDS.toDays(timeDifference)% 365;
		double computedBill = room.getRate() * dayDifference * room.getDiscount();
		//payment.setTotalPrice(computedBill);
		//missing setTotalPrice in payment even after I pull and refresh
	}

	public void makePayment(PaymentMethod paymentMethod) {
		payment.setPaymentMethod(paymentMethod);
	}

	public void makePayment(PaymentMethod paymentMethod, int cardNumber) {
		payment.setPaymentMethod(paymentMethod);
		payment.setcardNumber(cardNumber);
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setNumOfGuest(int numOfGuest) {
		this.numOfGuest = numOfGuest;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getBookingID() {
		return bookingID;
	}

	public ClientProfile getClientProfile() {
		return client;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public Room getRoom() {
		return room;
	}

	public int getNumOfGuest() {
		return numOfGuest;
	}

	public Payment getPayment() {
		return payment;
	}

	public Status getStatus() {
		return status;
	}

	public double getBill() {
		//double bill = payment.getTotalPrice();
		//missing getTotalPrice in payment even after I pull and refresh
		return 0;
	}
}

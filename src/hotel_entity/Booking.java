package hotel_entity;

import java.util.Date;

public class Booking { // hy
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

	}
	
	public boolean validatePolicy(Date dateToday) {//yy
		return true;
	}
	
	public void computeBill() {
		
	}

	public void makePayment(PaymentMethod paymentMethod) {
		
	}

	public void makePayment(PaymentMethod paymentMethod, int cardNumber) {
		
	}

	public void setCheckInDate(Date checkInDate) {

	}

	public void setCheckOutDate(Date checkOutDate) {

	}

	public void setRoom(Room room) {

	}

	public void setNumOfGuest(int numOfGuest) {

	}

	public void setStatus(Status status) {

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
		return 0;
	}
}

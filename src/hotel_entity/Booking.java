package hotel_entity;

import java.util.Date;

public class Booking {
	private int bookingID;
	private ClientProfile client;
	private Date checkInDate;
	private Date checkOutDate;
	private Room room;
	private int numOfGuest;
	private Payment payment;
	private BookingStatus status;
	
	public Booking(int bookingID, ClientProfile client, Date checkInDate, Date checkOutDate, Room room, int numOfGuest) {
		
	}
	
	public boolean validatePolicy(Date dateToday) {//yy
		return true;
	}
	
	public void setCheckInDate(Date checkInDate) {
		
	}
	public void setCheckOutDate(Date checkOutDate) {
		
	}
	public void setRoom(Room room) {
		
	}
	public void setNumOfGuest(int numOfGuest) {
		
	}
	public void setStatus(BookingStatus status) {
		
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
	public BookingStatus getStatus() {
		return status;
	}
}

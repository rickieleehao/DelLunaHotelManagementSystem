package hotel_interface;

import java.time.LocalDate;

import hotel_entity.ClientProfile;
import hotel_entity.Payment;
import hotel_entity.PaymentMethod;
import hotel_entity.Room;
import hotel_entity.Status;

public interface IBooking {

	public boolean validatePolicy();
	public void makePayment(PaymentMethod paymentMethod);
	public void makePayment(PaymentMethod paymentMethod, int cardNumber);
	
	//set method
	public void setBookingID(int bookingID);
	public void setClientProfile(ClientProfile clientProfile);
	public void setCheckInDate(String checkInDate);
	public void setCheckOutDate(String checkOutDate);
	public void setRoom(Room room);
	public void setNumOfGuest(int numOfGuest);
	public void setPaymentMethod(PaymentMethod paymentMethod);
	public void setTotalPrice(double totalPrice);
	public void setStatus (Status status);
	public void setCardNumber(int cardNumber);
	
	//get method
	public int getBookingID();
	public ClientProfile getClientProfile();
	public LocalDate getCheckInDate();
	public LocalDate getCheckOutDate();
	public Room getRoom();
	public int getNumOfGuest();
	public Payment getPayment();
	public Status getStatus();
	public double getTotalPrice();
	public PaymentMethod getPaymentMethod();
}

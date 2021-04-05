package hotel_interface;

import java.util.Date;

import hotel_entity.ClientProfile;
import hotel_entity.Payment;
import hotel_entity.PaymentMethod;
import hotel_entity.Room;
import hotel_entity.Status;

public interface IBooking {

	public boolean validatePolicy(Date dateToday);
	public void makePayment(PaymentMethod paymentMethod);
	public void makePayment(PaymentMethod paymentMethod, int cardNumber);
	
	//set method
	public void setBookingID(int bookingID);
	public void setClientProfile(ClientProfile clientProfile);
	public void setCheckInDate(Date checkInDate);
	public void setCheckOutDate(Date checkOutDate);
	public void setRoom(int room);
	public void setNumOfGuest(int numOfGuest);
	public void setPaymentMethod(PaymentMethod paymentMethod);
	public void setTotalPrice(double totalPrice);
	public void setStatus (Status status);
	public void setCardNumber(int cardNumber);
	
	//get method
	public int getBookingID();
	public ClientProfile getClientProfile();
	public Date getCheckInDate();
	public Date getCheckOutDate();
	public Room getRoom();
	public int getNumOfGuest();
	public Payment getPayment();
	public Status getStatus();
	public double getTotalPrice();
	public PaymentMethod getPaymentMethod();
}

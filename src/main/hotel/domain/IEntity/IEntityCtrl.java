package hotel.domain.IEntity;
import java.util.Date;

import hotel.domain.entity.*;
public interface IEntityCtrl{
	
	//Booking class
	public int getBookingID(Booking theBooking);
	public ClientProfile getClientProfile(Booking theBooking);
	public Date getCheckInDate(Booking theBooking);
	public Date getCheckOutDate (Booking theBooking);
	public Room getRoom(Booking theBooking);
	public int getNumOfGuest(Booking theBooking);
	public Payment getPayment(Booking theBooking);
	public Status getStatus(Booking theBooking);
	public double getBill(Booking theBooking);
	
	//ClientProfile class
	public String getNRIC(ClientProfile clientProfile);
	public String getFirstName (ClientProfile clientProfile);
	public String getLastName(ClientProfile clientProfile);
	public Gender getGender (ClientProfile clientProfile);
	public String getAddress(ClientProfile clientProfile);
}

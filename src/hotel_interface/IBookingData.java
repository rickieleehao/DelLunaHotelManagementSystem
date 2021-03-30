package hotel_interface;

import java.util.Date;
import java.util.List;

import hotel_entity.*;

public interface IBookingData {

	public List<Room> findAvailableRoom(Date checkInDate, Date checkOutDate);

	public int generateBookingID();

	public void addBooking(Booking newBooking);

	public void updateBooking(Booking theBooking, Date checkInDate, Date checkOutDate, Room room, int numOfGuest);

	public void updateBooking(Booking theBooking, Room room);

	public void updateBooking(Booking theBooking, int numOfGuest);

	public void cancelBooking(Booking theBooking);

	public Booking getBooking(int bookingID);

	public void checkIn(Booking theBooking);

	public void checkOut(Booking theBooking);

	public double getBill(Booking theBooking);
	
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod);
	
	public void makePayment(Booking theBooking, PaymentMethod paymentMethod, int cardNumber);

}

package hotel_interface;

public interface IBookingData {

	public void addBooking(Booking newBooking);
	
	public void cancelBooking (Booking theBooking);
	
	public void checkIn (Booking theBooking);
	
	
	public void checkOut (Booking theBooking);
	
	public Booking createBooking (int bookingID, ClientProfile clientProfile, 
					Date checkInDate, Date checkOutDate, Room room, int numOfGuest);
	
	public void findAvailableRoom (Date checkInDate, Date checkOutDate);
	
	public Booking generateBookingID () ;
	
	public Booking getBooking (Booking theBooking);
	
	public void updateBooking (Booking theBooking, Date checkInDate, Date checkOutDate,
			Room room, int numOfGuest);
	
	public void uodateBooking (Booking theBooking, Room room);
	
	public void uodateBooking (Booking theBooking, int numOfGuest);
	
}

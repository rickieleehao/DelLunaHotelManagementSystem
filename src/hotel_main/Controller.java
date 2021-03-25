package hotel_main;

public class Controller {
	
	private IBookingData bookingList;
	private IClientData clientProfileList; 
	private IUser user;
	
	public Controller(IBokingData bookingList,IClientData clientProfileList,IUser user) {
		
		this.bookingList=bookingList;
		this.clientProfileList=clientProfileList;
		this.user=user;
	}
	
	public void addBooking(Booking newBooking) {
		
	}
	
	public void cancelBooking(Booking theBooking) {
		
	}
	
	public void checkIn(Booking theBooking) {
		
	}
	
	public void checkOut(Booking theBooking) {
		
	}
	
	public Booking createBooking(int bookingID,ClientProfile clientProfile,Date checkInDate, Data checkOutDate,Room room,int numOfGuest) {
		
	}
	
	public ClientProfile createClientProfile(String NRIC,String firstName,String lastName,Gender gender,String address) {
		
	}
	
	public List<Room> findAvailableRoom(Date checkInDate,Date checkOutDate){
		
	}
	
	public int generateBookingID() {
		
	}
	
	public Booking getBooking(Booking theBooking) {
		
	}
	
	public int getCardNumber(Booking theBooking) {
		
	}
	
	public Date getCheckInDate(Booking theBooking) {
		
	}
	
	public void getClientProfile(String NRIC) {
		
	}
	
	public double getDeposit(Booking theBooking) {
		
	}
	
	public PaymentMethod getPaymentMethod(Booking theBooking) {
		
	}
	
	public Booking searchBooking(Booking theBooking) {
		
	}
	
	public boolean searchClientProfile(String NRIC) {
		
	}
	
	public void updateBooking(Booking theBooking,Room room) {
		
	}
	
	public void updateBooking(Booking theBooking,int numOfGuest) {
		
	}
	
	public void updateBooking(Booking theBooking,Date checkInDate,Date checkOutDate,Room room,int numOfGuest) {
		
	}
	
	public boolean validatePolicy(Booking theBooking,Date dateToday) {
		
	}	

}

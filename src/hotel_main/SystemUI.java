package hotel_main;
import hotel_entity.*;
import java.util.*;


public class SystemUI {
	private Scanner scanner;
	private Controller controller;
	
	public SystemUI(Controller controller, Scanner scanner) {
		this.controller = controller;
		this.scanner = scanner;
	}
	
	public void start() {
		/*
		 * displayMenu
		 * 
		 * choice
		 * 
		 * switch(choice)
		 * 
		 * case 1: createBooking();
		 * ..
		 * ..
		 * ...
		 * 
		 */
	}
	
	public void login() {
		
	}
	
	public void createBooking() { //xz
		int bookingID = controller.generateBookingID();
		System.out.print("Enter NRIC ----> ");
		String NRIC = scanner.nextLine();
		boolean isProfileFound = controller.searchClientProfile(NRIC);
	}
	
	public void updateBooking() { //hy
	}
	
	public void checkIn() { //april
		
		System.out.print("Enter booking ID ----> ");
		int bookingID= scanner.nextInt();
		
		Booking theBooking= controller.searchBooking(bookingID);
		if(theBooking==null) 
			System.out.println("Booking not found.");
		else
			System.out.println();
			controller.checkIn(theBooking);
		
	}
	
	public void checkOut() { //april
		System.out.print("Enter booking ID ----> ");
		int bookingID= scanner.nextInt();
		
		Booking theBooking= controller.searchBooking(bookingID);
		if(theBooking==null) 
			System.out.println("Booking not found.");
		else
		{
			System.out.println();
			makePayment(theBooking);
			controller.checkOut(theBooking);
		}
	}
	
	public void searchBooking() { // april
		
		System.out.print("Enter booking ID ----> ");
		int bookingID= scanner.nextInt();
		
		Booking theBooking= controller.searchBooking(bookingID);
		if(theBooking==null) 
			System.out.println("Booking not found.");
		else
			System.out.println(""); //view booking
		
		System.out.println();
		
	}
	
	public void displayMenu() { //R
		
	}
	
	public void displayClientInfo(ClientProfile clientProfile) { //xz
		
	}
	
	public void displayAvailableRoom(List<Room> availableRoomList) { // yy
		
	}
	
	public ClientProfile createClientProfile(String NRIC) { // yy
		return null;
	}
	
	public void changeBookingDets(Booking theBooking) { //hy
		
	}
	
	public void cancelBooking(Booking theBooking) { //yy
		
	}
	
	public void makePayment(Booking theBooking) { // tbc
		
	}
	
	
}

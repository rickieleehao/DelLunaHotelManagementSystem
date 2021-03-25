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
		
	}
	
	public void checkOut() { //april
		
	}
	
	public void searchBooking() { // april
		
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

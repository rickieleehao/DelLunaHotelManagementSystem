package hotel_main;
import hotel_entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public void createBooking() throws ParseException { //xz
		
		int bookingID = controller.generateBookingID();
		System.out.print("Enter NRIC ----> ");
		String NRIC = scanner.nextLine();
		
		boolean isProfileFound = controller.searchClientProfile(NRIC);
		
		String skip;
		ClientProfile clientProfile;
		
		if(isProfileFound==true) {	
			
			 clientProfile= controller.getClientProfile(NRIC);	
					
		}
		
		else {
			
			 clientProfile = createClientProfile(NRIC);
		}
		
		displayClientInfo(clientProfile);
		
		System.out.print("Enter Check In Date (DD/MM/YYYY) ----> ");
		String checkInDateString = scanner.nextLine();
		
		System.out.print("Enter Check Out Date (DD/MM/YYYY) ----> ");
		String checkOutDateString = scanner.nextLine();
		
		Date checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDateString);		
		Date checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateString);
		
		List<Room> availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);
				
		displayAvailableRoom(availableRoomList);
		
		System.out.print("Select available room by enter room number ----> ");
		int roomNo = scanner.nextInt();		
		//clear enter key
		skip= scanner.nextLine();
		Room room = availableRoomList.get(roomNo-1);
		
		System.out.print("Enter number of guest ----> ");
		int numOfGuest = scanner.nextInt();
		//clear enter key
		skip= scanner.nextLine();
		
		Booking newBooking = controller.createBooking(bookingID,clientProfile,checkInDate,checkOutDate,room,numOfGuest);
		
		controller.addBooking(newBooking);
		
		System.out.println(" Your booking has been created successful ");
						
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
		
		System.out.println();
		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :"+clientProfile.getFirstName());
		System.out.println("Last Name   :"+clientProfile.getLastName());
		System.out.println("NRIC        :"+clientProfile.getNRIC());
		System.out.println("Gender      :"+clientProfile.getGender());
		System.out.println("Address     :"+clientProfile.getAddress());
		System.out.println();
		
		
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

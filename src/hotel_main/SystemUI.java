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
	
	public void updateBooking() {//hy
		int choice;
		do {
			Booking theBooking = searchBooking();
			System.out.println("Update Booking");
			System.out.println("--------------");
			System.out.println("1. Change booking detail.");
			System.out.println("2. Cancel booking.");
			System.out.println("3. Exit.");
			System.out.print("Enter number ----> ");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1:
				changeBookingDets(theBooking);
				break;
			case 2: 
				cancelBooking(theBooking);
				break;
			}
		}while(choice != 3);
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
		int choice, roomNo;
		Date checkInDate, checkOutDate;
		List<Room> availableRoomList;
		Room room;
		do {
			System.out.println("Change Booking Detail");
			System.out.println("---------------------");
			System.out.println("1. Change check-in date & check-out date.");
			System.out.println("2. Change room.");
			System.out.println("3. Change number of guest.");
			System.out.println("4. Exit.");
			System.out.print("Enter number ----> ");
			choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1:
				System.out.print("Enter new check-in date (DD/MM/YYYY) ----> ");
				String checkInDateString = scanner.nextLine();
				System.out.print("Enter new check-out date (DD/MM/YYYY) ----> ");
				String checkOutDateString = scanner.nextLine();
				checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDateString);
				checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateString);
				availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);
				for(int i = 0; i < availableRoomList.size(); i++) {
					Room arl = availableRoomList.get(i);
					System.out.println("");
				}
				System.out.print("Enter new room ----> ");
				roomNo = scanner.nextInt();
				scanner.nextLine();
				room = availableRoomList.get(roomNo - 1);
				int numOfGuest = scanner.nextInt();
				scanner.nextLine();
				controller.updateBooking(theBooking, checkInDate, checkOutDate, room, numOfGuest);
				break;
			case 2: 
				checkInDate = controller.getCheckInDate(theBooking);
				checkOutDate = controller.getCheckOutDate(theBooking);
				availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);
				for(int i = 0; i < availableRoomList.size(); i++) {
					Room arl = availableRoomList.get(i);
					System.out.println("");
				}
				System.out.print("Enter new room ----> ");
				roomNo = scanner.nextInt();
				scanner.nextLine();
				room = availableRoomList.get(roomNo - 1);
				//not complete
				break;
			}
		}while(choice != 4);
	}
	
	public void cancelBooking(Booking theBooking) { //yy
		
	}
	
	public void makePayment(Booking theBooking) { // tbc
		
	}
	
	
}

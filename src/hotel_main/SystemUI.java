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

		boolean exit = false;
		do {
			UserType userType = controller.getUserType();
			System.out.println("This is the Menu.");
			int choice;
			if (userType == UserType.Client) {
				displayClientMenu();
			} else if (userType == UserType.Administrator) {
				System.out.println("1. Create Booking");
				System.out.println("2. Update Booking");
				System.out.println("3. Search Booking");
				System.out.println("4. Check-in");
				System.out.println("5. Check-out");
				System.out.println("6. Exit");

				System.out.print("Pick option (1-6) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();

				while (choice < 1 && choice > 6) {
					System.out.print("Invalid option. ");
					System.out.print("Pick option (1-6) ----> ");
					choice = scanner.nextInt();
					scanner.nextLine();
				}

				switch (choice) {
				case 1:
					createBooking();
					break;
				case 2:
					updateBooking();
					break;
				case 3:
					searchBooking();
					break;
				case 4:
					checkIn();
					break;
				case 5:
					checkOut();
					break;
				case 6:
					exit = true;
					break;
				}
			}
		} while (!exit);
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
		
		
		
		
	}
	
	public void displayAvailableRoom(List<Room> availableRoomList) { // yy

		
	}

	private boolean displayClientMenu() { // R
		boolean isExit = false;
		int choice;

		System.out.println("1. Login");
		System.out.println("2. Search Booking");
		System.out.println("3. Exit");

		System.out.print("Pick option (1-2) ----> ");
		choice = scanner.nextInt();
		scanner.nextLine();

		while (choice != 1 && choice != 2) {
			System.out.print("Invalid option. ");
			System.out.print("Pick option (1-2) ----> ");
			choice = scanner.nextInt();
			scanner.nextLine();
		}

		switch (choice) {
		case 1:
			login();
			break;
		case 2:
			searchBooking();
			break;
		case 3:
			isExit = true;
			break;
		}

		return isExit;
	}

	private void displayAdminMenu() {

	}

	private void displayClientInfo(ClientProfile clientProfile) { // xz

		System.out.println();

		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :"+clientProfile.getFirstName());
		System.out.println("Last Name   :"+clientProfile.getLastName());
		System.out.println("NRIC        :"+clientProfile.getNRIC());
		System.out.println("Gender      :"+clientProfile.getGender());
		System.out.println("Address     :"+clientProfile.getAddress());
		System.out.println();
	}

	private void displayAvailableRoom(List<Room> availableRoomList) { // yy
	}

	private ClientProfile createClientProfile(String NRIC) { // yy
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

	private void cancelBooking(Booking theBooking) { // yy

	}

	private void makePayment(Booking theBooking) { // tbc

	}
}

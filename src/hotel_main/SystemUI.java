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

	public void start(){

		boolean exit = false;
		do {
			UserType userType = controller.getUserType();
			System.out.println("This is the Menu.");
			int choice;
			if (userType == UserType.Client) {
				clientMenu();
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

	private void login() {

	}

	private void createBooking() { // xz

		int bookingID = controller.generateBookingID();
		System.out.print("Enter NRIC ----> ");
		String NRIC = scanner.nextLine();

		boolean isProfileFound = controller.searchClientProfile(NRIC);

		String skip;
		ClientProfile clientProfile;
		Date checkInDate = new Date();
		Date checkOutDate = new Date();

		if (isProfileFound == true) {

			clientProfile = controller.getClientProfile(NRIC);

		}

		else {

			clientProfile = createClientProfile(NRIC);
		}

		printClientProfileDets(clientProfile);
		boolean isDate;
		do {
			System.out.print("Enter Check In Date (DD/MM/YYYY) ----> ");
			String checkInDateString = scanner.nextLine();

			System.out.print("Enter Check Out Date (DD/MM/YYYY) ----> ");
			String checkOutDateString = scanner.nextLine();

			try {
				checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDateString);
				checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateString);
				isDate = true;
			} catch (ParseException e) {
				System.out.println("invliad date");
				isDate = false;
			}
		} while (!isDate);

		List<Room> availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);

		printRoomList(availableRoomList);

		System.out.print("Select available room by enter room number ----> ");
		int roomNo = scanner.nextInt();
		// clear enter key
		skip = scanner.nextLine();
		Room room = availableRoomList.get(roomNo - 1);

		System.out.print("Enter number of guest ----> ");
		int numOfGuest = scanner.nextInt();
		// clear enter key
		skip = scanner.nextLine();

		Booking newBooking = controller.createBooking(bookingID, clientProfile, checkInDate, checkOutDate, room,
				numOfGuest);

		controller.addBooking(newBooking);

		System.out.println(" Your booking has been created successful ");

	}

	private void updateBooking() { // hy
		int choice;
		boolean exit = false;
		do {
			Booking theBooking = searchBooking();
			System.out.println("Update Booking");
			System.out.println("--------------");
			System.out.println("1. Change booking detail.");
			System.out.println("2. Cancel booking.");
			System.out.println("3. Exit.");
			System.out.print("Pick option (1-3) ----> ");
			choice = scanner.nextInt();
			scanner.nextLine();

			while (choice < 1 || choice > 3) {
				System.out.println("Invalid oprion.");
				System.out.print("Pick option (1-3) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
			}

			switch (choice) {
			case 1:
				changeBookingDets(theBooking);
				break;
			case 2:
				cancelBooking(theBooking);
				break;
			case 3:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private void checkIn() { // april

		System.out.print("Enter booking ID ----> ");
		int bookingID = scanner.nextInt();

		Booking theBooking = controller.searchBooking(bookingID);
		if (theBooking == null)
			System.out.println("Booking not found.");
		else
			System.out.println();
		controller.checkIn(theBooking);

	}

	private void checkOut() { // april
		System.out.print("Enter booking ID ----> ");
		int bookingID = scanner.nextInt();

		Booking theBooking = controller.searchBooking(bookingID);
		if (theBooking == null)
			System.out.println("Booking not found.");
		else {
			System.out.println();
			makePayment(theBooking);
			controller.checkOut(theBooking);
		}
	}

	private Booking searchBooking() { // april

		System.out.print("Enter booking ID ----> ");
		int bookingID = scanner.nextInt();

		Booking theBooking = controller.searchBooking(bookingID);
		if (theBooking == null)
			System.out.println("Booking not found.");
		else
			System.out.println(""); // view booking
		return theBooking;
	}

	private ClientProfile createClientProfile(String NRIC) { // yy
	
		String firstName;
		String lastName;
		String temp;
		Gender gender = null;
		String address;
	
		System.out.print("Enter client's first name ----> ");
		firstName = scanner.nextLine();
	
		System.out.print("Enter client's last name ----> ");
		lastName = scanner.nextLine();
	
		System.out.print("Enter client's gender(M/F) ----> ");
		temp = scanner.nextLine();
		if (temp.toLowerCase().equals("m")) {
			gender = Gender.Male;
		} else if (temp.toLowerCase().equals("f")) {
			gender = Gender.Female;
		}
	
		System.out.print("Enter client's address ----> ");
		address = scanner.nextLine();
		ClientProfile clientProfile = controller.createClientProfile(NRIC, firstName, lastName, gender, address);
		System.out.println("Client's profile is created.");
		return clientProfile;
	}

	private void changeBookingDets(Booking theBooking) { // hy
		int choice, roomNo, numOfGuest;
		boolean isDate, exit = false;
		Date checkInDate = null, checkOutDate = null;
		List<Room> availableRoomList;
		Room room;
		do {
			System.out.println("Change Booking Detail");
			System.out.println("---------------------");
			System.out.println("1. Change check-in date & check-out date.");
			System.out.println("2. Change room.");
			System.out.println("3. Change number of guest.");
			System.out.println("4. Exit.");
			System.out.print("Pick option (1-4) ----> ");
			choice = scanner.nextInt();
			scanner.nextLine();
	
			while (choice < 1 || choice > 4) {
				System.out.println("Invalid option.");
				System.out.print("Pick option (1-4) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
			}
	
			switch (choice) {
			case 1:
				do {
					System.out.print("Enter new check-in date (DD/MM/YYYY) ----> ");
					String checkInDateString = scanner.nextLine();
					System.out.print("Enter new check-out date (DD/MM/YYYY) ----> ");
					String checkOutDateString = scanner.nextLine();
					try {
						checkInDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkInDateString);
						checkOutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkOutDateString);
						isDate = true;
					} catch (ParseException pex) {
						System.out.println("Invalid date format.");
						isDate = false;
					}
				} while (!isDate);
				availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);
				printRoomList(availableRoomList);
				System.out.print("Enter new room ----> ");
				roomNo = scanner.nextInt();
				scanner.nextLine();
				room = availableRoomList.get(roomNo - 1);
				numOfGuest = scanner.nextInt();
				scanner.nextLine();
				controller.updateBooking(theBooking, checkInDate, checkOutDate, room, numOfGuest);
				break;
			case 2:
				checkInDate = controller.getCheckInDate(theBooking);
				checkOutDate = controller.getCheckOutDate(theBooking);
				availableRoomList = controller.findAvailableRoom(checkInDate, checkOutDate);
				printRoomList(availableRoomList);
				System.out.print("Enter new room ----> ");
				roomNo = scanner.nextInt();
				scanner.nextLine();
				room = availableRoomList.get(roomNo - 1);
				controller.updateBooking(theBooking, room);
				break;
			case 3:
				System.out.print("Enter new number of guest ----> ");
				numOfGuest = scanner.nextInt();
				scanner.nextLine();
				controller.updateBooking(theBooking, numOfGuest);
				break;
			case 4:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private void cancelBooking(Booking theBooking) { // yy
		Calendar calendar = Calendar.getInstance();
		Date dateToday = calendar.getTime();
		boolean isRefundable = theBooking.validatePolicy(dateToday);
		if (isRefundable) {
			System.out.println("The deposit is refundable");
			double deposit = controller.getDeposit(theBooking);
			System.out.println("Deposit amount ----> " + deposit);
			PaymentMethod paymentMethod = controller.getPaymentMethod(theBooking);
			if (paymentMethod == PaymentMethod.CreditCard) {
				System.out.println("Payment Method ----> Credit Card");
				int cardNumber = controller.getCardNumber(theBooking);
				System.out.println("Card Number ----> " + cardNumber);
			} else {
				System.out.println("Payment Method ----> Cash");
			}
		}
		controller.cancelBooking(theBooking);
		System.out.println("The booking is cancelled.");
	}

	private void viewBooking(Booking theBooking) {
		
	}

	private void makePayment(Booking theBooking) { // tbc
	
	}

	private void printClientProfileDets(ClientProfile clientProfile) { // xz

		System.out.println();

		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :" + clientProfile.getFirstName());
		System.out.println("Last Name   :" + clientProfile.getLastName());
		System.out.println("NRIC        :" + clientProfile.getNRIC());
		System.out.println("Gender      :" + clientProfile.getGender());
		System.out.println("Address     :" + clientProfile.getAddress());
		System.out.println();
	}
	
	private void printRoomList(List<Room> availableRoomList) { // yy
		System.out.printf("%-9s", "Room ID");
		System.out.printf("%-8s", "Rates");
		System.out.printf("%-10s", "Discount");
		System.out.printf("%-16s", "Number of Beds");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < availableRoomList.size(); i++) {
			System.out.printf("%-9d", availableRoomList.get(i).getRoomID());
			System.out.printf("%-8.2f", availableRoomList.get(i).getRate());
			System.out.printf("%-10.2f", availableRoomList.get(i).getDiscount());
			System.out.printf("%-16d", availableRoomList.get(i).getNumOfBed());
			System.out.println("");
		}
		System.out.println(""); // view booking
	
	}

	private void printBookingDets(UserType userType, Booking theBooking) {
		
	}
	
	private void printReceipt(Booking theBooking) {
		
	}

	private boolean clientMenu() { // R
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

	private void adminMenu() {
	
	}
}

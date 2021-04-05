package hotel_main;

import hotel_entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
		while (!exit) {
			UserType userType = controller.getUserType();
			System.out.println("This is the Menu.");
			if (userType == UserType.Client) {
				exit = clientMenu();
			} else if (userType == UserType.Administrator) {
				exit = adminMenu();
			}
		}
	}

	private void login() {
		System.out.print("Enter the password ----> ");
		String password = scanner.nextLine();
		try {
			this.controller.login(password);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createBooking() {

		controller.createBooking();
		controller.setBookingID();

		String NRIC = null;
		boolean loop = true;
		while (loop) {
			try {
				System.out.print("Enter NRIC ----> ");
				NRIC = scanner.nextLine();
				controller.setClientProfile(NRIC);
				loop = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				ClientProfile clientProfile = createClientProfile(NRIC);
				controller.setClientProfile(clientProfile);
				loop = false;
			}
		}

		printClientProfileDets();
		String checkInString = null;
		String checkOutString = null;
		loop = true;
		while (loop) {
			try {
				System.out.print("Enter Check In Date (YYYY-MM-DD) ----> ");
				checkInString = scanner.nextLine();
				controller.setCheckInDate(checkInString);
				loop = false;
			} catch (DateTimeParseException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		while (loop) {
			try {
				System.out.print("Enter Check Out Date (YYYY-MM-DD) ----> ");
				checkOutString = scanner.nextLine();
				controller.setCheckOutDate(checkOutString);
				loop = false;
			} catch (DateTimeParseException e) {
				System.out.println(e.getMessage());
			}
		}

		this.controller.setAvailableRoom(checkInString, checkOutString);
		printRoomList();

		loop = true;
		Room selectedRoom;
		while (loop) {
			try {
				System.out.print("Select available room by enter room number ----> ");
				int option = scanner.nextInt();
				scanner.nextLine();
				selectedRoom = controller.getRoom(option);
				controller.setRoom(selectedRoom);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		while (loop) {
			try {
				System.out.print("Enter number of guest ---->");
				int numOfGuest = scanner.nextInt();
				scanner.nextLine();
				controller.setNumOfGuest(numOfGuest);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		PaymentMethod paymentMethod = PaymentMethod.Cash;
		while (loop) {
			try {
				paymentMethod.printPaymentMethodOption();
				int option = scanner.nextInt();
				scanner.nextLine();
				paymentMethod.selectPaymentMethod(option);
				controller.setPaymentMethod(paymentMethod);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		loop = true;
		if (paymentMethod == PaymentMethod.CreditCard) {
			while (loop) {
				try {
					System.out.println("Enter card number (12digits)---->");
					int cardNumber = scanner.nextInt();
					scanner.nextLine();
					controller.setCardNumber(cardNumber);
					loop = false;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		controller.addBooking();
	}

	private void updateBooking() {
		int choice = 0;
		boolean exit = false;
		boolean loop = true;
		do {
			Booking theBooking = searchBooking();
			System.out.println("Update Booking");
			System.out.println("--------------");
			System.out.println("1. Change booking detail.");
			System.out.println("2. Cancel booking.");
			System.out.println("3. Exit.");
			while(loop) {
				try {
					System.out.print("Pick option (1-3) ----> ");
					choice = scanner.nextInt();
					scanner.nextLine();
					loop = false;
				}
				catch(InputMismatchException e){
					scanner.nextLine();
					System.out.println("Please enter number only.");
				}
			}
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

	private void checkIn() {
		Booking theBooking = searchBooking();
		if (theBooking == null)
			System.out.println("Booking not found.");
		else
			System.out.println();
		controller.setStatus(Status.CheckedIn);

	}

	private void checkOut() {
		Booking theBooking = searchBooking();
		if (theBooking == null)
			System.out.println("Booking not found.");
		else {
			System.out.println();
			makePayment(theBooking);
			controller.setStatus(Status.CheckedOut);
		}
	}

	private Booking searchBooking() {
		Booking theBooking = null;
		boolean error = true;
		while (error = true) {
			try {
				System.out.print("Enter booking ID ----> ");
				theBooking = controller.getBooking(scanner.nextInt());
				error = false;

			} catch (InputMismatchException ex) {
				System.out.println("Enter only integer.");
			} catch (IllegalArgumentException e) {
				System.out.println("Booking not Found.");
			}
		}

		controller.createBooking(theBooking);
		viewBooking(theBooking);
		return theBooking;
	}

	private ClientProfile createClientProfile(String NRIC) { // yy

		String firstName;
		String lastName;
		String temp;
		Gender gender = null;
		String address;

		try {
			System.out.print("Enter client's first name ----> ");
			firstName = scanner.nextLine();
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid input");
		}

		try {
			System.out.print("Enter client's last name ----> ");
			lastName = scanner.nextLine();
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid input");
		}

		try {
			gender.printGenderOption();
			System.out.println("Enter a number: ");
			int choice = scanner.nextInt();
			gender.selectGender(choice);
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid input");
		}

		try {
			System.out.print("Enter client's address ----> ");
			address = scanner.nextLine();
			ClientProfile clientProfile = controller.createClientProfile(NRIC, firstName, lastName, gender, address);
			System.out.println("Client's profile is created.");
			return clientProfile;
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid input");
		}
	}

	private void changeBookingDets(Booking theBooking) {
		int choice = 0, roomNo, numOfGuest;
		boolean exit= false;
		boolean loop = true;
		String checkInDateStr = null;
		String checkOutDateStr = null;
		Room room;
		do {
			System.out.println("Change Booking Detail");
			System.out.println("---------------------");
			System.out.println("1. Change check-in date & check-out date.");
			System.out.println("2. Change room.");
			System.out.println("3. Change number of guest.");
			System.out.println("4. Exit.");
			while(loop) {
				try {
					System.out.print("Pick option (1-4) ----> ");
					choice = scanner.nextInt();
					scanner.nextLine();
					loop = false;
				}
				catch(InputMismatchException e) {
					scanner.nextLine();
					System.out.println("Please enter number only.");
				}
			}
			while (choice < 1 || choice > 4) {
				System.out.println("Invalid option.");
				System.out.print("Pick option (1-4) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
			}

			switch (choice) {
			case 1:
				while (loop) {
					try {
						System.out.print("Enter Check-in date (YYYY-MM-DD) ----> ");
						checkInDateStr = scanner.nextLine();
						this.controller.setCheckInDate(checkInDateStr);
						loop = false;
					} catch (DateTimeParseException e) {
						System.out.println(e.getMessage());
					}
				}
				while (loop) {
					try {
						System.out.print("Enter check-out date (YYYY-MM-DD) ----> ");
						checkOutDateStr = scanner.nextLine();
						this.controller.setCheckOutDate(checkOutDateStr);
						loop = false;
					} catch (DateTimeParseException e) {
						System.out.println(e.getMessage());
					}
				}
				this.controller.setAvailableRoom(checkInDateStr, checkOutDateStr);
				printRoomList();
				while(loop) {
					try {
						System.out.print("Enter new room ----> ");
						roomNo = scanner.nextInt();
						scanner.nextLine();
						room = this.controller.getRoom(roomNo);
						this.controller.setRoom(room);
						loop = false;
					}
					catch(InputMismatchException e){
						scanner.nextLine();
						System.out.println("Please enter number only.");
					}
				}
				while(loop) {
					try {
						System.out.print("Enter new number of guest ----> ");
						numOfGuest = scanner.nextInt();
						scanner.nextLine();
						this.controller.setNumOfGuest(numOfGuest);
						loop = false;
					}
					catch(InputMismatchException e){
						scanner.nextLine();
						System.out.println("Please enter number only.");
					}
					catch(IllegalArgumentException e) {
						scanner.nextLine();
						System.out.println(e.getMessage());
					}
				}
				
				break;
			case 2:
				checkInDateStr = this.controller.getCheckInDate().toString();
				checkOutDateStr = this.controller.getCheckOutDate().toString();
				this.controller.setAvailableRoom(checkInDateStr, checkOutDateStr);
				printRoomList();
				while(loop) {
					try {
						System.out.print("Enter new room ----> ");
						roomNo = scanner.nextInt();
						scanner.nextLine();
						room = this.controller.getRoom(roomNo);
						this.controller.setRoom(room);
						loop = false;
					}
					catch(InputMismatchException e){
						scanner.nextLine();
						System.out.println("Please enter number only.");
					}
				}
				break;
			case 3:
				while(loop) {
					try {
						System.out.print("Enter new number of guest ----> ");
						numOfGuest = scanner.nextInt();
						scanner.nextLine();
						this.controller.setNumOfGuest(numOfGuest);
						loop = false;
					}
					catch(InputMismatchException e){
						scanner.nextLine();
						System.out.println("Please enter number only.");
					}
					catch(IllegalArgumentException e) {
						scanner.nextLine();
						System.out.println(e.getMessage());
					}
				}
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
		UserType userType = this.controller.getUserType();
		printBookingDets(userType, theBooking);
	}

	private void makePayment(Booking theBooking) { // tbc
		controller.getBill(theBooking);
		PaymentMethod paymentMethod = null;
		System.out.print("Do you want to pay by cash or credit card(cash/card)?: ");
		String choice = scanner.nextLine();
		System.out.println();
		if (choice.toLowerCase().equals("cash")) {
			paymentMethod = PaymentMethod.Cash;
			System.out.println("Pay by cash");
			controller.makePayment(theBooking, paymentMethod);
		} else {
			paymentMethod = PaymentMethod.CreditCard;
			System.out.println("Pay by credit card");
			try {
				System.out.print("Enter card number: ");
				int cardNumber = scanner.nextInt();
			} catch (IllegalArgumentException iae) {
				System.out.println("Invalid input");
			}
			controller.makePayment(theBooking, paymentMethod, cardNumber);
		}

		printReceipt(theBooking);
	}

	private void printClientProfileDets() {

		System.out.println();

		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :" + controller.getFirstName());
		System.out.println("Last Name   :" + controller.getLastName());
		System.out.println("NRIC        :" + controller.getNRIC());
		System.out.println("Gender      :" + controller.getGender());
		System.out.println("Address     :" + controller.getAddress());
		System.out.println();
	}

	private void printRoomList() { // yy
		List<Room> availableRoomList = this.controller.getAvailableRoomList();
		System.out.printf("%-9s", "Room ID");
		System.out.printf("%-8s", "Rates");
		System.out.printf("%-16s", "Number of Beds");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < availableRoomList.size(); i++) {
			System.out.printf("%-9d", this.controller.getRoomID(availableRoomList.get(i)));
			System.out.printf("%-8.2f", this.controller.getRoomRate(availableRoomList.get(i)));
			System.out.printf("%-16d", this.controller.getRoomNumOfBed(availableRoomList.get(i)));
			System.out.println("");
		}

		System.out.println(""); // view booking

	}

	private void printBookingDets(UserType userType, Booking theBooking) {
		if(userType == UserType.Administrator) {
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + theBooking.getBookingID());
			System.out.println("Customer: " + theBooking.getClientProfile().getFirstName() + " " + 
					theBooking.getClientProfile().getLastName());
			System.out.println("NRIC: " + theBooking.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + theBooking.getCheckInDate().toString());
			System.out.println("Check-out date: " + theBooking.getCheckOutDate().toString());
			System.out.println("Room: " + theBooking.getRoom().getRoomID());
			System.out.println("Number of guest: " + theBooking.getNumOfGuest());
			System.out.println("Booking status: " + theBooking.getStatus());
			System.out.println("Payment method: " + theBooking.getPayment().getPaymentMethod());
			System.out.println("Total price:" + theBooking.getPayment().getTotalPrice());
		}
		if(userType == UserType.Client) {
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + theBooking.getBookingID());
			System.out.println("Customer: " + theBooking.getClientProfile().getFirstName() + " " + 
					theBooking.getClientProfile().getLastName());
			System.out.println("NRIC: " + theBooking.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + theBooking.getCheckInDate().toString());
			System.out.println("Check-out date: " + theBooking.getCheckOutDate().toString());
			System.out.println("Room: " + theBooking.getRoom().getRoomID());
			System.out.println("Number of guest: " + theBooking.getNumOfGuest());
		}
	}

	private void printReceipt() {

		System.out.println("");
		System.out.println("     DELLUNA HOTEL     ");
		System.out.println("");
		System.out.println("       RECEIPT         ");
		System.out.println("-----------------------");
		System.out.println("    Booking Detail     ");
		System.out.println("-----------------------");
		System.out.println("Booking ID     : " + controller.getBookingID());
		System.out.println("First Name     : " + controller.getFirstName());
		System.out.println("Last Name      : " + controller.getLastName());
		System.out.println("Booking Status : " + controller.getStatus());
		System.out.println("-----------------------");
		System.out.println("    Payment Detail     ");
		System.out.println("-----------------------");
		System.out.println("Payment Method : " + controller.getPaymentMethod());
		System.out.println("     TOTAL     : " + controller.getTotalPrice());
		System.out.println("");
		System.out.println("-----------------------");
		System.out.println("       THANK YOU       ");
		System.out.println("-----------------------");
	}

	private boolean clientMenu() { // R
		int choice;
		boolean exit = false;
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
			exit = true;
			break;
		}
		return exit;
	}

	private boolean adminMenu() {
		int choice;
		boolean exit = false;
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

		return exit;
	}
}

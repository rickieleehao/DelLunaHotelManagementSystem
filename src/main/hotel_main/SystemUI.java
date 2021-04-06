package hotel_main;

import hotel_entity.*;

import java.nio.channels.IllegalSelectorException;
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

		this.controller.createBooking();
		this.controller.setBookingID();

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
				this.controller.setClientProfile(clientProfile);
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
				this.controller.setCheckInDate(checkInString);
				loop = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		while (loop) {
			try {
				System.out.print("Enter Check Out Date (YYYY-MM-DD) ----> ");
				checkOutString = scanner.nextLine();
				this.controller.setCheckOutDate(checkOutString);
				loop = false;
			} catch (IllegalArgumentException e) {
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
				this.controller.setRoom(selectedRoom);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		while (loop) {
			try {
				System.out.print("Enter number of guest ----> ");
				int numOfGuest = scanner.nextInt();
				scanner.nextLine();
				this.controller.setNumOfGuest(numOfGuest);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
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
				this.controller.setPaymentMethod(paymentMethod);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		if (paymentMethod == PaymentMethod.CreditCard) {
			while (loop) {
				try {
					System.out.println("Enter card number (12digits)----> ");
					int cardNumber = scanner.nextInt();
					scanner.nextLine();
					this.controller.setCardNumber(cardNumber);
					loop = false;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		this.controller.setStatus(Status.Confirmed);
		controller.addBooking();
	}

	private void updateBooking() {
		int choice = 0;
		boolean loop = true;

		try {
			searchBooking();
		} catch (NullPointerException e) {
			return;
		}

		while (loop) {
			try {
				System.out.println("Update Booking");
				System.out.println("--------------");
				System.out.println("1. Change booking detail.");
				System.out.println("2. Cancel booking.");
				System.out.println("3. Exit.");
				System.out.print("Pick option (1-3) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IndexOutOfBoundsException e) {
				scanner.nextLine();
				System.out.println("Option cannot be negative!");
			}
		}

		switch (choice) {
		case 1:
			changeBookingDets();
			break;
		case 2:
			cancelBooking();
			break;
		case 3:
			break;
		default:
			System.out.println("Invalid option.\n");
		}
		this.controller.updateBookingList();
	}

	private void checkIn() {
		try {
			searchBooking();
			controller.setStatus(Status.CheckedIn);
			System.out.println("Booking checked in!");
		} catch (NullPointerException e) {

		}
	}

	private void checkOut() {
		try {
			searchBooking();
			makePayment();
			controller.setStatus(Status.CheckedOut);
			printReceipt();
			System.out.println("Booking checked out!");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void searchBooking() {
		Booking theBooking = null;
		boolean error = true;
		while (error) {
			try {
				System.out.print("Enter booking ID ----> ");
				theBooking = this.controller.getBooking(scanner.nextInt());
				this.controller.createBooking(theBooking);
				this.controller.setClientProfile();
				viewBooking();
				error = false;
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				error = false;
				throw new NullPointerException();
			}
		}
	}

	private ClientProfile createClientProfile(String NRIC) {
		String firstName;
		String lastName;
		Gender gender = Gender.Male;
		String address;

		System.out.println("Creating new client profile.");
		this.controller.createClientProfile();
		this.controller.setNRIC(NRIC); // already validated previously

		boolean error = true;
		while (error) {
			try {
				System.out.print("Enter client's first name ----> ");
				firstName = scanner.nextLine();
				this.controller.setFirstName(firstName);
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		error = true;
		while (error) {
			try {
				System.out.print("Enter client's last name ----> ");
				lastName = scanner.nextLine();
				this.controller.setLastName(lastName);
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		error = true;
		while (error) {
			try {
				gender.printGenderOption();
				int choice = scanner.nextInt();
				scanner.nextLine();
				gender = gender.selectGender(choice);
				this.controller.setGender(gender);
				error = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		error = true;
		while (error) {
			try {
				System.out.print("Enter client's address ----> ");
				address = scanner.nextLine();
				this.controller.setAddress(address);
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("New client profile created!");
		this.controller.addClientProfile();
		return this.controller.getClientProfile();
	}

	private void changeBookingDets() {
		int choice = 0, roomNo, numOfGuest;
		boolean loop = true;
		String checkInDateStr = null;
		String checkOutDateStr = null;
		Room room;
		System.out.println("Change Booking Detail");
		System.out.println("---------------------");
		System.out.println("1. Change check-in date & check-out date.");
		System.out.println("2. Change room.");
		System.out.println("3. Change number of guest.");
		System.out.println("4. Exit.");

		while (loop) {
			try {
				System.out.print("Pick option (1-4) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				if (choice < 1 || choice > 4) {
					throw new IllegalSelectorException();
				}
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalSelectorException e) {
				System.out.println("Invalid option.");
			}
		}

		switch (choice) {
		case 1:
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter Check-in date (YYYY-MM-DD) ----> ");
					checkInDateStr = scanner.nextLine();
					this.controller.setCheckInDate(checkInDateStr);
					loop = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter check-out date (YYYY-MM-DD) ----> ");
					checkOutDateStr = scanner.nextLine();
					this.controller.setCheckOutDate(checkOutDateStr);
					loop = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			this.controller.setAvailableRoom(checkInDateStr, checkOutDateStr);
			printRoomList();
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter new room ----> ");
					roomNo = scanner.nextInt();
					scanner.nextLine();
					room = this.controller.getRoom(roomNo);
					this.controller.setRoom(room);
					loop = false;
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter new number of guest ----> ");
					numOfGuest = scanner.nextInt();
					scanner.nextLine();
					this.controller.setNumOfGuest(numOfGuest);
					loop = false;
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

			break;
		case 2:
			checkInDateStr = this.controller.getCheckInDate().toString();
			checkOutDateStr = this.controller.getCheckOutDate().toString();
			this.controller.setAvailableRoom(checkInDateStr, checkOutDateStr);
			printRoomList();
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter new room ----> ");
					roomNo = scanner.nextInt();
					scanner.nextLine();
					room = this.controller.getRoom(roomNo);
					this.controller.setRoom(room);
					loop = false;
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case 3:
			loop = true;
			while (loop) {
				try {
					System.out.print("Enter new number of guest ----> ");
					numOfGuest = scanner.nextInt();
					scanner.nextLine();
					this.controller.setNumOfGuest(numOfGuest);
					loop = false;
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case 4:
			return;
		}
		System.out.println("Booking updated!\n");
	}

	private void cancelBooking() {
		if (this.controller.getStatus() == Status.Cancelled) {
			System.out.println("The booking has already cancelled! Enter another booking.");
		} else if (this.controller.getStatus() == Status.CheckedOut) {
			System.out.println("The booking has already checked out! Enter another booking.");
		} else {
			boolean isRefundable = this.controller.validatePolicy();
			if (isRefundable) {
				System.out.println("The deposit is refundable");

				PaymentMethod paymentMethod = controller.getPaymentMethod();
				if (paymentMethod == PaymentMethod.CreditCard) {
					System.out.println("Payment Method ----> Credit Card");
					int cardNumber = controller.getCardNumber();
					System.out.println("Card Number ----> " + cardNumber);
				} else {
					System.out.println("Payment Method ----> Cash");
				}

				double deposit = controller.getDeposit();
				System.out.println("Deposit amount ----> " + deposit);
			}
			controller.setStatus(Status.Cancelled);
			System.out.println("The booking is cancelled.");
		}
	}

	private void viewBooking() { // pending
		UserType userType = this.controller.getUserType();
		printBookingDets(userType);
	}

	private void makePayment() {
		double totalPrice = this.controller.getTotalPrice();
		this.controller.setTotalPrice(totalPrice);
		PaymentMethod paymentMethod = PaymentMethod.Cash;

		try {
			System.out.println("Select payment method ----> ");
			paymentMethod.printPaymentMethodOption();
			int option = scanner.nextInt();
			paymentMethod = paymentMethod.selectPaymentMethod(option);
			System.out.println();
		} catch (InputMismatchException ex) {
			scanner.nextLine();
			System.out.println("Please enter a valid number.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		if (paymentMethod == PaymentMethod.CreditCard) {
			try {
				System.out.print("Enter card number: ");
				int cardNumber = scanner.nextInt();
				scanner.nextLine();
				this.controller.makePayment(paymentMethod, cardNumber);
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} else {
			this.controller.makePayment(paymentMethod);
		}
	}

	private void printClientProfileDets() {

		System.out.println();

		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :" + this.controller.getFirstName());
		System.out.println("Last Name   :" + this.controller.getLastName());
		System.out.println("NRIC        :" + this.controller.getNRIC());
		System.out.println("Gender      :" + this.controller.getGender());
		System.out.println("Address     :" + this.controller.getAddress());
		System.out.println();
	}

	private void printRoomList() {
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

		System.out.println("");

	}

	private void printBookingDets(UserType userType) {
		if (userType == UserType.Administrator) {
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + this.controller.getBookingID());
			System.out.println("Customer: " + this.controller.getClientProfile().getFirstName() + " "
					+ this.controller.getClientProfile().getLastName());
			System.out.println("NRIC: " + this.controller.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + this.controller.getCheckInDate().toString());
			System.out.println("Check-out date: " + this.controller.getCheckOutDate().toString());
			System.out.println("Room: " + this.controller.getRoom().getRoomID());
			System.out.println("Number of guest: " + this.controller.getNumOfGuest());
			System.out.println("Booking status: " + this.controller.getStatus());
			System.out.println("Payment method: " + this.controller.getPayment().getPaymentMethod());
			System.out.println("Total price:" + this.controller.getPayment().getTotalPrice());
		}
		if (userType == UserType.Client) {
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + this.controller.getBookingID());
			System.out.println("Customer: " + this.controller.getClientProfile().getFirstName() + " "
					+ this.controller.getClientProfile().getLastName());
			System.out.println("NRIC: " + this.controller.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + this.controller.getCheckInDate().toString());
			System.out.println("Check-out date: " + this.controller.getCheckOutDate().toString());
			System.out.println("Room: " + this.controller.getRoom().getRoomID());
			System.out.println("Number of guest: " + this.controller.getNumOfGuest());
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
		int choice = 0;
		boolean exit = false;
		boolean error = true;

		while (error) {
			try {
				System.out.println("1. Login");
				System.out.println("2. Search Booking");
				System.out.println("3. Exit");
				System.out.print("Pick option (1-2) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				error = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}

		switch (choice) {
		case 1:
			login();
			break;
		case 2:
			try {
				searchBooking();
			} catch (NullPointerException e) {
			}
			break;
		case 3:
			exit = true;
			break;
		default:
			System.out.println("Invalid option.\n");
		}
		return exit;
	}

	private boolean adminMenu() {
		int choice = 0;
		boolean exit = false;
		boolean error = true;

		while (error) {
			try {
				System.out.println("1. Create Booking");
				System.out.println("2. Update Booking");
				System.out.println("3. Search Booking");
				System.out.println("4. Check-in");
				System.out.println("5. Check-out");
				System.out.println("6. Exit");

				System.out.print("Pick option (1-6) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				error = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IndexOutOfBoundsException e) {
				scanner.nextLine();
				System.out.println("Option cannot be negative!");
			}
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
		default:
			System.out.println("Invalid option.\n");
		}

		return exit;
	}
}

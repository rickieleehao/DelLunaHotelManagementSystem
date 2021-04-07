package hotel.boundary;

import java.nio.channels.IllegalSelectorException;
import java.util.*;

import hotel.controller.Controller;
import hotel.domain.entity.*;

public class SystemUI {
	private Scanner scanner;
	private Controller control;

	public SystemUI(Controller control, Scanner scanner) {
		this.control = control;
		this.scanner = scanner;
	}

	public void start() {
		boolean exit = false;
		while (!exit) {
			UserType userType = control.getUserType();
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
			this.control.login(password);
			System.out.println("\n||||||||||||||||||||||||||||");
			System.out.println("     Login successful");
			System.out.println("Welcome back Administrator!");
			System.out.println("||||||||||||||||||||||||||||\n");
		} catch (IllegalArgumentException e) {
			System.out.println("\n||||||||||||||||||||||||||||");
			System.out.println("      Login failed");
			System.out.println("   " + e.getMessage());
			System.out.println("||||||||||||||||||||||||||||\n");
		}
	}

	private void createBooking() {
		System.out.println("\n||||||||||||||||||||||||");
		System.out.println("  Creating new Booking");
		System.out.println("||||||||||||||||||||||||\n");
		this.control.createBooking();
		this.control.setBookingID();

		String NRIC = null;
		boolean loop = true;
		while (loop) {
			try {
				System.out.print("Enter NRIC ----> ");
				NRIC = scanner.nextLine();
				control.setClientProfile(NRIC);
				loop = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				ClientProfile clientProfile = createClientProfile(NRIC);
				this.control.setClientProfile(clientProfile);
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
				this.control.setCheckInDate(checkInString);
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
				this.control.setCheckOutDate(checkOutString);
				loop = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		this.control.setAvailableRoom(checkInString, checkOutString);

		loop = true;
		Room selectedRoom;
		while (loop) {
			try {
				printRoomList();
				int option = scanner.nextInt();
				scanner.nextLine();
				selectedRoom = control.getRoom(option - 1);
				this.control.setRoom(selectedRoom);
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
				this.control.setNumOfGuest(numOfGuest);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		loop = true;
		PaymentMethod paymentMethod = PaymentMethod.Undefined;
		while (loop) {
			try {
				this.control.printPaymentMethodOption();
				int option = scanner.nextInt();
				scanner.nextLine();
				paymentMethod = this.control.selectPaymentMethod(option);
				this.control.setPaymentMethod(paymentMethod);
				loop = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		String cardNumber;
		loop = true;
		if (paymentMethod == PaymentMethod.CreditCard) {
			while (loop) {
				try {
					System.out.print("Enter card number (12digits)----> ");
					cardNumber = scanner.nextLine();
					this.control.setCardNumber(cardNumber);
					loop = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		this.control.setStatus(Status.Confirmed);
		control.addBooking();
		printBookingDets();
		System.out.println("\n|||||||||||||||||||||||");
		System.out.println("  New Booking created");
		System.out.println("      and updated!");
		System.out.println("|||||||||||||||||||||||\n");
	}

	private void updateBooking() {
		System.out.println("\n||||||||||||||||||||||||");
		System.out.println("  Updating new Booking");
		System.out.println("||||||||||||||||||||||||\n");

		int choice = 0;
		boolean loop = true;

		try {
			searchBooking();
		} catch (NullPointerException e) {
			System.out.println("-----------------------\n");
			return;
		}

		if (this.control.getStatus() != Status.Confirmed) {
			System.out.println("\n|||||||||||||||||||||||||||||||");
			System.out.println("The booking cannot be  ");
			System.out.println("motified because it is either:-");
			System.out.println("   i. CheckedIn");
			System.out.println("   ii. CheckedOut");
			System.out.println("   iii. Cancelled");
			System.out.println("");
			System.out.println("Select another booking");
			System.out.println("   Back to the menu.");
			System.out.println("|||||||||||||||||||||||||||||||\n");
		} else {

			while (loop) {
				try {
					System.out.println("Pick an action:-");
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
			this.control.updateBookingList();
			System.out.println("\n||||||||||||||||||||");
			System.out.println("  Booking updated!");
			System.out.println("||||||||||||||||||||\n");
		}
	}

	private void checkIn() {
		System.out.println("\n||||||||||||||||||||||");
		System.out.println("  Checking-in Booking");
		System.out.println("||||||||||||||||||||||\n");

		try {
			searchBooking();
		} catch (NullPointerException e) {
			return;
		}

		if (this.control.getStatus() != Status.Confirmed) {
			System.out.println("\n|||||||||||||||||||||||||||");
			System.out.println("The booking is not longer  ");
			System.out.println("available for checking-in!");
			System.out.println("");
			System.out.println("Select another booking");
			System.out.println("   Back to the menu.");
			System.out.println("|||||||||||||||||||||||||||\n");
		} else {
			control.setStatus(Status.CheckedIn);
			System.out.println("\n|||||||||||||||||||||||");
			System.out.println("  Booking checked in!");
			System.out.println("|||||||||||||||||||||||\n");
			this.control.updateBookingList();
		}

	}

	private void checkOut() {
		System.out.println("\n||||||||||||||||||||||||");
		System.out.println("  Checking-out Booking");
		System.out.println("||||||||||||||||||||||||\n");

		try {
			searchBooking();
		} catch (NullPointerException e) {
			return;
		}
		if (this.control.getStatus() != Status.CheckedIn) {
			System.out.println("\n||||||||||||||||||||||||||||");
			System.out.println("The booking is not available");
			System.out.println("for checking-out because it");
			System.out.println("is either:-");
			System.out.println("   i. Haven't check in");
			System.out.println("   ii. CheckedOut");
			System.out.println("   iii. Cancelled");
			System.out.println("");
			System.out.println("Select another booking");
			System.out.println("   Back to the menu.");
			System.out.println("||||||||||||||||||||||||||||\n");
		} else {
			makePayment();
			control.setStatus(Status.CheckedOut);
			printReceipt();
			System.out.println("\n||||||||||||||||||||||||");
			System.out.println("  Booking checked out!");
			System.out.println("||||||||||||||||||||||||\n");
			this.control.updateBookingList();
		}
	}

	private void searchBooking() {
		Booking theBooking = null;
		boolean error = true;
		while (error) {
			try {
				System.out.print("Enter booking ID ----> ");
				theBooking = this.control.getBooking(scanner.nextInt());
				this.control.createBooking(theBooking);
				this.control.setClientProfile();
				viewBooking();
				error = false;
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (NullPointerException e) {
				System.out.println("\n||||||||||||||||||||||");
				System.out.println("   " + e.getMessage());
				System.out.println("||||||||||||||||||||||\n");
				error = false;
				throw new NullPointerException();
			}
		}
	}

	private ClientProfile createClientProfile(String NRIC) {
		String firstName;
		String lastName;
		Gender gender = Gender.Undefined;
		String address;
		
		System.out.println("\n|||||||||||||||||||||||||||||||");
		System.out.println("  Creating new client profile");
		System.out.println("|||||||||||||||||||||||||||||||\n");
		
		this.control.createClientProfile();
		this.control.setNRIC(NRIC); // already validated previously

		boolean error = true;
		while (error) {
			try {
				System.out.print("Enter client's first name ----> ");
				firstName = scanner.nextLine();
				this.control.setFirstName(firstName);
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
				this.control.setLastName(lastName);
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		error = true;
		while (error) {
			try {
				this.control.printGenderOption();
				int choice = scanner.nextInt();
				scanner.nextLine();
				gender = this.control.selectGender(choice);
				this.control.setGender(gender);
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
				this.control.setAddress(address);
				error = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("New client profile created!");
		this.control.addClientProfile();
		return this.control.getClientProfile();
	}

	private void changeBookingDets() {
		int choice = 0, roomNo, numOfGuest;
		boolean loop = true;
		String checkInDateStr = null;
		String checkOutDateStr = null;
		Room room;
		System.out.println("\n|||||||||||||||||||||||||");
		System.out.println("  Change Booking Detail");
		System.out.println("|||||||||||||||||||||||||\n");
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
					this.control.setCheckInDate(checkInDateStr);
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
					this.control.setCheckOutDate(checkOutDateStr);
					loop = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
			this.control.setAvailableRoom(checkInDateStr, checkOutDateStr);
			
			loop = true;
			while (loop) {
				try {
					printRoomList();
					roomNo = scanner.nextInt();
					scanner.nextLine();
					room = this.control.getRoom(roomNo - 1);
					this.control.setRoom(room);
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
					this.control.setNumOfGuest(numOfGuest);
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
			checkInDateStr = this.control.getCheckInDate().toString();
			checkOutDateStr = this.control.getCheckOutDate().toString();
			this.control.setAvailableRoom(checkInDateStr, checkOutDateStr);

			loop = true;
			while (loop) {
				try {
					printRoomList();
					roomNo = scanner.nextInt();
					scanner.nextLine();
					room = this.control.getRoom(roomNo - 1);
					this.control.setRoom(room);
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
					this.control.setNumOfGuest(numOfGuest);
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
	}

	private void cancelBooking() {
		boolean isRefundable = this.control.validatePolicy();
		if (isRefundable) {
			System.out.println("\n-----------------------");
			System.out.println("The deposit is refundable");

			PaymentMethod paymentMethod = control.getPaymentMethod();
			if (paymentMethod == PaymentMethod.CreditCard) {
				System.out.println("Payment Method ----> Credit Card");
				String cardNumber = this.control.getCardNumber();
				System.out.println("Card Number ----> " + cardNumber);
			} else {
				System.out.println("Payment Method ----> Cash");
			}

			double deposit = this.control.getDeposit();
			System.out.println("Deposit amount ----> " + deposit);
		}
		this.control.setStatus(Status.Cancelled);
		System.out.println("\n|||||||||||||||||||||||||||||");
		System.out.println("  The booking is cancelled!");
		System.out.println("|||||||||||||||||||||||||||||\n");

	}

	private void viewBooking() {
		printBookingDets();
	}

	private void makePayment() {
		double totalPrice = this.control.getTotalPrice();
		this.control.setTotalPrice(totalPrice);
		PaymentMethod paymentMethod = PaymentMethod.Undefined;

		boolean error = true;

		while (error) {
			try {
				this.control.printPaymentMethodOption();
				int option = scanner.nextInt();
				scanner.nextLine();
				paymentMethod = this.control.selectPaymentMethod(option);
				System.out.println();
				error = false;
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		error = true;
		while (error) {
			if (paymentMethod == PaymentMethod.CreditCard) {
				try {
					System.out.print("Enter card number: ");
					String cardNumber = scanner.nextLine();
					this.control.makePayment(paymentMethod, cardNumber);
					error = false;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else {
				this.control.makePayment(paymentMethod);
				error = false;
			}
		}

	}

	private void printClientProfileDets() {

		System.out.println();

		System.out.println("----Here is the Client Info----");
		System.out.println("First Name  :" + this.control.getFirstName());
		System.out.println("Last Name   :" + this.control.getLastName());
		System.out.println("NRIC        :" + this.control.getNRIC());
		System.out.println("Gender      :" + this.control.getGender());
		System.out.println("Address     :" + this.control.getAddress());
		System.out.println();
	}

	private void printRoomList() {
		List<Room> availableRoomList = this.control.getAvailableRoomList();
		System.out.println("\n-----------------------");
		System.out.printf("%-6s", "No.");
		System.out.printf("%-11s", "Room ID");
		System.out.printf("%-10s", "Rates");
		System.out.printf("%-16s", "Number of Beds");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < availableRoomList.size(); i++) {
			System.out.printf("%-2s", "");
			System.out.printf("%-6d", i + 1);
			System.out.printf("%-9d", this.control.getRoomID(availableRoomList.get(i)));
			System.out.printf("%-17.2f", this.control.getRoomRate(availableRoomList.get(i)));
			System.out.printf("%-4d", this.control.getRoomNumOfBed(availableRoomList.get(i)));
			System.out.println("");
		}
		System.out.println("");
		System.out.println("These are the available room.");
		System.out.println("");
		System.out.print("Pick a room (No.) ----> ");
	}

	private void printBookingDets() {
		UserType userType = this.control.getUserType();
		if (userType == UserType.Administrator) {
			System.out.println("\n--------------");
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + this.control.getBookingID());
			System.out.println("Customer: " + this.control.getClientProfile().getFirstName() + " "
					+ this.control.getClientProfile().getLastName());
			System.out.println("NRIC: " + this.control.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + this.control.getCheckInDate().toString());
			System.out.println("Check-out date: " + this.control.getCheckOutDate().toString());
			System.out.println("Room: " + this.control.getRoom().getRoomID());
			System.out.println("Number of guest: " + this.control.getNumOfGuest());
			System.out.println("Booking status: " + this.control.getStatus());
			System.out.println("Payment method: " + this.control.getPaymentMethod());
			System.out.println("Total price:" + this.control.getTotalPrice());
			System.out.println("");
		} else if (userType == UserType.Client) {
			System.out.println("\n--------------");
			System.out.println("Booking Detail");
			System.out.println("--------------");
			System.out.println("Booking ID: " + this.control.getBookingID());
			System.out.println("Customer: " + this.control.getClientProfile().getFirstName() + " "
					+ this.control.getClientProfile().getLastName());
			System.out.println("NRIC: " + this.control.getClientProfile().getNRIC());
			System.out.println("Check-in date : " + this.control.getCheckInDate().toString());
			System.out.println("Check-out date: " + this.control.getCheckOutDate().toString());
			System.out.println("Room: " + this.control.getRoom().getRoomID());
			System.out.println("Number of guest: " + this.control.getNumOfGuest());
			System.out.println("");
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
		System.out.println("Booking ID     : " + control.getBookingID());
		System.out.println("First Name     : " + control.getFirstName());
		System.out.println("Last Name      : " + control.getLastName());
		System.out.println("Booking Status : " + control.getStatus());
		System.out.println("-----------------------");
		System.out.println("    Payment Detail     ");
		System.out.println("-----------------------");
		System.out.println("Payment Method : " + control.getPaymentMethod());
		System.out.println("     TOTAL     : " + control.getTotalPrice());
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
				System.out.println("-----------------------");
				System.out.println("      Client Menu");
				System.out.println("-----------------------");
				System.out.println("1. Login as Administrator");
				System.out.println("2. Search Booking");
				System.out.println("3. Exit");
				System.out.print("Pick option (1-3) ----> ");
				choice = scanner.nextInt();
				scanner.nextLine();
				error = false;
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Please enter a valid number.\n");
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
				System.out.println("-----------------------");
				System.out.println("      Admin Menu");
				System.out.println("-----------------------");
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
				System.out.println("Please enter a valid number.\n");
			} catch (IndexOutOfBoundsException e) {
				scanner.nextLine();
				System.out.println("Option cannot be negative!\n");
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
			try {
				searchBooking();
			} catch (NullPointerException e) {
			}
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

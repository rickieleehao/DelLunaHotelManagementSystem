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
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createBooking() {

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
		printRoomList();

		loop = true;
		Room selectedRoom;
		while (loop) {
			try {
				System.out.print("Select available room by enter room number ----> ");
				int option = scanner.nextInt();
				scanner.nextLine();
				selectedRoom = control.getRoom(option);
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
		PaymentMethod paymentMethod = PaymentMethod.Cash;
		while (loop) {
			try {
				paymentMethod.printPaymentMethodOption();
				int option = scanner.nextInt();
				scanner.nextLine();
				paymentMethod.selectPaymentMethod(option);
				this.control.setPaymentMethod(paymentMethod);
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
					this.control.setCardNumber(cardNumber);
					loop = false;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		this.control.setStatus(Status.Confirmed);
		control.addBooking();
		printBookingDets();
		System.out.println("New Booking created and updated!");
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
		this.control.updateBookingList();
	}

	private void checkIn() {
		try {
			searchBooking();
			control.setStatus(Status.CheckedIn);
			System.out.println("Booking checked in!");
			this.control.updateBookingList();
		} catch (NullPointerException e) {

		}
	}

	private void checkOut() {
		try {
			searchBooking();
			makePayment();
			control.setStatus(Status.CheckedOut);
			printReceipt();
			System.out.println("Booking checked out!");
			this.control.updateBookingList();
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
				theBooking = this.control.getBooking(scanner.nextInt());
				this.control.createBooking(theBooking);
				this.control.setClientProfile();
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
				gender.printGenderOption();
				int choice = scanner.nextInt();
				scanner.nextLine();
				gender = gender.selectGender(choice);
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
		if (this.control.getStatus() == Status.Cancelled) {
			System.out.println("The booking has already cancelled! Enter another booking.");
		} else if (this.control.getStatus() == Status.CheckedOut) {
			System.out.println("The booking has already checked out! Enter another booking.");
		} else {
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
				printRoomList();
				loop = true;
				while (loop) {
					try {
						System.out.print("Enter new room ----> ");
						roomNo = scanner.nextInt();
						scanner.nextLine();
						room = this.control.getRoom(roomNo);
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
				printRoomList();
				loop = true;
				while (loop) {
					try {
						System.out.print("Enter new room ----> ");
						roomNo = scanner.nextInt();
						scanner.nextLine();
						room = this.control.getRoom(roomNo);
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
			System.out.println("Booking updated!\n");
		}
	}

	private void cancelBooking() {
		if (this.control.getStatus() == Status.Cancelled) {
			System.out.println("The booking has already cancelled! Enter another booking.");
		} else if (this.control.getStatus() == Status.CheckedOut) {
			System.out.println("The booking has already checked out! Enter another booking.");
		} else {
			boolean isRefundable = this.control.validatePolicy();
			if (isRefundable) {
				System.out.println("The deposit is refundable");

				PaymentMethod paymentMethod = control.getPaymentMethod();
				if (paymentMethod == PaymentMethod.CreditCard) {
					System.out.println("Payment Method ----> Credit Card");
					int cardNumber = control.getCardNumber();
					System.out.println("Card Number ----> " + cardNumber);
				} else {
					System.out.println("Payment Method ----> Cash");
				}

				double deposit = control.getDeposit();
				System.out.println("Deposit amount ----> " + deposit);
			}
			control.setStatus(Status.Cancelled);
			System.out.println("The booking is cancelled.");
		}
	}

	private void viewBooking() {
		printBookingDets();
	}

	private void makePayment() {
		double totalPrice = this.control.getTotalPrice();
		this.control.setTotalPrice(totalPrice);
		PaymentMethod paymentMethod = PaymentMethod.Cash;

		boolean error = true;

		while (error) {
			try {
				System.out.println("Select payment method ----> ");
				paymentMethod.printPaymentMethodOption();
				int option = scanner.nextInt();
				paymentMethod = paymentMethod.selectPaymentMethod(option);
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
					int cardNumber = scanner.nextInt();
					scanner.nextLine();
					this.control.makePayment(paymentMethod, cardNumber);
					error = false;
				} catch (InputMismatchException ex) {
					scanner.nextLine();
					System.out.println("Please enter a valid number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} else {
				this.control.makePayment(paymentMethod);
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
		System.out.printf("%-6s", "No.");
		System.out.printf("%-9s", "Room ID");
		System.out.printf("%-8s", "Rates");
		System.out.printf("%-16s", "Number of Beds");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < availableRoomList.size(); i++) {
			System.out.printf("%-2s", "");
			System.out.printf("%-6d", i);
			System.out.printf("%-9d", this.control.getRoomID(availableRoomList.get(i)));
			System.out.printf("%-8.2f", this.control.getRoomRate(availableRoomList.get(i)));
			System.out.printf("%-2s","");
			System.out.printf("%-16d", this.control.getRoomNumOfBed(availableRoomList.get(i)));
			System.out.println("");
		}
		String choice;
		System.out.print("Enter a room number: ");
		choice = scanner.nextLine();
		
		System.out.println("");

	}

	private void printBookingDets() {
		UserType userType = this.control.getUserType();
		if (userType == UserType.Administrator) {
			System.out.println("--------------");
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
			System.out.println("--------------");
		}
		if (userType == UserType.Client) {
			System.out.println("--------------");
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
			System.out.println("--------------");
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
				System.out.print("Pick option (1-2) ----> ");
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

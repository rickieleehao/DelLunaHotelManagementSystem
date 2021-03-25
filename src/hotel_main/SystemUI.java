package hotel_main;

import hotel_entity.*;
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

	public void createBooking() { // xz
		int bookingID = controller.generateBookingID();
		System.out.print("Enter NRIC ----> ");
		String NRIC = scanner.nextLine();
		boolean isProfileFound = controller.searchClientProfile(NRIC);

	}

	public void updateBooking() { // hy
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
	
	public ClientProfile createClientProfile(String NRIC) { // yy
		Scanner in = new Scanner(System.in);
		
		String firstName;
		String lastName;
		String temp;
		Gender gender = null;
		String address;
		
		System.out.print("Enter client's first name ----> ");
		firstName = in.nextLine();
		
		System.out.print("Enter client's last name ----> ");
		lastName = in.nextLine();
		
		System.out.print("Enter client's gender(M/F) ----> ");
		temp = in.nextLine();
		if(temp.toLowerCase().equals("m")) {
			gender = Gender.Male;
		}
		else if(temp.toLowerCase().equals("f")) {
			gender = Gender.Female;
		}
		
		System.out.print("Enter client's address ----> ");
		address = in.nextLine();
		
		ClientProfile newClientProfile = new ClientProfile(NRIC, firstName, lastName, gender, address);
		System.out.println("Client's profile is created.");
		return newClientProfile;
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

	}

	private void displayAvailableRoom(List<Room> availableRoomList) { // yy
		System.out.printf("%-9s", "Room ID");
		System.out.printf("%-8s", "Rates");
		System.out.printf("%-10s", "Discount");
		System.out.printf("%-16s", "Number of Beds");
		System.out.println("");
		System.out.println("");
		
		for(int i = 0; i < availableRoomList.size(); i++) {
			System.out.printf("%-9d", availableRoomList.get(i).getRoomID());
			System.out.printf("%-8.2f", availableRoomList.get(i).getRate());
			System.out.printf("%-10.2f", availableRoomList.get(i).getDiscount());
			System.out.printf("%-16d", availableRoomList.get(i).getNumOfBed());
			System.out.println("");
		}
	}

	private void changeBookingDets(Booking theBooking) { // hy

	}

	public void cancelBooking(Booking theBooking) { //yy
		Calendar calendar = Calendar.getInstance();
		Date dateToday = calendar.getTime();
		boolean isRefundable = theBooking.validatePolicy(dateToday);
		if(isRefundable) {
			System.out.println("The deposit is refundable");
			double deposit = theBooking.getPayment().getDeposit();
			System.out.println("Deposit amount ----> " + deposit);
			PaymentMethod paymentMethod = theBooking.getPayment().getPaymentMethod();
			if(paymentMethod == PaymentMethod.CreditCard) {
				System.out.println("Payment Method ----> Credit Card");
				int cardNumber = theBooking.getPayment().getCardNumber();
				System.out.println("Card Number ----> " + cardNumber);
			}
			else {
				System.out.println("Payment Method ----> Cash");
			}
		}
		theBooking.setStatus(BookingStatus.Cancelled);
		System.out.println("The booking is cancelled.");
	}

	private void makePayment(Booking theBooking) { // tbc

	}
}

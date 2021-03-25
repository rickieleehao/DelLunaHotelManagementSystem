package hotel_main;

import hotel_interface.*;

import java.util.Scanner;

import hotel_entity.*;

public class HotelManagementSystem {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		IBookingData bookingList = new BookingList("fileName");
		IClientData clientProfileList = new ClientProfileList("fileName");
		IUser user = new User();

		Controller controller = new Controller(bookingList, clientProfileList, user);
		SystemUI ui = new SystemUI(controller, scanner);
		ui.start();
	}

}

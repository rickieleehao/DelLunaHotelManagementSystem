package hotel_main;

import hotel_interface.*;

import java.util.Scanner;

import hotel_entity.*;

public class HotelManagementSystem {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		RoomList roomList = new RoomList("RoomList.txt");
		IClientData clientProfileList = new ClientProfileList("ClientProfileList.txt");
		IBookingData bookingList = new BookingList("BookingList.txt", (ClientProfileList) clientProfileList, roomList);
		IUser user = new User();
		Controller controller = new Controller(bookingList, clientProfileList, user);
		SystemUI ui = new SystemUI(controller, scanner);
		ui.start();
		System.out.println("Thanks for using DelLuna Hotel Management System!");
	}

}

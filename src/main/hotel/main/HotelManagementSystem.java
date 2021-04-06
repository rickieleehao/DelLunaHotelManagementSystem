package hotel.main;

import java.util.Scanner;

import hotel.boundary.SystemUI;
import hotel.controller.Controller;
import hotel.domain.IEntity.*;
import hotel.domain.entity.*;

public class HotelManagementSystem {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		RoomList roomList = new RoomList("RoomList.txt");
		IClientData clientProfileList = new ClientProfileList("ClientProfileList.txt");
		IBookingData bookingList = new BookingList("BookingList.txt", (ClientProfileList) clientProfileList, roomList);
		IUser user = new User();
		Controller control = new Controller(bookingList, clientProfileList, user);
		SystemUI ui = new SystemUI(control, scanner);
		ui.start();
		System.out.println("\nThanks for using DelLuna Hotel Management System!");
	}
}

package hotel_main;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import hotel_entity.BookingList;
import hotel_entity.ClientProfileList;
import hotel_entity.RoomList;
import hotel_entity.User;
import hotel_interface.IBookingData;
import hotel_interface.IClientData;
import hotel_interface.IUser;

public class SystemUITest {

	private static Scanner scanner;
	private static RoomList roomList;
	private static IClientData clientProfileList;
	private static IBookingData bookingList;
	private static IUser user;
	private static Controller controller;
	private static SystemUI ui;

	@BeforeClass
	public static void setup() {
		scanner = new Scanner(System.in);
		roomList = new RoomList("RoomList.txt");
		clientProfileList = new ClientProfileList("ClientProfileList.txt");
		bookingList = new BookingList("BookingList.txt", (ClientProfileList) clientProfileList, roomList);
		user = new User();
		controller = new Controller(bookingList, clientProfileList, user);
		ui = new SystemUI(controller, scanner);
	}

	@Test
	public void startMenuTest() {
		ui.start();
	}

}

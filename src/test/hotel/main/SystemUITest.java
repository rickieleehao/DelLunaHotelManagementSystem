package hotel.main;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import hotel.boundary.SystemUI;
import hotel.controller.Controller;
import hotel.domain.IEntity.IBookingData;
import hotel.domain.IEntity.IClientData;
import hotel.domain.IEntity.IUser;
import hotel.domain.entity.BookingList;
import hotel.domain.entity.ClientProfileList;
import hotel.domain.entity.RoomList;
import hotel.domain.entity.User;

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

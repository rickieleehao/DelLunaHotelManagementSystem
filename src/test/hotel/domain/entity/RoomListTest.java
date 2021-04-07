package hotel.domain.entity;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hotel.domain.IEntity.IClientData;

public class RoomListTest {

	@Test
	public void test() {

		IClientData ICD = new ClientProfileList("ClientProfileList.txt");
		RoomList RL = new RoomList("RoomList.txt");
		BookingList BL = new BookingList("BookingList.txt", (ClientProfileList) ICD, RL);
		List<Room> availableRoomList = RL.getRoomList();
		List<Room> removedRoom = new ArrayList<Room>();

		List<Booking> bookingList = BL.getBookingList();
		LocalDate checkInDate = LocalDate.parse("2020-01-01");
		LocalDate checkOutDate = LocalDate.parse("2020-01-02");

		for (int i = 0; i < bookingList.size(); i++) {
			if (!(checkInDate.isAfter(bookingList.get(i).getCheckOutDate()))
					&& !(checkOutDate.isBefore(bookingList.get(i).getCheckInDate()))
					&& (bookingList.get(i).getStatus() == Status.Confirmed
							|| bookingList.get(i).getStatus() == Status.CheckedIn)) {
				if (!removedRoom.contains(bookingList.get(i).getRoom())) {
					removedRoom.add(bookingList.get(i).getRoom());
				}
			}
		}

		for (int k = 0; k < removedRoom.size(); k++) {
			for (int j = 0; j < availableRoomList.size(); j++) {
				if (availableRoomList.get(j).getRoomID() == removedRoom.get(k).getRoomID()) {
					availableRoomList.remove(j);
					break;
				}
			}
		}
	}

}

package hotel.domain.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomList {

	private List<Room> roomList;
	private static String fileName;

	public RoomList(String fileName) {
		RoomList.fileName = fileName;
		this.roomList = new ArrayList<Room>();
		String roomType;
		try {
			Scanner s = new Scanner(new File(fileName));
			s.useDelimiter("(,|\r\n|\r|\n)");
			while (s.hasNext()) {
				roomType = s.next();
				if (roomType.equals("Suite")) {
					this.roomList.add(new Suite(s.nextInt(), s.nextDouble(), s.nextInt()));
				} else if (roomType.equals("Deluxe")) {
					this.roomList.add(new Deluxe(s.nextInt(), s.nextDouble(), s.nextInt()));
				}
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RoomList() {
		String fileName = RoomList.fileName;
		this.roomList = new ArrayList<Room>();
		String roomType;
		try {
			Scanner s = new Scanner(new File(fileName));
			s.useDelimiter("(,|\r\n|\r|\n)");
			while (s.hasNext()) {
				roomType = s.next();
				if (roomType.equals("Suite")) {
					this.roomList.add(new Suite(s.nextInt(), s.nextDouble(), s.nextInt()));
				} else if (roomType.equals("Deluxe")) {
					this.roomList.add(new Deluxe(s.nextInt(), s.nextDouble(), s.nextInt()));
				}
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public Room getRoom(int option) {
		if (option < 0) {
			throw new IllegalArgumentException("Invalid option! cannot be negative value!");
		} else if (option >= this.roomList.size()) {
			throw new IllegalArgumentException("Invalid option! exceeded the provided option!");
		}

		return this.roomList.get(option);
	}

	public void setRoomList(List<Room> listOfRoom) {
		this.roomList = listOfRoom;
	}
}

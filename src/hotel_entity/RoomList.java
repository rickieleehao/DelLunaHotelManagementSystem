package hotel_entity;

import java.util.List;

public class RoomList {
	
	List<Room> roomList;
	
	public RoomList() {
	}
	
	public List<Room> getRoomList() {
		return roomList;
	}
	
	public void setRoomList(List<Room> listOfRoom) {
		this.roomList = listOfRoom;
	}
	
	public Room getRoom(int option) {
		if(option <0) {
			throw new IllegalArgumentException("Invalid option! cannot be negative value!");
		}else if(option >= this.roomList.size()) {
			throw new IllegalArgumentException("Invalid option! exceeded the provided option!");
		}
		
		return this.roomList.get(option);
	}
}

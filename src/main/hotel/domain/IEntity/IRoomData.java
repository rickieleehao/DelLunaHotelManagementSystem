package hotel.domain.IEntity;

import java.util.List;

import hotel.domain.entity.Room;

public interface IRoomData {

	public void setRoomList(List<Room> roomList);
	public List<Room> getRoomList();
	public Room getRoom(int option);
}

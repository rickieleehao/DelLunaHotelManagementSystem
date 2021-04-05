package hotel_entity;

public class Room {
	protected int roomID;
	protected double rate;
	protected int numOfBed;
	
	public Room(int roomID, double rate, int numOfBed) {
		this.roomID = roomID;
		this.rate = rate;
		this.numOfBed = numOfBed;
//		if(roomID >= 0) {
//			this.roomID = roomID;
//		}
//		if(rate >= 0) {
//			this.rate = rate;
//		}
//		else {
//			throw new IllegalArgumentException("Invalid input");
//		}
//		if(numOfBed >= 0) {
//			this.numOfBed = numOfBed;
//		}
//		else {
//			throw new IllegalArgumentException("Invalid input");
//		}
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public double getRate() {
		return rate;
	}
	
	public int getNumOfBed() {
		return numOfBed;
	}
}

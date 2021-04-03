package hotel_entity;

public abstract class Room {
	protected int roomID;
	protected double rate;
	protected int numOfBed;
	
	public Room(int roomID, double rate, int numOfBed) {
		this.roomID = roomID;
		this.rate = rate;
		this.numOfBed = numOfBed;
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

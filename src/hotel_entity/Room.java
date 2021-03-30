package hotel_entity;

public abstract class Room {
	protected int roomID;
	protected double rate;
	protected double discount;
	protected int numOfBed;
	
	public Room(int roomID, double rate, double discount, int numOfBed) {
		this.roomID = roomID;
		this.rate = rate;
		this.discount = discount;
		this.numOfBed = numOfBed;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public double getRate() {
		return rate;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public int getNumOfBed() {
		return numOfBed;
	}
}

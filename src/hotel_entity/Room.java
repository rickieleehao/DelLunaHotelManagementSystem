package hotel_entity;

public abstract class Room {
	private int roomID;
	private double rate;
	private double discountl;
	private int numOfBed;
	
	public Room(int roomID, double rate, double discount, int numOfBed) {
		this.roomID = roomID;
		this.rate = rate;
		this.discountl = discount;
		this.numOfBed = numOfBed;
	}
}

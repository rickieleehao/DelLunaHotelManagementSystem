package hotel.domain.entity;

public class Deluxe extends Room {
	public Deluxe(int roomID, double rate, int numOfBed) {
		super(roomID, rate, numOfBed);
		this.type = "Deluxe";
	}
}

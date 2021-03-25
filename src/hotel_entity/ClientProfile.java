package hotel_entity;

public class ClientProfile {
	private String NRIC;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String address;
	
	public ClientProfile(String NRIC, String firstName, String lastName, Gender gender, String address) {
		this.NRIC = NRIC;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
	}
	
	public String getNRIC() {
		return NRIC;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public String getAddress() {
		return address;
	}
}

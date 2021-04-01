package hotel_entity;

public class ClientProfile { //done
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
	
	public ClientProfile() {
		
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
	
	public void setFirstName(String firstName) {
		if(firstName.length() < 5) {
			throw new IllegalArgumentException("First name at least need 5 characters.");
		}else if (firstName.length() > 40) {
			throw new IllegalArgumentException("First name cannot exceed 40 characters.");
		}
		
		this.firstName = firstName;
	}
}

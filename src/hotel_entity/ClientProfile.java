package hotel_entity;

import hotel_interface.IClientProfile;

public class ClientProfile implements IClientProfile {
	private String NRIC;
	private String firstName;
	private String lastName;
	private Gender gender;
	private String address;
	
	public ClientProfile(String NRIC, String firstName, String lastName, String gender, String address) {
		this.NRIC = NRIC;
		this.firstName = firstName;
		this.lastName = lastName;
		if (gender.contentEquals("Male")) {
			this.gender = Gender.Male;
		}else {
			this.gender = Gender.Female;
		}
		this.address = address;
	}
	
	public ClientProfile(String NRIC, String firstName, String lastName, Gender gender, String address) {
		this.NRIC = NRIC;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
	}
	
	public ClientProfile() {
		
	}
	
	@Override
	public String getNRIC() {
		return NRIC;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public Gender getGender() {
		return gender;
	}
	
	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setNRIC(String NRIC) {
		if (NRIC.length() != 12 || NRIC.matches("[0-9]+")) {
			throw new IllegalArgumentException();
		}
		else {
			this.NRIC = NRIC;
		}
		
	}

	@Override
	public void setFirstName(String firstName) {
		if (firstName.length() < 5 || NRIC.matches("[a-zA-Z]+") == false) {
			throw new IllegalArgumentException();
		}
		else {
			this.firstName = firstName;
		}
		
	}

	@Override
	public void setLastName(String lastName) {
		if (lastName.length() < 5 || lastName.matches("[a-zA-Z]+") == false) {
			throw new IllegalArgumentException();
		}
		else {
			this.lastName = lastName;
		}
		
	}

	@Override
	public void setGender(Gender gender) {
		if (gender == null) {
			throw new NullPointerException();
		}
		else {
			this.gender = gender;
		}
		
	}

	@Override
	public void setAddress(String address) {
		if (address.length() < 30) {
			throw new IllegalArgumentException();
		}
		else {
			this.address = address;
		}
		
	}
}

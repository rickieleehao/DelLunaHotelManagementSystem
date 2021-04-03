package hotel_entity;

import hotel_interface.IClientProfile;

public class ClientProfile implements IClientProfile {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGender(Gender gender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		
	}
}

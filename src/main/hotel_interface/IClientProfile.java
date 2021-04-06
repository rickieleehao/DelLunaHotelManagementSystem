package hotel_interface;

import hotel_entity.Gender;

public interface IClientProfile {

	// set method
	public void setNRIC(String NRIC);
	public void setFirstName(String firstName);
	public void setLastName(String lastName);
	public void setGender(Gender gender);
	public void setAddress(String address);
	
	// get method
	public String getNRIC();
	public String getFirstName();
	public String getLastName();
	public Gender getGender();
	public String getAddress();

}

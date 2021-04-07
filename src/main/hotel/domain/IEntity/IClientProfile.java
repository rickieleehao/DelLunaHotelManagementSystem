package hotel.domain.IEntity;

import hotel.domain.entity.Gender;

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

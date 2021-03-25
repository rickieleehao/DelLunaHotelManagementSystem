package hotel_entity;

import hotel_interface.ClientProfile;
import hotel_interface.IClientData;

public class ClientProfileList implements IClientData {
	
	List<ClientProfile> clientProfileList = new List<ClientProfile>;
	
	puiblic ClientProfileList(String fileName) {
		
	}

	@Override
	public void addClientProfile(ClientProfile newClientProfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientProfile getClientProfile(String NRIC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean searchClientProfile(String NRIC) {
		// TODO Auto-generated method stub
		return false;
	}

}

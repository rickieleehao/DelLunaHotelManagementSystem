package hotel_entity;

import java.util.List;

import hotel_interface.IClientData;

public class ClientProfileList implements IClientData {
	
	List<ClientProfile> clientProfileList;
	
	public ClientProfileList(String fileName) {
		
	}

	@Override
	public void addClientProfile(hotel_entity.ClientProfile newClientProfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public hotel_entity.ClientProfile getClientProfile(String NRIC) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean searchClientProfile(String NRIC) {
		// TODO Auto-generated method stub
		return false;
	}
}

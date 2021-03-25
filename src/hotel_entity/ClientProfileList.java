package hotel_entity;

import java.util.List;

import hotel_interface.IClientData;

public class ClientProfileList implements IClientData {

	List<ClientProfile> clientProfileList;

	public ClientProfileList(String fileName) { // xz

	}

	@Override
	public void addClientProfile(hotel_entity.ClientProfile newClientProfile) { // xz
		// TODO Auto-generated method stub

	}

	@Override
	public ClientProfile getClientProfile(String NRIC) { // xz
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean searchClientProfile(String NRIC) { // xz
		// TODO Auto-generated method stub
		return false;
	}
}

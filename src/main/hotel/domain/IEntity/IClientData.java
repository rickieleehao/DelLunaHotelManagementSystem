package hotel.domain.IEntity;

import hotel.domain.entity.ClientProfile;

public interface IClientData {
	
	public ClientProfile getClientProfile(String NRIC);
	public void addClientProfile(ClientProfile newClientProfile);

}

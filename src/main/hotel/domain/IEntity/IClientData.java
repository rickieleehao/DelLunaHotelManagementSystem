package hotel.domain.IEntity;

import hotel.domain.entity.ClientProfile;

public interface IClientData {
	
	public void addClientProfile(ClientProfile newClientProfile);
	public ClientProfile getClientProfile(String NRIC);

}

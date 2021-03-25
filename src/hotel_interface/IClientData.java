package hotel_interface;

public interface IClientData {
	
	public void addClientProfile(ClientProfile newClientProfile);
	}
	
	public ClientProfile getClientProfile(String NRIC);

	public boolean searchClientProfile(String NRIC);
}

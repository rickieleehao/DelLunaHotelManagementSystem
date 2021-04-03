package hotel_entity;

import hotel_interface.IUser;

class isAdministrator {

	private String password;
	
	public boolean verifyAdmin(String password) {
		boolean verify = false;
		if(this.password.equals(password)) {
			verify = true;
		}
		return verify;
	}
}


public class User implements IUser {
	private UserType type;

	public User() {
		this.type = UserType.Client;
	}
	
	@Override
	public UserType getUserType() {
		return this.type;
	}
}

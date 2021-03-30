package hotel_entity;

import hotel_interface.IUser;

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

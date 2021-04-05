package hotel_interface;

import hotel_entity.UserType;

public interface IUser {

	public UserType getUserType();
	public void login(String password);
}

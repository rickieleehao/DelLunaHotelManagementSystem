package hotel.domain.IEntity;

import hotel.domain.entity.UserType;

public interface IUser {

	public UserType getUserType();
	public void login(String password);
}

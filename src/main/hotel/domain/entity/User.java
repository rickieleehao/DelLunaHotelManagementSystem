package hotel.domain.entity;

import hotel.domain.IEntity.IUser;

class isAdministrator {

	private String password;

	public isAdministrator() {
		this.password = "admin123";
	}

	public boolean verifyPassword(String password) {
		boolean verify = false;
		if (this.password.equals(password)) {
			verify = true;
		}
		return verify;
	}
}

public class User implements IUser {
	private UserType type;
	private isAdministrator adminInfo;

	public User() {
		this.type = UserType.Client;
		this.adminInfo = new isAdministrator();
	}

	@Override
	public UserType getUserType() {
		return this.type;
	}

	@Override
	public void login(String password) {
		if (this.adminInfo.verifyPassword(password)) {
			this.type = UserType.Administrator;
		} else {
			throw new IllegalArgumentException("Incorrect password!");
		}
	}
}

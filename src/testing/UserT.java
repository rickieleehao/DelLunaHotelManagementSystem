package testing;

public class UserT implements aUserT {

	private Type type;
	private isAdministratorT isAdmin;

	@Override
	public void login(String password) {
		if(isAdmin.verifyPassword(password) == true)
			this.type = Type.Administrator;
	}

	public Type getType() {
		return type;
	}

	public UserT() {
		isAdmin = new AdministratorT();
		this.type = Type.Client;
	}

}

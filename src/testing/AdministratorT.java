package testing;

public class AdministratorT implements isAdministratorT {

	private String password;

	@Override
	public boolean verifyPassword(String password) {
		if (password == this.password)
			return true;
		else
			return false;
	}

	public AdministratorT() {
		this.password = "admin123";
	}
}

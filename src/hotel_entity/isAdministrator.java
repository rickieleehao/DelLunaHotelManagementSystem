package hotel_entity;

public class isAdministrator {

	private String password;
	
	public boolean verifyAdmin(String password) {
		boolean verify = false;
		if(this.password.equals(password)) {
			verify = true;
		}
		return verify;
	}
}

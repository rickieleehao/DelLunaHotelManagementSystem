package testing;

public class ControllerT {

	private aUserT user;

	public void login() {
		InputT input = new InputT();
		String password = input.getInput("Enter password ----> ");
		user.login(password);
	}

	public Type getUserType() {
		return ((UserT) this.user).getType();
	}

	public ControllerT() {
		user = new UserT();
	}
}

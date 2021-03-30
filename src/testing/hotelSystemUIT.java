package testing;

public class hotelSystemUIT {

	private ControllerT controller;
	private InputT input;
	private aUserT user;

	

	public hotelSystemUIT() {
		controller = new ControllerT();
		input = new InputT();
		user = new UserT();
	}

	public void menuOptions() {
		// print options
		if (controller.getUserType() == Type.Administrator)
			//Administrator options
			;
		else
			//Client options
			;
			
	}

	public int selectOption() {
		
		int option = Integer.parseInt(input.getInput("Select Option ----> "));

		switch (option) {
		case 1:
			controller.login();
			break;
		default:
			break;
		}

		return option;
	}
}

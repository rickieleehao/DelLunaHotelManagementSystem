package testing;

public class hotelSystemT {
	
	private static hotelSystemUIT ui; // research
	
	public static void main(String[] args) {

		ui = new hotelSystemUIT();

		int option;
		
		// display 1.login 2.menuOptions

		do {
			ui.menuOptions();
			option = ui.selectOption();
		} while (option != 0);
	}
}

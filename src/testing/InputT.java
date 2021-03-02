package testing;

import java.util.Scanner;

public class InputT {
	Scanner scanner = new Scanner (System.in);
	
	public String getInput(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

}

import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class WritePoll {

	private Formatter output;
	
	public static void main(String[] args) {
		
		WritePoll wp = new WritePoll();
		wp.openFile();
		wp.userPrompt();
		wp.closeFile();
	}
	
	public void openFile() {
		
		try {
			output = new Formatter("numbers.txt"); 
		} catch (SecurityException se) {
			displayMessage("Cannot write to file. Terminating ...");
			displayMessage(se.getMessage());
			System.exit(1);
		} catch (FileNotFoundException fnf) {
			displayMessage("Error opening file. Terminating ...");
			displayMessage(fnf.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Method to prompt the user to enter poll information for 20 users
	 */
	private void userPrompt() {
		// open scanner object for user response
		Scanner scanner = new Scanner(System.in);
		
		int input = 0;
		
		// Initialize the student var, up to 20
		int student = 1;

		displayMessage("Enter rating (1-5). Press CTRL-D or CTRL-C to end input");
		// start asking for the answer
		// ask for 20 entries or until EOF (CTRL-D/CTRL-C) is entered
		do {
			try {
				
				System.out.printf("%s %d %s", "student", student, "vote: ");
				input = scanner.nextInt();
				
				while (!verifyInput(input)) {
					displayMessage("Rating out of range. Please, choose between (1-5)");
					System.out.printf("%s %d %s", "student", student, "vote: ");				
					input = scanner.nextInt();
				}
				

				output.format("%d%n", input);
				
			} catch (InputMismatchException im) {
				displayMessage("Wrong input type");
				break;
			} catch (FormatterClosedException fc) {
				displayMessage("Cannot write to file.");
				break;
			} catch (IllegalStateException is) {
				displayMessage("Scanner is closed.");
				break;
			}
			student++;
			
		} while (student <= 20);
		
		// close the scanner
		scanner.close();
	}
	
	/**
	 * Helper method to verify if input is in range (1-5)
	 * @param input the input as a digit between 1 and 5
	 * @return true if input is in range or false if not
	 */
	private boolean verifyInput(int input) {
		if (input >= 1 && input <= 5)
			return true;
		else
			return false;
	}

	/**
	 * Helper method to close the formatter object
	 */
	private void closeFile() {
		// if the formatter is not empty
		if (output != null) {
			output.close();
		}
	}
	
	
	/**
	 * Greeting message, that explains what the user should do
	 */
	private void greetingMessage() {
		String message = String.format("%s %d %s %d %s", 
				"Please, rate the food in the student cafeteria, with",
				1,
				"being \"awfulu\" and",
				5,
				"being \"excellent\"");
		
		displayMessage(message);
	}
	
	/**
	 * Generig method to display a message on the screen
	 * @param message the message do be displayed
	 */
	private void displayMessage(String message) {
		System.out.println(message);
	}
	
}

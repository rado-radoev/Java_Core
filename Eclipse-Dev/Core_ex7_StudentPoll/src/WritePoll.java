import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Class that will write user entered data into a file
 * @author Radoslav Radoev
 */
public class WritePoll {

	private Formatter output;
	
	// file name, by default it is named numbers.txt
	private String file = "numbers.txt";
	
	// main applicaion execution
	public static void main(String[] args) {
		
		WritePoll wp = new WritePoll();
		wp.greetingMessage();
		wp.openFile();
		wp.userPrompt();
		wp.closeFile();
	}
	
	// get file name
	public String getFile() {
		return file;
	}

	// set file name
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * Method to open file for writing, using Java Formatter object
	 */
	private void openFile() {
		// convert the fileName string into a path 
		Path path = Paths.get(getFile());
		
		// check if the file exists, and if it does not
		// create it. Throw an excepiton if it cannot be created
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException io) {
				System.out.println("Cannot create file.");
			}
		}
		
		// Try to open the file as a formatter object
		// If not, throw an error and exit application.
		try {
			output = new Formatter(getFile()); 
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
		
		// input control variable
		int input = 0;
		
		// Students (1-20)
		int student = 1;

		displayMessage("Enter rating (1-5). Press CTRL-D or CTRL-C to end input");
		
		// start asking for input
		// ask for 20 entries or until EOF (CTRL-D/CTRL-C) is entered
		do {
			try {
				
				System.out.printf("%s %d %s", "student", student, "vote: ");
				input = scanner.nextInt();
				
				// keep asking for the same input, if wrong number is entered
				while (!verifyInput(input)) {
					displayMessage("Rating out of range. Please, choose between (1-5)");
					System.out.printf("%s %d %s", "student", student, "vote: ");				
					input = scanner.nextInt();
				}

				// if input is valid, output to text file
				output.format("%d%n", input);
				
			// exceptions that can be thrown
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
			// move to next student
			student++;
			
		} while (student <= 20); // loop until students number reached 20
		
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
	 * Method to display instructions message, that explains to the user how to use the program
	 */
	private void greetingMessage(String...message) {
		if (message.length > 0) {
			displayMessage(message[0]);
		}
		else {
			greetingMessage(
					String.format("%s %d %s %d %s", 
					"Please, rate the food in the student cafeteria, with",
					1,
					"being \"awful\" and",
					5,
					"being \"excellent\""));
		}
		
	}
	
	/**
	 * Method to display a message on the screen
	 * @param message the message do be displayed
	 */
	private void displayMessage(String message) {
		System.out.println(message);
	}
	
}

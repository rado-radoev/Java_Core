import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		// information display message
		System.out.println("Enter sentence to be reversed:");
		
		// prompt the user to enter the string
		Scanner input = new Scanner(System.in);
		
		// reverse it
		reverseString(input.nextLine());
		
		// close the scanner object
		input.close();
	}
	
	/**
	 * Method to reverse a string, using space as delimiter
	 * @param str string to display in reversed order 
	 */
	public static void reverseString(String str) {		
		// tokenize the string, using space as delimiter
		String[] splitted = str.split(" ");
		
		// iterate over the string in reverse and display each word, separated by space
		for (int i = splitted.length - 1; i >= 0 ; i--) {
			System.out.print(splitted[i] + " ");
		}
	}

}

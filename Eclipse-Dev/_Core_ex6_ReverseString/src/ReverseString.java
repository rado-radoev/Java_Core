import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		System.out.println("Enter sentence to be reversed:");
		
		Scanner input = new Scanner(System.in);
			
		String[] splitted = input.nextLine().split(" ");
	
		input.close();
		
		for (int i = splitted.length - 1; i >= 0 ; i--) {
			System.out.print(splitted[i] + " ");
		}
		
		
	}

}

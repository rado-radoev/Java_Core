import java.io.*;

import sun.text.resources.ar.FormatData_ar_LB;

public class FormLetterGenerator {

	/**
	 * Method to generate a form letter
	 * @param isMale true if this is for a male
	 * @param String lastName the last name for the recipient
	 * @param String city the name of the city for the recipient
	 * @param eyeColor the eye color of the recipient
	 */
	public void writeLetter(String title, String lastName, String city, String eyeColor) {
		String fileName = lastName + "letter.txt";
		
		// try to open the file and write to it
		try {
			
			// create the buffered writer to use to write the file
			BufferedWriter writer =
					new BufferedWriter(new FileWriter(fileName));
			
			// write the beginning of the letter
			writer.write("Dear " + title + " " + lastName +", ");
			writer.newLine();
			writer.newLine();
			
			// write the body of the letter
			writer.write("I am writing to remind you of the offer" );
			writer.newLine();
			writer.write("that we sent to you last week. ");
			writer.write("Everyone in");
			writer.newLine();
			writer.write(city + " knows what an exceptional offer this is!");
			writer.newLine();
			writer.write("(Especially those with lovely eyes of " + eyeColor + "!)");
			writer.newLine();
			writer.write("We hope to hear from you soon");
			writer.newLine();
			writer.newLine();
			
			// write the ending
			writer.write("Sincerely,");
			writer.newLine();
			writer.write("I. M. Acrook");
			
			// close the file
			writer.close();
			
		} catch (Exception e) {
			System.out.println("Error writing to " + fileName);
		}
	}
	
	public static void main(String[] args) {
		FormLetterGenerator formLetterGenerator = 
				new FormLetterGenerator();
		formLetterGenerator.writeLetter("Mr.", "Radoev", "San Diego", "brown");
	}
}

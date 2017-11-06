import java.util.*;
import java.io.*;

/**
 * A class that represents a phone book. This phone book maps names to phone numbers.
 * this will  read the phone book information from a file.
 * @author superlamer
 */
public class PhoneBook {

	///////////////////////////// fields	/////////////////////////////
	private String fileName;
	private Map<String, String> phoneMap = 
			new HashMap<String, String>();
	
	///////////////////////////// constructors /////////////////////////////
	/**
	 * Constructor that takes a file name and reads in the names and phone numbers from a file
	 * @param file the name of the file to read
	 */
	 public PhoneBook(String file) {
		 fileName = file;
		 
		 // read the map information from the file
		 readInfoFromFile();
	 }
	 
	///////////////////////////// methods /////////////////////////////
	/**
	 * Get the phone number for the passed name
	 * @param name the name to look up in the map
	 * @return the phone number if found, else null 
	 */
	 public String getPhoneNumber(String name) {
		 String phoneNumber = phoneMap.get(name);
		 return phoneNumber;
	 }
	 
	 /**
	  * Method to read the phone information from a file 
	  * and use it to fill the map
	  */
	 public void readInfoFromFile() {
		 String line = null;
		 String[] phoneArray = null;
		 
		 try {
			BufferedReader reader = 
					new BufferedReader(new FileReader(fileName));
			
			// loop reading from the file 
			while ((line = reader.readLine()) != null) {
				if (line.indexOf(":") >= 0) {
					phoneArray = line.split(":");
					phoneMap.put(phoneArray[0].trim(), 
							phoneArray[1].trim());
				}
			}
			// close the reader
			reader.close();
		} catch (FileNotFoundException fnf) {
			SimpleOutput.showError("Couldn't find file " + fileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	 }
	 
	 public static void main(String[] args) {
		 PhoneBook phoneBokk = new PhoneBook(FileChooser.pickAFile());
		 System.out.println(phoneBokk.getPhoneNumber("Shayna"));
		 System.out.println(phoneBokk.getPhoneNumber("Dentist"));
	 }
	 
	 
}

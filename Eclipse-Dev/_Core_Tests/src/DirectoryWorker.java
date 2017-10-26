import java.io.File;

/**
 * Class to work with files in a directory 
 * @author superlamer
 *
 */
public class DirectoryWorker {

	/**
	 * Method to add a string to every picture in directory 
	 * @param dir the name of the directory
	 * @param text the text of the string to add
	 */
	public void addStringToPictures(String dir, String text) {
		String name = null;
		
		// create object that representes directory
		File file = new File(dir);
		
		// get the array of names in directory
		String[] nameArray = file.list();
		
		// loop through the names
		for (int i = 0; i < nameArray.length; i++) {
			name = nameArray[i];
			
			if (name.indexOf(".jpg") >= 0) {
				// create the picture object
				Picture p = new Picture(dir + name);
				
				// add text to the picture
				p.drawString(text, 5, p.getHeight() - 50);
				
				// save the changed picture to a file
				p.write(dir + "titled-" + name);
			}
		}
	}
	
	public static void main(String[] args) {

		DirectoryWorker worker = new DirectoryWorker();
		String dir = "//Users//superlamer//GitHub//Java_Core//Media sources//";
		worker.addStringToPictures(dir, "Copyright 2017");
		
	}

}

import java.io.*;
import java.time.temporal.TemporalField;

/**
 * Class used to write HTML (web) pages
 * @author superlamer
 */
public class WebPageWriter {

	/**
	 * Method to write the doctype and html tags
	 * @param writer the writer to use
	 * @throws IOException
	 */
	private void writeStart(BufferedWriter writer) throws IOException {
		// write the document type
		writer.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transition //EN\"");
		writer.newLine();
		writer.write("\"http://www.w3.org/TR/html14/loose.dtd\">");
		writer.newLine();
		
		// start the html page
		writer.write("<html>");
		writer.newLine();
	}
	
	/**
	 * Method to write the title out
	 * @param writer the writer to use
	 * @param title the title to use
	 * @throws IOException
	 */
	private void writeHead(BufferedWriter writer, String title) throws IOException {
		writer.write("<head><title>" + title + "</title>></head>");
		writer.newLine();
	}
	
	/**
	 * Method to write the body of the page
	 * @param writer the writer to use
	 * @param body the body to write
	 * @throws IOException
	 */
	private void writeBody(BufferedWriter writer, String body) throws IOException {
		writer.write("<body>" + body + "</body>");
		writer.newLine();
	}
	
	/**
	 * Method to finish the html page
	 * @param writer the writer to use
	 * @throws IOException
	 */
	private void writeEnd(BufferedWriter writer) throws IOException {
		writer.write("</html>");
	}
	
	/**
	 * Method to write a page with thumbnails of all the images in a directory
	 * @param directory the directory to create the page for
	 */
	public void createImagePage(String directory) {
		String name = null;
		String body = "";
		String endOfLine = System.getProperty("line.separator");
		
		// try the following
		try {
			// create the File object
			File dir = new File(directory);
			
			// get the full path name of the directory
			String pathName = directory + dir.getName() + ".html";
			BufferedWriter writer =
					new BufferedWriter(new FileWriter(pathName));
			
			// write the start
			writeStart(writer);
			
			// write the head
			writeHead(writer, "Thumbnails from " + directory);
			
			// get the array of item the directory
			String[] items = dir.list();
			
			// loop through the array
			for (int i = 0; i < items.length;i++) {
				name = items[i];
				
				if (name.indexOf(".jpg") >= 0) {
					body += "<p>FileName: " + name + "<img src='" + name + "'height=150'/></p>" + endOfLine;
				}
			}
			
			// write the body 
			writeBody(writer, body);
			
			// write the end
			writeEnd(writer);
			
			// close the writer
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		WebPageWriter writer = new WebPageWriter();
		String dir = FileChooser.pickADirectory();
		writer.createImagePage(dir);
	
	}
}





































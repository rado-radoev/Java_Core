import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;

public class ReadPoll {

	private Formatter output;
	private String file = "numbers.txt";
	
	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}
	
	public static void main(String[] args) {
		ReadPoll rp = new ReadPoll();
		
		//rp.openFile();
		rp.outputResults();
		rp.closeFile();
	}


	public void openFile() {
		Path path = Paths.get(file);
		
		if (!Files.exists(path)) {
			System.out.println("File does not exist. Try creating it first. Terminating ...");
			System.exit(1);
		}
		
		try {
			output = new Formatter(file);
		} catch (FileNotFoundException fnf) {
			System.out.println("File does not exist. Try creating it first. Terminating ...");
			System.exit(1);
		} catch (SecurityException se) {
			System.out.println("Cannot access the file. Terminating ...");
			System.exit(1);
		}
	}
	
	public void outputResults() {
		int[] answers = readFile();
		for (int i = 0; i < answers.length; i++) {
			System.out.println("Answer " + i + " is: " + answers[i]);
		}
	}
	
	
	public int[] readFile() {
		int[] frequencey = new int[6];	// frequency counter
		
	
		try (Scanner scanner = new Scanner(Paths.get(getFile()))) {
			while (scanner.hasNext()) {
				int num = scanner.nextInt();
				frequencey[num]++;
			}
		} catch (IllegalStateException ise) {
			System.out.println("Error reading file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// foreach answer, select response element and use that value
		// as frequency index to determine elemnt to incrmement
		
		return frequencey;
	}
	
	
	public void closeFile() {
		if (output != null) {
			output.close();
		}
	}
}

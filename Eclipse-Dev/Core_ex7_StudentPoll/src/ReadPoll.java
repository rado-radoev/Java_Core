import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;

public class ReadPoll {

	private String file = "numbers.txt";
	
	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}
	
	public static void main(String[] args) {
		ReadPoll rp = new ReadPoll();
		
		rp.checkFile();
		rp.outputResults();
	}


	public void checkFile() {
		Path path = Paths.get(file);
		
		if (!Files.exists(path)) {
			System.out.println("File does not exist. Try creating it first. Terminating ...");
			System.exit(1);
		}
	}
	
	public void outputResults() {
		int[] answers = readFile();
		for (int i = 1; i < answers.length; i++) {
			System.out.println("Answer " + i + " voted: " + answers[i] + " times");
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

}

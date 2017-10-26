import java.io.File;

public class PackagesTest {

	public static void main(String[] args) {
		File dir = new File("//Users//superlamer//GitHub//Java_Core//Media Computation book source");
		String[] pathArray = dir.list();
		for (int i = 0; i < 5; i++) System.out.println(pathArray[i]);
		
	}

}

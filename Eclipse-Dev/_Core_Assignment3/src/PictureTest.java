
public class PictureTest {

	public static void main(String[] args) {
		Picture pic = new Picture(
				"C:\\GitHub\\Java_Core\\Media Computation book source\\mediasources-no-movies-7-30-06\\intro-prog-java\\mediasources\\barbara.jpg");
		pic.blurr(83, 73, 100, 100, 10);
		pic.explore();
	}

}

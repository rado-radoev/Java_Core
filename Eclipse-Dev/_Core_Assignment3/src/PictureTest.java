import java.awt.Color;

public class PictureTest {

	public static void main(String[] args) {
		Picture pic = new Picture("barbara.jpg");
		//pic.blur(83, 73, 100, 100, 10);
		//pic.edgeDetection(Color.WHITE, Color.BLACK, 2, 0, pic.getWidth() - 1, 0, pic.getHeight() - 1);
		//pic.changePixels();
		pic.pixelate(5);
		pic.explore();
	}

}

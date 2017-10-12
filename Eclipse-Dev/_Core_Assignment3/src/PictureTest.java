
public class PictureTest {

	public static void main(String[] args) {
		Picture pic = 
				new Picture("barbara.jpg");
		pic.blurr(83, 73, 100, 100, 2);
		pic.explore();
	}

}

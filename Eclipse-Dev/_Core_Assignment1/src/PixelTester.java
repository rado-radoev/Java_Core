import java.awt.Color;

public class PixelTester {

	public static void main(String[] args) {
		//FileChooser.setMediaPath("/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/");
		String fileName = FileChooser.getMediaPath("640x480.jpg");
		Picture paperPicture = new Picture(fileName);
		paperPicture.blendPictures();
		paperPicture.show();
	}
	
	public static void testMethod7() {
		String fileName = "/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/beach-smaller.jpg";
		Picture picture = new Picture(fileName);
		picture.modifyRGB(1.0, 0.7, 1.5);;
		picture.show();
	}
	
	public static void testMethod6() {
		String fileName = "/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/caterpillar.jpg";
		Picture picture = new Picture(fileName);
		picture.clearBlue();
		picture.show();
	}
	
	public static void testMethod5() {
		String fileName = "/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/caterpillar.jpg";
		Picture picture = new Picture(fileName);
		picture.show();
		Pixel[] pixelArray = picture.getPixels();
		Pixel pixel = null;
		int index = 0;
		while (index < pixelArray.length) {
			pixel = pixelArray[index];
			pixel.setColor(Color.BLACK);
			index+=10;
		}
		picture.repaint();
	}
	
	public static void testMethod4() {
		String fileName = "/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/caterpillar.jpg";
		Picture picture = new Picture(fileName);
		picture.show();
		picture.getPixel(10, 100).setColor(Color.black);
		picture.getPixel(11, 100).setColor(Color.black);
		picture.getPixel(12, 100).setColor(Color.black);
		picture.getPixel(13, 100).setColor(Color.black);
		picture.getPixel(14, 100).setColor(Color.black);
		picture.getPixel(15, 100).setColor(Color.black);
		picture.getPixel(16, 100).setColor(Color.black);
		picture.getPixel(17, 100).setColor(Color.black);
		picture.getPixel(18, 100).setColor(Color.black);
		picture.getPixel(19, 100).setColor(Color.black);
		picture.repaint();
		picture.explore();
	}
	
	public static void testMethod3() {
		Color pickedColorObj = ColorChooser.pickAColor();
		System.out.println(pickedColorObj);
	}
	
	public static void testMethod2() {
		Color testColorObject = new Color(168,131,105);
		System.out.println(testColorObject);
	
		testColorObject = testColorObject.darker();
		System.out.println(testColorObject);

		testColorObject = testColorObject.brighter();
		System.out.println(testColorObject);
	
	}
	
	public static void testMethod() {
		String fileName = "/Users/superlamer/GitHub/Java_Core/Media Computation book source/mediasources-no-movies-7-30-06/intro-prog-java/mediasources/caterpillar.jpg";
		Picture pictureObject = new Picture(fileName);
		
		Pixel pixelObject =  pictureObject.getPixel(0, 0);
		
		System.out.println(pixelObject);
		
		Pixel[] pixelArray = pictureObject.getPixels();
		System.out.println(pixelArray[1]);
		
		System.out.println("x: " + pixelObject.getX() + " y: " + pixelObject.getY());
		
		System.out.println(pixelObject.getRed());
		pixelObject.setRed(0);
		System.out.println(pixelObject.getRed());
		
		Color colorObject = pixelObject.getColor();
		System.out.println(colorObject);
		pixelObject.setColor(new Color(0,100,0));
		System.out.println(pixelObject.getColor());
	
	
		System.out.println(pictureObject.getPixel(0, 0));
		
		pictureObject.write(fileName + ".jpeg");
	}
}

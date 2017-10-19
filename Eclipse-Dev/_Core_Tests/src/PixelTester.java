import java.awt.Color;

public class PixelTester {

	public static void main(String[] args) {
		Picture pic = 
				new Picture("640x480.jpg");
		pic.drawRainbow();
		pic.explore();
	}
	
	public static void testMethod25() {
		Picture pic = 
				new Picture("blue-mark.jpg");

		pic.drawConversationBubble();
		pic.explore();
	}
	
	public static void testMethod24() {
		Picture house = 
				new Picture("640x480.jpg");
		house.drawHouse();
		house.explore();
	}
	
	public static void testMethod23() {
		Picture pic = 
				new Picture("blue-mark.jpg");
		Picture newBg = 
				new Picture("beach.jpg");
		Picture halo = 
				new Picture("halo_clean_background_cropped.jpg");
		Picture newBg1 = new Picture(pic.getWidth(), pic.getHeight());
		
		//pic.chromakey(newBg);
		//pic.explore();
		
		newBg1.chromakey(halo, pic, 262, 89, halo.getWidth(), halo.getHeight()).explore();
		//halo.explore();
	}
	
	public static void testMethod22() {
		Picture pic = 
				new Picture("C:\\GitHub\\Java_Core\\Media Computation book source\\mediasources-no-movies-7-30-06\\intro-prog-java\\mediasources\\blue-mark.jpg");
		Picture newBg1 = 
				new Picture("C:\\GitHub\\Java_Core\\Media Computation book source\\mediasources-no-movies-7-30-06\\intro-prog-java\\mediasources\\halo_clean_background.jpg");
		pic.chromakeyShorter(newBg1,283,171, newBg1.getWidth(), newBg1.getHeight());
		pic.explore();
	}
	
	public static void testMethod21() {
		Picture pic1 = 
				new Picture("KatieFancy.jpg");
		Picture pic2 =
				new Picture("JenParty.jpg");
		Picture pic3 = 
				new Picture("640x480.jpg");
		pic3.overlapPictures(pic1, pic2, 150);
		pic3.explore();
	}
	
	public static void testMethod20() {
		Picture pic = 
				new Picture("grayMotorcycle.jpg");
		pic.drawWideX(Color.RED, 5);
		pic.show();
	}
	
	public static void testMethod19() {
		Picture pic = 
				new Picture("640x480.jpg");
		pic.drawRectangles();
		pic.show();
	}
	
	public static void testMethod18() {
		Picture pic = 
				new Picture("barbara.jpg");
		pic.blur(20);
		pic.explore();
	}
	
	public static void testMethod17() {
		Picture pic = 
				new Picture("whiteFlower.jpg");
		Picture newPic = 
				new Picture(pic.getWidth(), pic.getHeight());
		
		pic.copyAllButWhite(newPic);
		newPic.explore();
	}
	
	public static void testMethod16() {
		Picture pic = 
				new Picture("blue-mark.jpg");
		Picture newBg = 
				new Picture("moon-surface.jpg");
		Picture newBg1 = 
				new Picture("beach.jpg");
		pic.chromakeyShorter(newBg1);
		
		pic.explore();
	}
	
	public static void testMethod15() {
		Picture pic = 
				new Picture("twoKidsWall.jpg");
		Picture oldBg =
				new Picture("wall2.jpg");
		Picture newBg = 
				new Picture("bridge.jpg");
		pic.swapBackground(oldBg, newBg, 50);
		
		pic.explore();
	}
	
	public static void testMethod14() {
		Picture pic = 
				new Picture("flower1.jpg");
		pic.highlightLightAndDark(50d, Color.RED);
		pic.explore();
	}
	
	public static void testMethod13() {
		Picture pic = 
				new Picture("butterfly1.jpg");
		pic.highlightLightAndDark(50d, Color.RED);
		pic.explore();
	}
	
	public static void testMethod12() {
		Picture pic = 
				new Picture("gorge.jpg");
		pic.posterize(1);
		pic.explore();
	}
	
	public static void testMethod11() {
		Picture pic = 
				new Picture("jenny-red.jpg");
		pic.removeRedEye(109, 91, 202, 107, Color.BLACK);
		pic.explore();
	}
	
	public static void testMethod10() {
		Picture pic = 
				new Picture("butterfly1.jpg");
		pic.edgeDetection(10);
		pic.explore();
	}
	
	public static void testMethod9() {
		Picture barbara =
				new Picture("barbara.jpg");
		Picture pic =
				new Picture();
				
		pic.copyPictureRotateLeft(barbara).explore();;
	}
	
	public static void testMethod8() {
		Picture picture = new Picture(FileChooser.getMediaPath("flower1.jpg"));
		picture.scaleUp(20).explore();
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

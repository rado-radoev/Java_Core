
public class SoundTester {

	public static void main(String[] args) throws SoundException {

		//String silence = FileChooser.getMediaPath("Elliot-hello.wav");
		Sound target = new Sound("http://stream.metacast.eu/nrj.ogg");
		//target.explore();
		//target.blockingPlay();
		//target.changeFreq(0.75);
		//target.explore();
		target.play();
		
//		String fileName = FileChooser.getMediaPath("thisisatest.wav");
//		Sound sound1 = new Sound(fileName);
//		
//		Sound sound2Louder = sound1.clip(0, 8500);
//		sound2Louder.play();
//		sound2Louder.explore();
		
		//sound2Louder.increaseVolume();
		
		//sound2Louder.explore();
//		sound2Louder.halveVolume();
//		sound2Louder.explore();
//		
//		sound2Louder.play();
				
		
//		sound2Louder.writeToFile(FileChooser.getMediaPath("gettysburg10-louder.wav"));
		
//		Sound sound3 = new Sound(FileChooser.getMediaPath("gettysburg10-louder.wav"));
//		System.out.println(sound3);
		//sound1.blockingPlay();
		//sound1.explore();
//		System.out.println(sound3.getSampleValueAt(0));
//		sound3.changeVolume(.2);
//		System.out.println(sound3.getSampleValueAt(0));
//		sound3.blockingPlay();
		
		//sound1.increaseVolume();
		//sound1.blockingPlay();
		//sound1.explore();
		
		
	}
	
	 public static void method1() {
		FileChooser.setMediaPath("//Users//superlamer//GitHub//Java_Core//Media Computation book source//mediasources-no-movies-7-30-06//intro-prog-java//mediasources//");
		String fileName = FileChooser.getMediaPath("preamble.wav");
		Sound sound1 = new Sound(fileName);
		//System.out.println(sound1);
		
		//System.out.println(sound1.getSampleValueAt(0));
		//System.out.println(sound1.getSampleValueAt(1));
		System.out.println(sound1.getLength());
		System.out.println(sound1.getSamplingRate());
		
		sound1.write("//Users//superlamer//test.wav");
	 }
	
	
}

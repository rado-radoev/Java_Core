
/**
 * Class that represents a sound. This class is used by the students to extend
 * the capabilities of SimpleSound.
 * 
 * Copyright Georgia Institute of Technology 2004
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Sound extends SimpleSound {

	/////////////// consructors ////////////////////////////////////

	/**
	 * Constructor that takes a file name
	 * 
	 * @param fileName
	 *            the name of the file to read the sound from
	 */
	public Sound(String fileName) {
		// let the parent class handle setting the file name
		super(fileName);
	}

	/**
	 * Constructor that takes the number of samples in the sound
	 * 
	 * @param numSamples
	 *            the number of samples desired
	 */
	public Sound(int numSamples) {
		// let the parent class handle this
		super(numSamples);
	}

	/**
	 * Constructor that takes the number of samples that this sound will have and
	 * the sample rate
	 * 
	 * @param numSamples
	 *            the number of samples desired
	 * @param sampleRate
	 *            the number of samples per second
	 */
	public Sound(int numSamples, int sampleRate) {
		// let the parent class handle this
		super(numSamples, sampleRate);
	}
	/**
	 * Constructor that takes a sound to copy
	 * @param copySound the sound to copy
	 */
	public Sound(Sound copySound) {
		// let the parent class handle this
		super(copySound);
	}

	////////////////// methods ////////////////////////////////////

	/**
	 * Method to return the string representation of this sound
	 * 
	 * @return a string with information about this sound
	 */
	public String toString() {
		String output = "Sound";
		String fileName = getFileName();

		// if there is a file name then add that to the output
		if (fileName != null)
			output = output + " file: " + fileName;

		// add the length in frames
		output = output + " number of samples: " + getLengthInFrames();

		return output;
	}

	public static void main(String[] args) {
		// Uncomment below line and set this path to where your media files are located
		// FileChooser.setMediaPath("");

		// Sound object that will be manipulated
		Sound sound1 = new Sound(FileChooser.getMediaPath("oink.wav"));
		Sound sound2 = new Sound(FileChooser.getMediaPath("meow.wav"));

		// generate a college WAV file, using two sounds
		collage(sound1, sound2);

		// increase the volume (amplitude) of a sound
		sound2.increaseVolume();
		sound2.blockingPlay();

		// halve the volume of positive values and double the volume of negative values
		sound1.halvePosDoubleNeg();
		sound1.blockingPlay();
	}

	/**
	 * Method to make an audio collage. Makes copy of two different sounds and
	 * mirrors the first one. Splices together the original souds and the modified
	 * sound to make an audio collage.
	 * 
	 * @param sound1
	 *            first sound to splice and mirror
	 * @param sound2
	 *            second sound to splice
	 */
	public static void collage(Sound sound1, Sound sound2) {
		// temp Sound that will be written to disk
		Sound temp = new Sound((sound1.getLength() * 2) + sound2.getLength());

		// make copy of first sound
		Sound s = new Sound(sound1);

		// mirror it
		s.mirror();

		// splice original sound1 and sound2
		temp.splice(sound1, 0, sound1.getLength(), 0);
		temp.splice(sound2, 0, sound2.getLength(), sound1.getLength());

		// add the mirrored sound to the end
		temp.splice(s, 0, s.getLength(), sound1.getLength() + sound2.getLength());

		// write the file to disk
		temp.write(FileChooser.getMediaPath("collage.wav"));
	}

	/**
	 * Method to copy part of the passed sound into this sound at the given start
	 * index
	 * 
	 * @param source
	 *            the source sound to copy from
	 * @param sourceStart
	 *            the starting index to copy from in the source (the copy will
	 *            include this)
	 * @param sourceStop
	 *            the stopping index to copy to in the source (the copy will not
	 *            include this)
	 * @param targetStart
	 *            the index to start copying to
	 */
	public void splice(Sound source, int sourceStart, int sourceStop, int targetStart) {
		// loop copying from source to target
		for (int sourceIndex = sourceStart, targetIndex = targetStart; sourceIndex < sourceStop
				&& targetIndex < getLength(); sourceIndex++, targetIndex++) {
			setSampleValueAt(targetIndex, source.getSampleValueAt(sourceIndex));
		}
	}

	/**
	 * Method to mirror a sound front to back
	 */
	public void mirror() {
		int mirrorPoint = getLength() / 2; // set mirror point to the middle of the sound
		int value = 0;

		// loop from 0 to mirror point
		for (int i = 0; i < mirrorPoint; i++) {
			value = getSampleValueAt(i);
			setSampleValueAt(getLength() - 1 - i, value);
		}
	}

	/**
	 * Method that halves the value of positive values and doubles the value of
	 * negative values
	 */
	public void halvePosDoubleNeg() {
		SoundSample[] sampleArray = getSamples();
		int value = 0;

		for (int i = 0; i < sampleArray.length; i++) {
			value = sampleArray[i].getValue();

			if (value >= 0) {
				sampleArray[i].setValue((int) (value / 2));
			} else {
				sampleArray[i].setValue((int) (value * 2));
			}
		}
	}

	/**
	 * Method to increase the volume (amplitude) of the sound, using foreach loop
	 */
	public void increaseVolume() {
		SoundSample[] sampleArray = getSamples();
		int value = 0;

		// loop through all the samples in the array
		for (SoundSample soundSample : sampleArray) {
			value = soundSample.getValue();
			soundSample.setValue(value * 2);
		}
	}

} // this } is the end of class Sound, put all new methods before this
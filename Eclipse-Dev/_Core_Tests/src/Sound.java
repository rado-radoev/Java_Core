
/**
 * Class that represents a sound.  This class is used by the students
 * to extend the capabilities of SimpleSound. 
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Sound extends SimpleSound
{
  
  /////////////// consructors ////////////////////////////////////
  
  /**
   * Constructor that takes a file name
   * @param fileName the name of the file to read the sound from
   */
  public Sound(String fileName)
  {
    // let the parent class handle setting the file name
    super(fileName);
  }
  
  /**
   * Constructor that takes the number of samples in
   * the sound
   * @param numSamples the number of samples desired
   */
  public Sound (int numSamples)
  {
    // let the parent class handle this
    super(numSamples);
  }
  
  /**
   * Constructor that takes the number of samples that this
   * sound will have and the sample rate
   * @param numSamples the number of samples desired
   * @param sampleRate the number of samples per second
   */
  public Sound (int numSamples, int sampleRate)
  {
    // let the parent class handle this
    super(numSamples,sampleRate);
  }
  
  /**
   * Constructor that takes a sound to copy
   */
  public Sound (Sound copySound)
  {
    // let the parent class handle this
    super(copySound);
  }
  
  ////////////////// methods ////////////////////////////////////
  
  /**
   * Method to return the string representation of this sound
   * @return a string with information about this sound
   */
  public String toString()
  {
    String output = "Sound";
    String fileName = getFileName();
    
    // if there is a file name then add that to the output
    if (fileName != null)
      output = output + " file: " + fileName;
    
    // add the length in frames
    output = output + " number of samples: " + getLengthInFrames();
    
    return output;
  }
  
  /**
   * Method to mirror a sound front to back
   */
  public void mirrorFrontToBack() {
	  int length = getLength(); // save the length 
	  int mirrorPoint = length / 2;  // mirror around this
	  int value = 0; // hold the current value
	  
	  // loop from 0 to mirror point
	  for (int i = 0; i < mirrorPoint; i++) {
		  value = getSampleValueAt(i);
		  setSampleValueAt(length - 1 - i, value);
	  }
  }
  
  /**
   * Method to reverse the current sound
   */
  public void reverese() {
	  Sound orig = new Sound(getFileName());
	  int length = getLength();
	  
	  // loop through the samples
	  for (int targetIndex = 0, sourceIndex = length - 1; 
			  targetIndex < length && sourceIndex > 0;
			  targetIndex++, sourceIndex--) {
		  setSampleValueAt(targetIndex, orig.getSampleValueAt(sourceIndex));
	  }
  }
  
  /**
   * Method to clips part of a sound file
   * @param start the start index of the clip to cut
   * @param end the end index of the clip to cut
   * @return only the cutted clip
   */
  public Sound clip(int start, int end) {
	  // calculate the number of samples in the clip
	  int lengthInSamples = end - start + 1;
	  Sound target = new Sound(lengthInSamples); // hold clip
	  int value = 0; // holds the current sample
	  int targetIndex = 0;  // index in target sound
	  
	  // copy from start to end from source into target
	  for (int i = start; i <= end; i++, targetIndex++) {
		  value = getSampleValueAt(i);
		  target.setSampleValueAt(targetIndex, value);
	  }
	  
	  return target;
  }
  
  /**
   * Method to copy part of the passed sound into this sound at the given start index
   * @param source the source sound to copy from
   * @param soureStart the starting index to copy from in the source (the copy will include this)
   * @param sourceStop the stopping index to copy to in the source (the copy will not include this)
   * @param targetStart the index to start copying to
   */
  public void splice(Sound source, int sourceStart, int sourceStop, int targetStart) {
	 // loop copying from source to target
	  for (int sourceIndex = sourceStart, targetIndex = targetStart; 
			  sourceIndex < sourceStop && targetIndex < getLength();
			  sourceIndex++, targetIndex++) {
		  setSampleValueAt(targetIndex, getSampleValueAt(sourceIndex));
	  }
  }
  
  /**
   * Method to splice two sounds together with some silence
   * between them into the current sound
   */
  public void splice() {
	  Sound sound1 =
			  new Sound(FileChooser.getMediaPath("guzdial.wav"));
	  Sound sound2 = 
			  new Sound(FileChooser.getMediaPath("is.wav"));
	  int targetIndex = 0; // the starting place on the target
	  int value = 0;
	  
	  // copy all of sound 1 into the current sound (target)
	  for (int i = 0; i < sound1.getLength(); i++, targetIndex++) {
		  value = sound1.getSampleValueAt(i);
		  setSampleValueAt(targetIndex, value);
	  }
	  
	  // create silence between words by setting values to 0
	  for (int i = 0; i < ((int) (getSamplingRate() * .5)); i++, targetIndex++) {
		  setSampleValueAt(targetIndex, value);
	  }
	  
	  // copy all of sound 2 into the current sound (target)
	  for (int i = 0; i < sound2.getLength();i++, targetIndex++) {
		  value = sound2.getSampleValueAt(i);
		  setSampleValueAt(targetIndex, value);
	  }
  }
  
  /**
   * Method that will halve the positive values and double 
   * the volume of the negative values
   */
  public void halveVolume() {
	  SoundSample[] sampleArray = getSamples();
	  
	  for (SoundSample soundSample : sampleArray) {
		if (soundSample.getValue() >= 0)
			soundSample.setValue((int) (soundSample.getValue() / 2));
		else
			soundSample.setValue((int)(soundSample.getValue() * 2));
	}
  }
  
  
  /**
   * Method to set all the sample values to the maximum positive value if they were
   * positive (including 0) and the minimum negative value if thye were negative
   */
  public void forceToExtreme() {
	  SoundSample[] sampleArray = getSamples();
	  SoundSample sample;
	  
	  // loop through the sample values
	  for (int i = 0; i < sampleArray.length; i++) {
		  // get the current sample
		  sample = sampleArray[i];
		  
		  // if the value is positive or zero set it to the maximum positive value
		  if (sample.getValue() >= 0)
			  sample.setValue(32767);
		  // else force to max negative
		  else 
			  sample.setValue(-32768);
	  }
  }
  
  
  
  /**
   * Method to normalize (make as lound as possible) a sound
   */
  public void normalize() {
	  int largest = 0;
	  int maxIndex = 0;
	  SoundSample[] sampleArray = getSamples();
	  SoundSample sample;
	  int value = 0;
	  
	  // loop comparing the absolute value of the current value
	  // to the current largest
	  for (int i = 0; i < sampleArray.length; i++) {
		  sample = sampleArray[i];
		  value = Math.abs(sample.getValue());
		  if (value > largest) {
			  largest = value;
			  maxIndex = i;
		  }
	  }
	  
	  // now calculate the multiplier to multiply by
	  double multiplier = 32767.0 / largest;
	  
	  // print the largest value and the multiplier
	  System.out.println("The largest value was " + largest + 
			  " at index " + maxIndex);
	  System.out.println("The multipler is: " + multiplier);
	  
	  // loop through all the samples and multiply by multipliers
	  for (int i = 0; i < sampleArray.length; i++) {
		  sample = sampleArray[i];
		  sample.setValue( (int) (sample.getValue() * multiplier) );
	  }
  }
  
  /**
   * Method to change the volume (amplitude) of the sound
   * by multiplying the current values in the sound by the passed factor
   * @param factor the factor to multiply by
   */
  public void changeVolume(double factor) {
	  SoundSample[] sampleArray = getSamples();
	  SoundSample sample;
	  
	  for (int i = 0; i < sampleArray.length; i++) {
		  sample = sampleArray[i];
		  sample.setValue((int) (sample.getValue() * factor));
	  }
	  
  }
  
  /**
   * Method to halve the volume (amplitude) of the sound
   */
  public void decreaseVolume() {
	  SoundSample[] sampleArray = getSamples();
	  SoundSample sample;
	  int index = 0, value = 0;
	  
	  // loop through all the samples in the array
	  sample = sampleArray[index];
	  value = sample.getValue();
	  sample.setValue((int) (value * 0.5));
	  index++;
	  
  }
  
  /**
   * Method to double the volume (amplitude) of the sound
   */
  public void increaseVolume() {
	  SoundSample[] sampleArray = getSamples();
	  SoundSample sample;
	  int value = 0, index = 0;
	  
	  // loop through all the samples in the array
	  while (index < sampleArray.length) {
		  sample = sampleArray[index];
		  value = sample.getValue();
		  sample.setValue(value * 2);
		  index++;
	  }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 
  public static void main(String[] args)
  {
    Sound sound1 = new Sound(FileChooser.pickAFile());
    sound1.explore();
  }
             
} // this } is the end of class Sound, put all new methods before this
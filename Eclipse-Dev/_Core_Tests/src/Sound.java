
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
   * Method to change the frequency of a sound by the passed factor
   * @param factor the amount to increment the source index by. A number 
   * greater than 1 will increase the frequency and the sound higher while a number
   * less than one will decrease the frequency and make the sound lower.
   */
  public void changeFreq(double factor) {
	  // make a copy of the original sound
	  Sound s = new Sound(getFileName());
	  
	  /* loop through the sound and increment the target index
	   * by one but increment the source index by the factor
	   */
	  for (double sourceIndex = 0, targetIndex = 0;
			  targetIndex < this.getLength();
			  sourceIndex += factor, targetIndex++) {
		  if (sourceIndex >= s.getLength()) 
			  sourceIndex = 0;
		  
		  setSampleValueAt((int) targetIndex, s.getSampleValueAt((int)sourceIndex));
	  }
  }
  
  /**
   * Method to double the frequency of a sound by taking
   * every second sample. The result will be a higher sound
   */
  public void doubleFreq() {
	  // make a copy of the original sound
	  Sound s = new Sound(getFileName());
	  
	  /*
	   * Loop through the sound and increment target index
	   * by one but source index by 2 and set target value
	   * to the copy of the original sound
	   */
	  for (int sourceIndex = 0, targetIndex = 0; sourceIndex < getLength();
			  sourceIndex += 2, targetIndex++) {
		  setSampleValueAt(targetIndex, s.getSampleValueAt(sourceIndex));
	  }
	  
	  // clear out the rest of this sound
	  for (int i = getLength() / 2; i < getLength();i++) {
		  setSampleValueAt(i , 0);
	  }
  }
  
  /**
   * Method to create multiple echoes of the current sound
   * @param delay the number of samples before the echo starts
   * @param numEchoes number of echoes desired
   * @return a new sound with the echoes in t
   */
  public Sound echo(int delay, int numEchoes) {
	  int soundLength = getLength();
	  Sound echoSound = new Sound(numEchoes * delay + soundLength);
	  int value, echoIndex, echoValue;
	  value = echoIndex = echoValue = 0;
	  double echoAmplitude = 1; // to start
	  
	  // copy the original sound
	  echoSound.splice(this, 0, soundLength, 0);

	  // loop starting with 1 to create the first echo at the
	  // right place and end when = the number of echoes
	  for (int echoCount = 1; echoCount <= numEchoes; echoCount++) {
		  // decrease the volume (amplitude) of the echo
		  echoAmplitude *= 0.6;
		  
		  // echo the whole sound
		  for (int i = 0; i < soundLength; i++) {
			  echoIndex = i + (delay * echoCount);
			  echoValue = (int) (getSampleValueAt(i) * echoAmplitude);
			  echoSound.setSampleValueAt(echoIndex, echoValue + 
					  echoSound.getSampleValueAt(echoIndex));
		  }
	  }
	  
	  return echoSound;
  }
  
  
  /**
   * Method to add an echo to a sound
   * @param delay the number of samples before the echo starts
   */
  public void echo(int delay) {
	  // make a copy of the original sound
	  Sound s = new Sound(getFileName());
	  int value = 0;
	  
	  // loop from delay to end of sound
	  for (int i = delay; i < s.getLength(); i++) {
		  // get the values back by delay samples from the copy of the sound
		  // and make it fainter
		  value = (int) (s.getSampleValueAt(i - delay) * 0.6);
		  
		  // set the value at the current index to the sum
		  // of the current value and the echo
		  setSampleValueAt(i, getSampleValueAt(i) + value);
	  }
  
  }
  

  
  /**
   * Method to overlap or blend two sounds. Start by copying the first 20,000 samples
   * from sound1 into the current sound then copy the sum of half of sound1 and half of sound2
   * for the next 20,000 samples and end with the next 20,000 samples from sound2.
   */
  public void blendSounds() {
	  Sound sound1 = 
			  new Sound(FileChooser.getMediaPath("aah.wav"));
	  Sound sound2 = 
			  new Sound(FileChooser.getMediaPath("bassoon-c4.wav"));
	  int value = 0;
	  
	  // copy the first 20,000 samples from sound1 into current 
	  for (int i = 0; i < 20000; i++) {
		  setSampleValueAt(i, sound1.getSampleValueAt(i));
	  }
	  
	  // copy the next 20,000 samples from sound1 and blend that with the first 20,000
	  // samples from sound2
	  for (int i = 0; i < 20000;i++) {
		  value = (int) ((sound1.getSampleValueAt(i + 2000) * 0.5) + 
				  (sound2.getSampleValueAt(i) * 0.5));
		  
		  setSampleValueAt(i + 2000, value);
	  }
	  
	  // copy the next 20,000 samples from sound2 into the target
	  for (int i = 2000; i < 40000; i++) {
		  setSampleValueAt(i + 20000, sound2.getSampleValueAt(i));
		  
	  }
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
		  setSampleValueAt(targetIndex, source.getSampleValueAt(sourceIndex));
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
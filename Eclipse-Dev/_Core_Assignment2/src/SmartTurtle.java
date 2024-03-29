
public class SmartTurtle extends Turtle {
	
   /** Constructor that takes the x and y and a picture to
   * draw on
   * @param x the starting x position
   * @param y the starting y position
   * @param picture the picture to draw on
   */
	public SmartTurtle(int x, int y, Picture picture) {
		// let the parent constructor handle it
		super(x,y,picture);
	}
	
   /** Constructor that takes the x and y and a model
   * display to draw it on
   * @param x the starting x position
   * @param y the starting y position
   * @param modelDisplayer the thing that displays the model
   */
	public SmartTurtle(int x, int y, ModelDisplay modelDisplay) {
		// let the parent constructor handle it
		super(x, y, modelDisplay);
	}
	
   /** Constructor that takes the model display
   * @param modelDisplay the thing that displays the model
   */
	public SmartTurtle(ModelDisplay modelDisplay) {
		// let the parent constructor handle it
		super(modelDisplay);
	}
	
   /**
   * Constructor that takes a picture to draw on
   * @param p the picture to draw on
   */	
	public SmartTurtle(Picture p) {
		// let the parent constructor handle it
		super(p);
	}
	
	@Override
	// Overridden turtle method that draws a hexagone shape
	public void draw() {
		drawHexagon(35);
	}
}

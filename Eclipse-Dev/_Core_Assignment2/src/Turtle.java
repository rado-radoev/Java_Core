import java.util.*;
import java.awt.*;
import java.security.SecureRandom;

/**
 * Class that represents a turtle which is similar to a Logo turtle.
 * This class inherts from SimpleTurtle and is for students
 * to add methods to.
 *
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class Turtle extends SimpleTurtle
{
  ////////////////// constructors ///////////////////////
  
  /** Constructor that takes the x and y and a picture to
   * draw on
   * @param x the starting x position
   * @param y the starting y position
   * @param picture the picture to draw on
   */
  public Turtle (int x, int y, Picture picture) 
  {
    // let the parent constructor handle it
    super(x,y,picture);
  }
  
  /** Constructor that takes the x and y and a model
   * display to draw it on
   * @param x the starting x position
   * @param y the starting y position
   * @param modelDisplayer the thing that displays the model
   */
  public Turtle (int x, int y, 
                 ModelDisplay modelDisplayer) 
  {
    // let the parent constructor handle it
    super(x,y,modelDisplayer);
  }
  
  /** Constructor that takes the model display
   * @param modelDisplay the thing that displays the model
   */
  public Turtle (ModelDisplay modelDisplay) 
  {
    // let the parent constructor handle it
    super(modelDisplay);
  }
  
  /**
   * Constructor that takes a picture to draw on
   * @param p the picture to draw on
   */
  public Turtle (Picture p)
  {
    // let the parent constructor handle it
    super(p);
  }  
  
  /////////////////// methods ///////////////////////

  public static void main(String[] args)
  {
    World earth = new World();
    Turtle t1 = new Turtle(earth);
    t1.forward();
  }
  
  /**
   * Method to draw random shapes
   * @param random SecureRandom object that will be used to generate 
   * random length and random direction
   */
  public void drawRandomShape(SecureRandom random) {
		int randLength;
		int randDirection;
	  
	  for (int i = 0; i < 20; i++) {
		  
	       if (outOfBoundCheck()) {
			  	// generate random length and direction	
		        randLength = random.nextInt(20) + 70;
		        randDirection = random.nextInt(120 + 1 - 35) + 35;
		   
		        // move the turtle using the specified directions
		        forward(randLength);
		        turn(randDirection);
	       }
	    }
  }
  
  /**
   * Method that draws a spiral
   */
  public void drawSpiral() {
	  
	  // starting length
	  int length = 1;
	  
	  // loop 50 times
	  for (int i = 0; i < 50; i++) {
	
		  if (outOfBoundCheck()) {
		  
			// move and turn   
	        forward(length);
	        turn(22);
	        
	        // increment length by 1
	        length++;
		  }
	  }
  }
  
  /**
   * Method that will be overriden in child classes
   */
  public void draw() { }
  
  /**
   * Method that changes the pen color and draws a shape
   * @param color Color of the pen
   */
  public void drawColor(Color color) { 
	  // Change the pen color
	  setPenColor(color);
	  draw();
  }
 
  
  /**
   * Method that puts the turtle in the center of the screen
   */
  public void center() {
	  ModelDisplay displayModel = this.getModelDisplay();
	  this.penUp();
	  
  	  this.moveTo(displayModel.getWidth() /2 , 
		  displayModel.getHeight() / 2);

	  this.penDown();
  }
  
  /**
   * Method to draw an arrow
   * @param length
   */
  public void drawArrow(int length) {
	  this.moveTo(100, 100);
	  this.turnRight();
	  this.forward(length);
	  this.turn(120);
	  this.forward(length);
	  this.turn(120);
	  this.forward(length);
  }

  /**
   * Method to draw equilateral triangle
   * @param length
   * @throws IllegalArgumentException
   */

  public void drawTriangle(int length) throws IllegalArgumentException {
	  drawPolygonHelper(length, 3);
  }
  
  /**
   * Method to draw a rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @throws IllegalArgumentException
   */
  public void drawRectangle(int width, int height) throws IllegalArgumentException {
	  // Rectangle has 4 sides so we need to iterate 4 times
	  // turn right with each iteration
	  // if in even iteration, draw height
	  // if in odd iteration, drawing width
	  
	  for (int i = 1; i <= 4; i++) {
		  if (outOfBoundCheck())
		  {
			  this.turnRight(); 
			  if (i % 2 == 0) {
				  this.forward(height);
			  }
			  else {
				   this.forward(width); 
			  }	  
		  }
	  }
  }
  
  /**
   * Method to draw hexagon
   * @param length the length of the heagon's sides
   * @throws IllegalArgumentException   
   */
  public void drawHexagon(int length) throws IllegalArgumentException {
	 drawPolygonHelper(length, 5);
  }
 
  /**
   * Method to draw pentagon
   * @param length the length of the pentagon's sides
   * @throws IllegalArgumentException
   */
  public void drawPentagon(int length) throws IllegalArgumentException {
	  drawPolygonHelper(length, 6);
  }
  
  /**
   * Method that draws a star
   * @throws IllegalArgumentException
   */
  public void drawStart() throws IllegalArgumentException {
	  for (int i = 0; i < 20; i++) {
		  if (outOfBoundCheck()) {
			  this.forward(i * 5);
			  this.turn(144);
		  }
	  }
  }
  
  /**
   * Method that draws funnyShapes
   * @throws IllegalArgumentException
   */
  public void drawFunnyShapes() throws IllegalArgumentException {
	  for (int i = 0; i < 10; i++) {
		  if (outOfBoundCheck()) {
			  this.forward(50);
			  this.turn(-123);
		  }
		  else {
			  throw new IllegalArgumentException("Tutle cannot move out of the window border");
		  }
	  }
	  
	  Color currentColor = this.getBodyColor();
	  this.setBodyColor(Color.ORANGE);
	  for (int i = 0; i < 30; i++) {
		  if (outOfBoundCheck()) {
			  this.forward(45);
			  this.turn(20);
		  }
		  else {
			  throw new IllegalArgumentException("Tutle cannot move out of the window border");
		  }
	  }
	  this.setBodyColor(currentColor);
  }
  
  /**
   * Method that checks if turtle move will be outside the borders of the frame
   * @return boolean if the turtle is currently out the frames's limit
   */
  private boolean outOfBoundCheck() {
	  ModelDisplay displayMode = getModelDisplay();
	  int maxHeight = displayMode.getHeight();
	  int maxWidth = displayMode.getWidth();
	  
	  if ((getXPos() > maxHeight || getXPos() < 0) || 
			  (getYPos() > maxWidth || getYPos() < 0))
		  return false;
	  else
		  return true;
  }
  
  /**
   * Helper method that draws polygon shapes
   * @param length the polygon sides length
   * @param sides how many sides the polygon has
   * @throws IllegalArgumentException
   */
  private void drawPolygonHelper(int length, int sides) throws IllegalArgumentException {
  	  // Start drawing the polygon
	  
	  // Get the current turtle X position; this is the pivot point
	  int circleX = this.getXPos();
	  int circleY = this.getYPos();
	  
	  // the radius of the circle is the provided length
	  int radius = length;
	  
	  // Move the turtle to the 1st point with pen up
	  // This is required so the turtle doesn't start drawing from the middle of the circle
	  this.penUp();
	  this.moveTo((int)(circleX + radius * Math.cos(2.0 * Math.PI * 0)), 
	  		(int)(circleY + radius * Math.sin(2.0 * Math.PI * 0)));
	  this.penDown();
	  
	  // The real drawing starts here 
	  for (int i = 1; i <= sides; i++) {
		  int xx = (int)(circleX + radius * Math.cos(2.0 * Math.PI * i/sides));
		  int xy = (int)(circleY + radius * Math.sin(2.0 * Math.PI * i/sides));
		  
		  if (outOfBoundCheck()) {
			  this.moveTo(xx, xy); 
		  }
		  else {
			  throw new IllegalArgumentException("Tutle cannot move out of the window border");
		  }
		  
	  }
  }
  
  
} // this } is the end of class Turtle, put all new methods before this

// The easy way to draw Hexagon
//for (int i = 0 ; i < 6; i++) {
//	  this.forward(length);
//	  this.turn(60);
//}

// The easy way to draw Pentagon
//for (int i = 0; i < 5; i++) {
//this.forward(length);
//this.turn(72);
//}






















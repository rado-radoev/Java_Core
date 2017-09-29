
public class TurtleTest {
	
	public static void main(String[] args) {
		World world = new World();
		
		Turtle turtle = new Turtle(50, 50, world);
		turtle.setName("Jane");
		
		turtle.drawRectangle(150, 200);
		
		turtle.moveTo(200, 200);
		
		turtle.drawHexagon(60);
		
		// draw square with upper left corner at 50 50 
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		
//		// lift the pen so we don't draw when moving
//		turtle.penUp();
//	
//		
//		// move the turtle and put the pen down, ready to draw
//		turtle.moveTo(200, 200);
//		turtle.penDown();
//		
//		// draw square with upper left corner at 200 200
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		turtle.turnRight();
//		turtle.forward(30);
//		
		
		
		
		System.out.println(turtle);
	}

}

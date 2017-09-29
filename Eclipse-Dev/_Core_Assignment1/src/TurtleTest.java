
public class TurtleTest {
	
	public static void main(String[] args) {
		World world = new World();
		
		Turtle turtle = new Turtle(world);
		turtle.setName("Jane");
		
		turtle.drawStart();
		//turtle.drawFunnyShapes();
		
		//turtle.drawRectangle(200, 100);;
		turtle.center();
		
		//turtle.drawHexagon(60);
		
		turtle.penUp();
		turtle.moveTo(100, 100);
		turtle.penDown();
		
		//turtle.drawPentagon(60);

		turtle.penUp();
		turtle.center();
		turtle.penDown();

	}

}

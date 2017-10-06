import java.awt.Color;

public class BaleOfTurtles {

	 public static void main(String[] args) {
		/* Generate new world*/
		World world = new World();
		
		/* Generate array of different turtle objects*/
		Turtle[] turtles = {
				new SpiralTurtle(150, 150, world),
				new ConfusedTurtle(world),
				new SmartTurtle(390, 400, world)
		};
		
		/* Colors array; to be used to set different color for each turtle*/
		Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
		
		/* Iterate thought the turtles array and invoke each turtle drawColor method
		 * using different color
		*/ 
		for (int i = 0; i < turtles.length; i++) {
			turtles[i].drawColor(colors[i]);
		}

	}

}

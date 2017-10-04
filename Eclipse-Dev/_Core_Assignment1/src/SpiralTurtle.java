
public class SpiralTurtle extends Turtle {

	public SpiralTurtle(int x, int y, Picture picture) {
		super(x,y,picture);
	}
	
	public SpiralTurtle(int x, int y, ModelDisplay modelDisplay) {
		super(x, y, modelDisplay);
	}
	
	public SpiralTurtle(ModelDisplay modelDisplay) {
		super(modelDisplay);
	}
	
	public SpiralTurtle(Picture p) {
		super(p);
	}
	
	
	@Override
	public void draw() {
		int length = 1;
	    for (int i = 0; i < 100; i++) {
	
	        forward(length);
	        turn(22);
	        length++;
	    }
	}

}

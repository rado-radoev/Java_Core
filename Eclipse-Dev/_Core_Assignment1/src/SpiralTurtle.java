
public class SpiralTurtle extends Turtle {

	private double x;
	private double y;
	private double angle;
	
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
		int length = 5;
	    for (int i = 0; i < 150; i++) {
	
	        forward(length);
	        turn(-85);
	        length += 3;
	    }
	}

}

import java.security.SecureRandom;

public class ConfusedTurtle extends Turtle {
	
	public ConfusedTurtle(int x, int y, Picture picture) {
		super(x,y,picture);
	}
	
	public ConfusedTurtle(int x, int y, ModelDisplay modelDisplay) {
		super(x, y, modelDisplay);
	}
	
	public ConfusedTurtle(ModelDisplay modelDisplay) {
		super(modelDisplay);
	}
	
	public ConfusedTurtle(Picture p) {
		super(p);
	}

	@Override
	public void draw() {
		SecureRandom rand = new SecureRandom();
		
		
	}
}

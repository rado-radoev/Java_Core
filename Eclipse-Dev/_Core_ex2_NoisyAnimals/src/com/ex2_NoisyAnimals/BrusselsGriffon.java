package com.ex2_NoisyAnimals;

public class BrusselsGriffon extends Dog {
	
	public BrusselsGriffon() {
		super();
	}
	
	public BrusselsGriffon(String name) {
		super(name);
	}

	@Override
	public String toString() {
		//return String.format("%s does %s", getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), vocalize());
		return String.format("%s does %s", getName(), vocalize());
	}
}

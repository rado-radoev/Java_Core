package com.ex2_NoisyAnimals;

public class GermanShepard extends Dog {
	
	public GermanShepard() {
		super();
	}
	
	public GermanShepard(String name) {
		super(name);
	}

	@Override
	public String toString() {
		//return String.format("%s does %s", getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), vocalize());
		return String.format("%s does %s", getName(), vocalize());
	}
}

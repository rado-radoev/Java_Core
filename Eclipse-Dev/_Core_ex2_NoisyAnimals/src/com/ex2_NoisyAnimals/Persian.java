package com.ex2_NoisyAnimals;

public class Persian extends Cat {
	
	public Persian() {
		super();
	}
	
	public Persian(String name) {
		super(name);
	}

	@Override
	public String toString() {
		//return String.format("%s does %s", getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), vocalize());
		return String.format("%s does %s", getName(), vocalize());
	}
}

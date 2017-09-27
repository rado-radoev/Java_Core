package com.ex2NoisyAnimals;

public class BritishShorthair extends Cat {
	
	public BritishShorthair() {
		super();
	}
	
	public BritishShorthair(String name) {
		super(name);
	}

	@Override
	public String toString() {
		//return String.format("%s does %s", getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), vocalize());
		return String.format("%s does %s", getName(), vocalize());
	}
}

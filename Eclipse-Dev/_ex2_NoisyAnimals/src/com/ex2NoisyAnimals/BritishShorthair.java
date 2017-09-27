package com.ex2NoisyAnimals;

public class BritishShorthair extends Cat {

	@Override
	public String toString() {
		return String.format("%s does %s", getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1), vocalize());
	}
}

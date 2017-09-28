package com.ex2_NoisyAnimals;

public class Dog extends Animal {
	
	private String name;
	
	public Dog() {
		this("");
	}
	
	public Dog(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name.equals("")) {
			this.name = getClass().getName().substring(getClass().getName().lastIndexOf('.') + 1);
			return;
		}
		
		this.name = name;
	}

	@Override
	public String vocalize() {
		return String.format("barf barf%n");
		
	}

}

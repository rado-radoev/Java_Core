package com.ex2NoisyAnimals;

public class Menagerie {

	// initialize empty animals arrays
	private static Animal[] animals;
	
	public static void main(String[] args) {

		// create cat objects
		BritishShorthair britisShorthair = new BritishShorthair();
		Persian persion = new Persian();
		
		// create dog objects
		GermanShepard germanShepard = new GermanShepard();
		BrusselsGriffon brusselsGriffon = new BrusselsGriffon();
		BrusselsGriffon tobby = new BrusselsGriffon("Tobby");
		
		// instantiate the animals array
		animals = new Animal[5];
		
		// add cats and dogs to animals array
		animals[0] = britisShorthair;
		animals[1] = persion;
		
		animals[2] = germanShepard;
		animals[3] = brusselsGriffon;
		animals[4] = tobby;
		
		// loop through animals array and display
		for (int i = 0; i < animals.length; i++) {
			System.out.print(animals[i]);
		}
	}

}

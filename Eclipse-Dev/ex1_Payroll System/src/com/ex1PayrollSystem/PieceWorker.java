package com.ex1PayrollSystem;

import com.PayrollSystem.Employee;

public class PieceWorker extends Employee {

	private double wage; // wage per piece
	private double pieces; // pieces produced
	
	// Constructor
	public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
			double wage, double pieces) {
		super(firstName, lastName, socialSecurityNumber);
		
		setWage(wage);
		setPieces(pieces);
	}

	// return wage
	public double getWage() {
		return wage;
	}

	// set wage
	public final void setWage(double wage) {
		if (wage < 0D) {
			throw new IllegalArgumentException(
					"Wage per piece must be greater than zero");
		}
		this.wage = wage;
	}

	// return pieces produced
	public double getPieces() {
		return pieces;
	}

	// set pieces produced
	public final void setPieces(double pieces) {
		if (pieces < 0D) {  // validate pieces
			throw new IllegalArgumentException(
					"Pieces produced cannot be negative value");
		}
		
		this.pieces = pieces;
	}

	@Override
	// Override abstract method earnings in Employee class. 
	// earnings calculated by multiplying wage per piece with pieces created
	public double earnings() {
		return getWage() * getPieces();
	}
	
	@Override
	public String toString() {
		return String.format("Piece worker: %s%n%s: $%.2f; %s: %,.2f", 
				super.toString(), "wage per piece", getWage(),
				"pieces created", getPieces());
	}

}

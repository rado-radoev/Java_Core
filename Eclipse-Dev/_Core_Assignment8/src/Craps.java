import java.security.SecureRandom;


public class Craps {
	
	private Status gameStatus; // Can contain CONTINUE, WON, LOST
	private int myPoint, sumOfDice, die1, die2, sum;
	
	StringBuffer log;
	
	// Create secure random number generator for use in method rolldice
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	// Enum type with constants that represent the game status
	private enum Status{ CONTINUE, WON, LOST};
	
	// Constants that represent common rolls of the dice
	private static final int SNAKE_EYE = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;
    
    /**
     * @return log
     */
    public String getLog() {
    		return log.toString();
    }
       
    /**
	 * @return the gameStatus
	 */
	public Status getGameStatus() {
		return gameStatus;
	}

	/**
	 * @return the myPoint
	 */
	public int getMyPoint() {
		return myPoint;
	}

	/**
	 * @return the sumOfDice
	 */
	public int getSumOfDice() {
		return sumOfDice;
	}

	/**
	 * @return the die1
	 */
	public int getDie1() {
		return die1;
	}

	/**
	 * @return the die2
	 */
	public int getDie2() {
		return die2;
	}

	/**
	 * @return the sum
	 */
	public int getSum() {
		return sum;
	}

	/**
	 * @param message the message to append to log
	 */
	private void setLog(String message) {
		this.log.append(message);
	}
	
	/**
	 * @param gameStatus the gameStatus to set
	 */
	private void setGameStatus(Status gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * @param myPoint the myPoint to set
	 */
	private void setMyPoint(int myPoint) {
		this.myPoint = myPoint;
	}

	/**
	 * @param sumOfDice the sumOfDice to set
	 */
	private void setSumOfDice(int sumOfDice) {
		this.sumOfDice = sumOfDice;
	}

	/**
	 * @param die1 the die1 to set
	 */
	private void setDie1(int die1) {
		this.die1 = die1;
	}

	/**
	 * @param die2 the die2 to set
	 */
	private void setDie2(int die2) {
		this.die2 = die2;
	}

	/**
	 * @param sum the sum to set
	 */
	private void setSum(int sum) {
		this.sum = sum;
	}

	// Play the game of craps
	public void play() {
	
		log = new StringBuffer();
		
		setSumOfDice(rollDice()); // first roll of the dice
		
		// Determine game status and point based on first roll
		switch (getSumOfDice()) {
		case SEVEN: // win with 7 on the first roll
		case YO_LEVEN:	// win with 11 on the first roll
			setLog(String.format("Player wins%n"));
			setGameStatus(Status.WON);
			break;
		case SNAKE_EYE:	// lose with 2 on the first roll
		case TREY:	// lose with 3 on the first roll
		case BOX_CARS:	 // lose with 12 on the first roll
			setGameStatus(Status.LOST);
		default:	// did not win or lose, so remember point
			setGameStatus(Status.CONTINUE);	// game is not over
			setMyPoint(getSumOfDice());	// remember the point
			setLog(String.format("Point is %d%n", getMyPoint()));
			break;
		}
		
		// while game is not complete
		while (getGameStatus() == Status.CONTINUE) {
			setSumOfDice(rollDice());	// roll dice again
			
			// determine game status
			if (getSumOfDice() == getMyPoint()) { // win by making point
				//setLog(String.format("Player won making point%n"));
					setGameStatus(Status.WON);
				}
			else 
				if (getSumOfDice() == SEVEN)	{ // loose by rolling 7 before point
					//setLog(String.format("Player lost rolling 7 before point%n"));
						setGameStatus(Status.LOST);
					}
					
			// log won or lost message
			if (gameStatus == Status.WON)
				setLog(String.format("Player wins%n"));
			else
				setLog(String.format("Player loses%n"));
		}
	}
	
	// roll dice, calculate sum and display result
	public int rollDice() {
		// pick random die value
		setDie1(1 + randomNumbers.nextInt(6));	// first die roll
		setDie2(1 + randomNumbers.nextInt(6));	// second die roll
		
		setSum(getDie1() + getDie2()); // sum of die values
		
		// display results of this roll
		log.append(String.format("Player rolled %d + %d = %d%n", die1, die2, sum));
		
		return getSum();
	}

}

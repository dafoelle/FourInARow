package de.htwg.fourInARow.model.mock;

import de.htwg.fourInARow.model.IPlayer;

/**
 * Player implementation
 * @author David Föllenz, 21/05/2013
 * Player class as user abstraction. Implements from IPlayer.
 */
public class Player implements IPlayer{

	/* Fields */
	private String name;
	private mode session;
	private char sign;
	
	/* Methods */
	
	/*
	 * Standard constructor to initialise a new player.
	 */
	public Player() {
		this ("Player", mode.human, 'x');
	}
	
	public Player(String name, mode mode, char sign){
		this.name = name;
		this.session = mode;
		this.sign = sign;
	}
	
	/* Field access operations */
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#getSign()
	 */
	public char getSign(){
		return this.sign;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#setSign(char)
	 */
	public void setSign(char sign) {
		this.sign = sign;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#getName()
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#getSession()
	 */
	public mode getSession() {
		return this.session;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayer#setSession(de.htwg.fourInARow.model.IPlayer.mode)
	 */
	public void setSession(mode session) {
		this.session = session;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer();
		
		// Sign
		result.append("Sign: ");
		result.append(getSign());
		result.append(newLine);
		
		// Name
		result.append("Name: ");
		result.append(getName());
		result.append(newLine);
		
		// Session
		result.append("Session: ");
		result.append(getSession().toString());
		result.append(newLine);
		
		return result.toString();
	}
}

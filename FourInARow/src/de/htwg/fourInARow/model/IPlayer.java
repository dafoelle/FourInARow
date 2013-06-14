package de.htwg.fourInARow.model;

/**
 * IPlayer
 * @author David Föllenz, 21/05/2013
 * Interface as encapsulation mechanism for model.
 */
public interface IPlayer {
	
	/* Constructs */
	// Mode indicating in what modus the player plays in
	public enum mode { human, computer }
	
	/* Methods */
	// Returns users sign.
	char getSign();	
	
	// Returns users name.
	String getName();
	
	// Returns users chosen mode.
	mode getSession();	
	
	// Sets the users sign.
	void setSign(char sign);
	
	// Sets the users name.
	void setName(String name);
	
	// Sets the users session mode.
	void setSession(mode m);
}

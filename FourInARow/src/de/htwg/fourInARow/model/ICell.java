package de.htwg.fourInARow.model;

/**
 * ICell
 * @author David Föllenz, 21/05/2013
 * Interface as encapsulation mechanism for model. 
 */
public interface ICell {

	/* Constructs */
	public enum state {checked, unchecked}
	
	/* Methods */		
	// Returns Cell status.
	state getState();
	
	// Returns assigned sign.
	char getSign();
}

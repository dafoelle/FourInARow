package de.htwg.fourInARow.model.impl;

import de.htwg.fourInARow.model.ICell;

/**
 * Cell
 * @author David Föllenz, 21/05/2013
 * Smallest entity called "Cell". Implements from ICell.
 */
public class Cell implements ICell {
	
	/* Fields */
	// Cell status
	private state checked;
	// Assigned user sign
	private char sign;
		
	/* Methods */
	
	/**
	 * Standard constructor method initialising a new Cell.
	 */
	public Cell() {
		this.checked = state.unchecked;
		this.sign = ' ';
	}
	
	/*
	 * Sets current cell as checked.
	 */
	public void setAsChecked(char sign) {		
		this.checked = state.checked;
		this.sign = sign;
	}
	
	/*
	 * Returns cell status.
	 * @see de.htwg.fourInARow.model.ICell#getState()
	 */
	public state getState()	{
		return this.checked;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.ICell#getSign()
	 */
	public char getSign() {
		return this.sign;
	}
}

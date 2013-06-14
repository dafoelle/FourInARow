package de.htwg.fourInARow.model;

/**
 * IColumn
 * @author David Föllenz, 21/05/2013
 * Interface as encapsulation mechanism for model.
 */
public interface IColumn {

	/* Methods */	
	// Checks if there is still space in column.
	Boolean hasSpace();	
	
	// Returns cell of index X.
	ICell getCell(int index);
	
}

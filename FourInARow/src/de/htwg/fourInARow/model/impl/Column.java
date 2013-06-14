package de.htwg.fourInARow.model.impl;

import de.htwg.fourInARow.model.IColumn;
import de.htwg.fourInARow.model.IPlayer;

/**
 * Column
 * @author David Föllenz, 21/05/2013
 * Column class as container of cells. Implements from IColumn.
 */
public class Column implements IColumn {

	/* Fields */
	// All cells contained in column
	private Cell[] cells;
	// number of cells contained in column
	private int size;
	
	/* Methods */
	
	/*
	 * Standard constructor method to initialise a new column.
	 */
	protected Column(int fieldHeight)
	{
		this.size = fieldHeight;		
		this.cells = new Cell[this.size];
		
		// initialise column with X new Cells.
		for(int i=0; i < this.size; i++){
			cells[i] = new Cell();
		}
	}
	
	/*
	 * Sets last free cell in column as "checked".
	 */
	public Boolean addChip(IPlayer player){		
		if(this.hasSpace()){			
			for(int i=0; i < this.size; i++){
				if(this.cells[i].getState().equals(Cell.state.unchecked)){
					this.cells[i].setAsChecked(player.getSign());					
					return true;
				}
			}
		}	
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IColumn#hasSpace()
	 */
	public Boolean hasSpace() {
		return this.cells[this.size-1].getState().equals(Cell.state.unchecked);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IColumn#getCell(int)
	 */
	public Cell getCell(int index) {
		if(index >= 0 && index <= this.size) {
			return this.cells[index];
		}
		else {
			return null;
		}
	}	
}

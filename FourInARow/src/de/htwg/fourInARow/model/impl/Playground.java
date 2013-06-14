package de.htwg.fourInARow.model.impl;

import de.htwg.fourInARow.model.IColumn;
import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayground;

/**
 * Playground
 * @author David Föllenz, 21/05/2013
 * Playground implementation as field. Implements IPlayground.
 */
public class Playground implements IPlayground{

	/* Fields */
	private int width = 0;
	private int height = 0;
	private Column[] columns = null;
	private int scannerSize = 0;
	
	/* max values */
	private static final int MAXCOLUMNCOUNT = 50;
	private static final int MINCOLUMNCOUNT = 5;
	private static final int MAXROWCOUNT = 50;
	private static final int MINROWCOUNT = 5;
	private static final int MINSCANNERSIZE = 4;
	private static final int INITWIDTH = 10;
	private static final int INITHEIGHT = 5;	
	
	/* Methods */
	
	/* 
	 * Standard constructor method to initialise a new Playground.
	 */
	public Playground() {
		this(INITWIDTH, INITHEIGHT);		
	}
	
	/*
	 * Returns columns of playground as an array.
	 */
	public IColumn[] getColumns() {
		return this.columns;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#Clear()
	 */
	public void clear() {
		this.columns = new Column[this.width];
		
		for(int i=0; i < this.width; i++){
			this.columns[i] = new Column(this.height);
		}
	}
	
	/*
	 * Constructor method to initialise playground with non-standard sizes.
	 * @param cntColumns: Number of columns
	 * @param cntRows: Number of rows.
	 */
	public Playground(int cntColumns, int cntRows){	
		
		this.height = (cntRows > MINROWCOUNT && cntRows < MAXROWCOUNT) ? Math.abs(cntRows) : MINROWCOUNT; 
		this.width = (cntColumns > MINCOLUMNCOUNT && cntColumns < MAXCOLUMNCOUNT) ? Math.abs(cntColumns) : MINCOLUMNCOUNT;
		this.columns = new Column[this.width];
		this.scannerSize = MINSCANNERSIZE;
		
		// array initialisation of columns.
		for(int i=0; i < this.width; i++){
			this.columns[i] = new Column(this.height);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#addChip(int, de.htwg.fourInARow.model.IPlayer)
	 */
	public Boolean addChip(int column, IPlayer player){
		if(column >= 0 && column <= this.width) {
			return this.columns[column].addChip(player);
		}
		else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#getColumn(int)
	 */
	public IColumn getColumn(int index){
		if(index >= 0 && index <= this.width) {
			return this.columns[index];
		}
		else {
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#getScannerSize()
	 */
	public int getScannerSize() {
		return this.scannerSize;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#setScannerSize(int)
	 */
	public void setScannerSize(int size) {
		int tmp = (size >= MINSCANNERSIZE && (size <= getWidth() || size <= getHeight()) ? size : MINSCANNERSIZE);
		this.scannerSize = tmp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#getHeight()
	 */
	public int getHeight() {
		return this.height;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#getWidth()
	 */
	public int getWidth() {
		return this.width;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#setHeight(int)
	 */
	public void setHeight(int height) {
		int tmp = (height > MINCOLUMNCOUNT && height < MAXCOLUMNCOUNT) ? height : MINCOLUMNCOUNT;		
		this.height = tmp;
		this.clear();
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#setWidth(int)
	 */
	public void setWidth(int width) {
		int tmp = (width > MINROWCOUNT && width < MAXROWCOUNT) ? width : MINROWCOUNT;
		this.width = tmp;
		this.clear();
	}	
	
	public Boolean isReady() {
		// Check if there is still a free cell in playground.
		for(int i=0; i < this.width; i++) {
			if(this.columns[i].hasSpace())	{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#hasFinished()
	 */
	public Boolean hasFinished() {
		
		Boolean retVal = false;
		
		// Check the horizontal way for each row
		if(!retVal) {
			retVal = checkForHorizontal(0, 0);
		}
		
		// Check the vertical way for each column
		
		if(!retVal) {
			retVal = checkForVertical(0, 0);
		}
		
		// Check the diagonal way (left to right) for each row and column
		if(!retVal) {
			retVal = checkForDiagonal1(0, 0);
		}
		
		// Check the diagonal way (right to left) for each row and column
		if(!retVal) {
			retVal = checkForDiagonal2(0, 0);
		}
		
		return retVal;
	}
	
	/*
	 * Searches for X in a row in vertical direction. (Recursive)
	 */
	private Boolean checkForVertical(int start, int col) {
		if(!(start + this.scannerSize > this.height)) {
			int counter = 0;
			if(this.getColumn(col).getCell(start).getState() != Cell.state.unchecked){
				char tmpChar = this.getColumn(col).getCell(start).getSign();
				
				for(int i = start; i < start+this.scannerSize; i++) {				
					if(this.getColumn(col).getCell(i).getSign() == tmpChar) {
						counter++;				
					}
				}
			}
			if(counter == this.scannerSize) {
				return true;
			}
			else {
				return checkForVertical(start+1, col);
			}
		}
		if(col < this.width-1) {
			return checkForVertical(0, col+1);
		}
		else {
			return false;
		}
	}
	
	/*
	 * Searches for X in a row in horizontal direction. (Recursive)
	 */
	private Boolean checkForHorizontal(int start, int row) {
		if(!(start + this.scannerSize > this.width)) {
			int counter = 0;
			if(this.getColumn(start).getCell(row).getState() != Cell.state.unchecked){
				char tmpChar = this.getColumn(start).getCell(row).getSign();
				
				for(int i = start; i < start+this.scannerSize; i++) {				
					if(this.getColumn(i).getCell(row).getSign() == tmpChar) {
						counter++;				
					}
				}
			}
			if(counter == this.scannerSize) {
				return true;
			}
			else {
				return checkForHorizontal(start+1, row);
			}
		}
		if(row < this.height-1) {
			return checkForHorizontal(0, row+1);
		}
		else {
			return false;
		}
	}
	
	/*
	 * Searches for X in a row in diagonal direction from left to right. (Recursive)
	 */
	private Boolean checkForDiagonal1(int startCol, int startRow) {
		if(!(startCol + this.scannerSize > this.width) && !(startRow + this.scannerSize > this.height )) {
			int counter = 0;
			if(this.getColumn(startCol).getCell(startRow).getState() != Cell.state.unchecked){
				char tmpChar = this.getColumn(startCol).getCell(startRow).getSign();
				int k = startRow;
				for(int i = startCol; i < startCol + this.scannerSize; i++) {
					if(this.getColumn(i).getCell(k).getSign() == tmpChar) {
						counter++;	
					}
					k += 1;
				}
			}
			if(counter == this.scannerSize) {
				return true;
			}
			else {
				return checkForDiagonal1(startCol+1, startRow);
			}
		}
		if(startRow < this.height-this.scannerSize-1) {
			return checkForDiagonal1(0, startRow+1);
		}
		else {
			return false;
		}
	}
	
	/*
	 * Searches for X in a row from right to left in diagonal direction. (Recursive)
	 */
	private Boolean checkForDiagonal2(int startCol, int startRow) {
		if(!(startCol + this.scannerSize > this.width) && !(startRow + this.scannerSize > this.height )) {
			int counter = 0;
			if(this.getColumn(startCol).getCell(startRow+this.scannerSize-1).getState() != Cell.state.unchecked){
				char tmpChar = this.getColumn(startCol).getCell(startRow+this.scannerSize-1).getSign();
				int k = startRow+this.scannerSize-1;
				for(int i = startCol; i < startCol + this.scannerSize; i++) {
					if(this.getColumn(i).getCell(k).getSign() == tmpChar) {
						counter++;	
					}
					k -= 1;
				}
			}
			if(counter == this.scannerSize) {
				return true;
			}
			else {
				return checkForDiagonal2(startCol+1, startRow);
			}
		}
		if(startRow + this.scannerSize < this.height) {
			return checkForDiagonal2(0, startRow+1);
		}
		else {
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.htwg.fourInARow.model.IPlayground#toSettingString()
	 */
	public String toSettingString() {		
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer();
		
		// Width
		result.append("Width: ");
		result.append(this.getWidth());
		result.append(newLine);
		
		// Height
		result.append("Height: ");
		result.append(this.getHeight());
		result.append(newLine);
		
		// Scanner size
		result.append("Scanner size: ");
		result.append(this.getScannerSize());
		result.append(newLine);
		
		return result.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer(rowSeparator(this.width));
		result.append(newLine);
		
		for (int row = this.height-1; row >= 0; row--) {
			result.append(rowPrinter(row));
			result.append(newLine);
			
			result.append(rowSeparator(this.width));
			result.append(newLine);
		}
		return result.toString();
	}
	
	/*
	 * Converts single row in string.
	 */
	private String rowPrinter(int row) {
		
		StringBuffer result = new StringBuffer("|");
		for (int column = 0; column < this.width; column++) {
						
			result.append(" ");
			result.append(this.columns[column].getCell(row).getSign());
			result.append(" |");
			
		}
		return result.toString();
	}

	/**
	 * returns a string of the form +---+ (i.e. in the case of colCount = 1)
	 */
	private String rowSeparator(int colCount) {
		final int colLenght = 3;
		StringBuffer result = new StringBuffer("+");
		for (int i = 0; i < colCount; i++) {
			for (int j = 0; j < colLenght; j++) {
				result.append("-");
			}
			result.append("+");
		}
		return result.toString();
	}
}

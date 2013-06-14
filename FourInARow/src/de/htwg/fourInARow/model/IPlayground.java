package de.htwg.fourInARow.model;

/**
 * IPlayground
 * @author David Föllenz, 21/05/2013
 * Interface as encapsulation mechanism for model.
 */
public interface IPlayground {

	/* Methods */	
	// Clears playground completely.
	void clear();
	
	// Adds chip to special column.
	Boolean addChip(int column, IPlayer player);

	// Returns column number X.
	IColumn getColumn(int index);
	
	// Returns playgrounds height.
	int getHeight();
	
	// Returns playgrounds width.
	int getWidth();
	
	// Sets playgrounds height.
	void setHeight(int height);
	
	// Sets playgrounds width.
	void setWidth(int width);
	
	// Returns scanner size.
	int getScannerSize();
	
	// Sets scanner size.
	void setScannerSize(int size);
	
	// Returns a flag indicating if the game finished with winner or not.
	Boolean isReady();
	
	// Returns a flag indicating if the game finished by someone winning or not.
	Boolean hasFinished();

	// Returns a string containing the playground settings.
	String toSettingString();
	
}

package de.htwg.fourInARow.model;

public interface IPlaygroundFactory {
	
	IPlayground create(int cols, int rows);
	
}

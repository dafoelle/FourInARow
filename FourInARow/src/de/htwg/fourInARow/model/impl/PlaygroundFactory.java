package de.htwg.fourInARow.model.impl;

import de.htwg.fourInARow.model.IPlayground;
import de.htwg.fourInARow.model.IPlaygroundFactory;

public class PlaygroundFactory implements IPlaygroundFactory {

	@Override
	public IPlayground create(int cols, int rows) {
		return new Playground(cols, rows);
	}

}

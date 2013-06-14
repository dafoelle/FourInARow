package de.htwg.fourInARow.model.mock;

import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayerFactory;

public class PlayerFactory implements IPlayerFactory {

	@Override
	public IPlayer create(String name, IPlayer.mode session, char sign) {
		return new Player(name, session, sign);
	}

}

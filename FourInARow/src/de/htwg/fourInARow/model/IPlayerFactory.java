package de.htwg.fourInARow.model;

public interface IPlayerFactory {
	
	IPlayer create(String name, IPlayer.mode session, char sign);
	
}

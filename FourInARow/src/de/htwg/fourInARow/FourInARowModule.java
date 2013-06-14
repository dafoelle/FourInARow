package de.htwg.fourInARow;

import com.google.inject.AbstractModule;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayerFactory;
import de.htwg.fourInARow.model.IPlayground;
import de.htwg.fourInARow.model.IPlaygroundFactory;


public class FourInARowModule extends AbstractModule {
	
	// Binds the real implementation to application.
	
	@Override
	protected void configure() {
		bind(IPlayerFactory.class).to(de.htwg.fourInARow.model.impl.PlayerFactory.class);
		bind(IPlaygroundFactory.class).to(de.htwg.fourInARow.model.impl.PlaygroundFactory.class);
		bind(IFourInARowController.class).to(de.htwg.fourInARow.controller.impl.FourInARowController.class);
	}

}

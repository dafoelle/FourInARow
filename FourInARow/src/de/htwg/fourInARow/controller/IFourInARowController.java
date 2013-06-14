package de.htwg.fourInARow.controller;

import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayground;
import de.htwg.util.Observer.IObservable;

/**
 * IFourInARowController
 * @author David, 21/05/2013
 * Interface as encapsulation mechanism for game controller.
 */
public interface IFourInARowController extends IObservable {
	
	// reinitialises game
	void newGame();
	
	// returns the currently used playground
	IPlayground getPlayground();
	
	// returns player one
	IPlayer getPlayerOne();

	// returns player two
	IPlayer getPlayerTwo();

	// returns status message as string
	String getStatus();

	// sets the status message
	void setStatusMessage(String status);
	
	// returns message indicating whose turn it is
	String getTurnMessage();

	// adds movement to game
	Boolean nextMove(int column);
	
	// returns a flag indicating if game finished or not
	Boolean hasFinished();
	
}

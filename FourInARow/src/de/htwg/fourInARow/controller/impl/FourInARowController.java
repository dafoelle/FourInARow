package de.htwg.fourInARow.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.fourInARow.model.ICell;
import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayerFactory;
import de.htwg.fourInARow.model.IPlayground;
import de.htwg.fourInARow.model.IPlaygroundFactory;
import de.htwg.util.Observer.Observable;

/**
 * Four in a Row
 * @author David Föllenz, 21/05/2013
 * TextUI: User interface as console.
 */
@Singleton
public class FourInARowController extends Observable implements IFourInARowController {

	/* Fields */
	private String statusMessage = "Welcome to 4InARow!";
	private String turnMessage = "";
	private IPlayground playground = null;
	private IPlayer player1 = null;
	private IPlayer player2 = null;
	private Boolean turn = true;
	private Boolean gameOver = false;
	
	/* Methods */
	
	/**
	 * Constructor method to initialise a new controller.
	 * @param Playground to use.
	 * @param Player one to set.
	 * @param player two to set.
	 */
	@Inject
	public FourInARowController(IPlaygroundFactory pg, IPlayerFactory p1, IPlayerFactory p2) {
		
		/* Factories */
		IPlayerFactory playerOneFactory = p1;
		IPlayerFactory playerTwoFactory = p2;
		IPlaygroundFactory playgroundFactory = pg;
		
		this.playground = playgroundFactory.create(6, 5);
		this.player1 = playerOneFactory.create("Player1", IPlayer.mode.human, 'O');
		this.player2 = playerTwoFactory.create("Player2", IPlayer.mode.human, 'X');
		
		this.turn = true;
		this.setTurnMessage("Its " + player1.getName() + "'s turn.");
		this.gameOver = false;
	}
	
	/**
	 * Reinitialises game. (Playground and status messages.)
	 */
	public void newGame() {
		this.playground.clear();
		this.gameOver = false;
		this.turn = true;
		this.setTurnMessage("Its " + player1.getName() + "'s turn.");
		this.setStatusMessage("");
	}
	
	/**
	 * Returns the currently used playground.
	 */
	public IPlayground getPlayground() {
		return this.playground;
	}
	
	/**
	 * Returns player one.
	 */
	public IPlayer getPlayerOne() {
		return this.player1;
	}
	
	/**
	 * Returns player two.
	 */
	public IPlayer getPlayerTwo() {
		return this.player2;
	}
	
	/**
	 * Returns status message.
	 */
	public String getStatus() {
		return statusMessage;
	}

	/**
	 * Sets the status message assigning a new value.
	 * @param statusMessage to set.
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Returns a message indication whose turn it is. 
	 */
	public String getTurnMessage() {
		return this.turnMessage;
	}

	/**
	 * Sets the turn message.
	 * @param message to set.
	 */
	private void setTurnMessage(String message) {
		this.turnMessage = message;
	}
	
	/**
	 * Adds an new Movement.
	 * @param Column indicating where to add the chip.
	 */
	public Boolean nextMove(int column)  
	{
		// check if passed value is inside the bounds
		if(column > this.playground.getWidth() || (column <= 0)) {
			return false;
		}
		
		Boolean chipAdded = false;
		IPlayer player = getPlayerForTurn(turn);
		
		// special case for computer player
		if(player.getSession().equals(IPlayer.mode.computer)){
					
			// add as long as it needs to succeed
			while(!chipAdded) {
				chipAdded = playground.addChip(computerNextStep(player), player);
			}				
		}		
		else {
			// human player case. Just set chip here.
			chipAdded = playground.addChip(column-1, player);					
		}
		
		turn = !turn;		
		this.setTurnMessage("Its " + getPlayerForTurn(turn).getName() + "'s turn.");
		
		if(!chipAdded) {
			turn = !turn;
		}
		else {
			hasFinished(getPlayerForTurn(!turn));
				
			// start with next move if following player is computer.
			if(getPlayerForTurn(turn).getSession().equals(IPlayer.mode.computer) && !gameOver) {
				// computer's next turn.
				this.nextMove(1);	
			}
		}		
		notifyObservers();
		return chipAdded;
	}
	
	/**
	 * To check if game has finished or not. (intern)
	 */
	public Boolean hasFinished() {
		return this.gameOver;
	}
	
	/**
	 * check if game has finished or not. Also figures out who's the wiinner.
	 * @param last moving player.
	 * @return Flag indicating if game finished or not.
	 */
	private Boolean hasFinished(IPlayer player) {
		
		// game field is filled up to 100%. There is no more space to add anymore chips.
		if(playground.isReady()) {
			this.setStatusMessage("Game over. The playground is filled completely. " );
			this.setTurnMessage("");
			gameOver = true;
		}
		
		// Someone won
		if(playground.hasFinished()) {
			this.setStatusMessage("Game over. " + player.getName() + " wins.");
			this.setTurnMessage("");
			gameOver = true;
		}
		
		return gameOver;
	}
	
	/**
	 * Figures out the player for turn X.
	 * @param turn (current or next.)
	 * @return Player who's on the turn.
	 */
	private IPlayer getPlayerForTurn(Boolean turn) {
		IPlayer ret;
		if(turn) {
			ret = player1;
		}
		else {
			ret = player2;
		}
		return ret;
	}
	
	/**
	 * Artificial intelligence used here to make movements as computer.
	 * @param player
	 * @return
	 */
	private int computerNextStep(IPlayer player) {
				
		List<Integer> pairList= new ArrayList<Integer>();
		
		pairList = analyseColumns(pairList, player.getSign());
		
		//2. analyze all rows for own chips
		if(pairList.size() == 0) {			
			pairList = analyseRows(pairList, player.getSign());
		}
		
		// if there is any row/column with more than CONST/2 chips from player and fill free space here.		
		if(pairList.size() == 0) {
			return new Random().nextInt(playground.getWidth());
		}
		else {
			return pairList.get(pairList.size()/2);
		}
	}
	
	/*
	 * Analyse all columns for own chips
	 * @param current pair list of integer.
	 */
	private List<Integer> analyseColumns(List<Integer> pairList, char sign) {
		for(int i = 0; i < this.playground.getWidth()-1; i++)	{
			if(!playground.getColumn(i).hasSpace()) {
				continue;
			}
			for (int j = 0; j < this.playground.getHeight()-playground.getScannerSize()-1; j++) {
				ICell[] array = new ICell[playground.getScannerSize()];
				
				int cnt = 0;
				List<Integer> tmpPairList= new ArrayList<Integer>();
				for(int k = 0; k < playground.getScannerSize(); k++) {
					array[k] = this.playground.getColumn(i).getCell(j+k);
					if(array[k].getSign() == sign) {
						cnt++;
					}
					else if(array[k].getState() == ICell.state.unchecked) {						
						tmpPairList.add(i);
					}						
				}
				if(cnt > playground.getScannerSize()-2 && tmpPairList.size() > 0) {
					pairList.addAll(tmpPairList);			
				}
			}
		}		
		return pairList;
	}
	
	private List<Integer> analyseRows(List<Integer> pairList, char sign) {
		for(int i = 0; i < this.playground.getHeight()-1; i++)	{
			if(!playground.getColumn(i).hasSpace()) {
				continue;
			}
			for (int j = 0; j < this.playground.getWidth()-playground.getScannerSize()-1; j++) {
				ICell[] array = new ICell[playground.getScannerSize()];
				
				int cnt = 0;
				List<Integer> tmpPairList= new ArrayList<Integer>();
				for(int k = 0; k < playground.getScannerSize(); k++) {
					array[k] = this.playground.getColumn(j+k).getCell(i);
					if(array[k].getSign() == sign) {
						cnt++;
					}
					else if(array[k].getState() == ICell.state.unchecked) {						
						tmpPairList.add(i);
					}						
				}
				if(cnt > playground.getScannerSize()-2) {
					pairList.addAll(tmpPairList);	
				}
			}
		}
		return pairList;
	}
	
}

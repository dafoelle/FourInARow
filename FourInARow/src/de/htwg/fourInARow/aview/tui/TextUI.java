package de.htwg.fourInARow.aview.tui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayground;
import de.htwg.util.Observer.IObserver;

/**
 * Four in a Row
 * @author David Föllenz, 21/05/2013
 * TextUI: User interface as console.
 */
public class TextUI implements IObserver {
	
	/**
	 * Text wrapper
	 */
	String newLine = System.getProperty("line.separator");
	
	/**
	 * Used controller to manage the application.
	 */
	private IFourInARowController controller = null;
	
	/**
	 * Scanner to read from console.
	 */
	private Scanner scanner = null;
	
	/**
	 * Logger to avoid printing on console.
	 */
	private Logger logger = Logger.getLogger("de.htwg.fourInARow.aview.tui");
	
	/**
	 * Initialises all fields. Returns an instance of TextUI.
	 * @param controller
	 */
	@Inject
	public TextUI(IFourInARowController controller){
		this.controller = controller;
		controller.addObserver(this);
		scanner = new Scanner (System.in);	
	}

	/**
	 * Overridden method "update" to print out the Playground.
	 */
	public void update() {
		printTUI();
	}

	/**
	 * Method to iterate over the user process.
	 * @return Boolean to indicate if the game has finished or not.
	 */
	public boolean iterate() {
		printTUI();
		return handleInputOrQuit(scanner.next());
	}
	
	/**
	 * Prints out the standard UI to terminal. Also used to print out status messages and user options.
	 */
	public void printTUI() {
		logger.info(newLine + controller.getPlayground().toString());
		logger.info(newLine + controller.getStatus());
		logger.info(newLine + controller.getTurnMessage());
		logger.info(newLine + "Please enter a command( q - quit, n - new, m - next Move, s - Settings");
	}
	
	/**
	 * Used to handle terminal input. Basically compares input string with any options.
	 * @param line (text input)
	 * @return Flag to indicate if game has finished or not.
	 */
	public boolean handleInputOrQuit(String line) {	
		
		boolean quit=false;
		
		// quit game.
		if (line.equalsIgnoreCase("q")) {
			quit=true;
		}

		// add an movement.
		if (line.matches("m")){	
			boolean ret = false;
			while(!ret) {
				if(controller.hasFinished()) {
					ret = true;
					break;
				}
				int val = scanner.nextInt();
				ret = controller.nextMove(val);		
			}
		}		
		
		// Reinitialise game.
		if(line.matches("n")) {
			controller.newGame();
		}
		
		// Configure game settings.
		if(line.matches("s")) {
			handleSettings();
		}
		return quit;
	}
	
	/**
	 * Mehtod to print out and handle game configuration menu.
	 */
	private void handleSettings() {	
		boolean quit = false;
		while(!quit) {
			logger.info(newLine + "Please enter a command( p1 - Player1, p2 - Player2, pg - Playground, back - Step back");
			
			String val = scanner.next();
			if (val.equals("p1")) {
				handlePlayer(controller.getPlayerOne());
			}
			if (val.equals("p2")) {
				handlePlayer(controller.getPlayerTwo());
			}
			if (val.equals("pg")) {
				handlePlayground(controller.getPlayground());
			}
			if (val.equals("back")) {
				quit = true;
			}
		}
	}

	/** 
	 * Configure playground settings.
	 * @param playground to change.
	 */
	private void handlePlayground(IPlayground playground) {
		
		boolean quit = false;
		while(!quit) {
			logger.info(newLine + playground.toSettingString());
			logger.info(newLine + "Please enter a command( width - change Width, height - change Height, scanner - change Scanner size, back - Step back");
			String val = scanner.next();
			
			if(val.equals("width")) {
				playground.setWidth(scanner.nextInt());		
			}
			if (val.equals("height")) {
				playground.setHeight(scanner.nextInt());
			}
			if (val.equals("scanner")) {
				playground.setScannerSize(scanner.nextInt());	
			}
			if (val.equals("back")) {
				quit = true;				
			}
		}
	}

	/**
	 * Method to configure player.
	 * @param player to configure.
	 */
	private void handlePlayer(IPlayer player) {
		controller.getPlayground().clear();
		boolean quit = false;
		while(!quit) {
			logger.info(newLine + player.toString());
			logger.info(newLine + "Please enter a command( sign - change Sign, name - change Name, mode - change Mode, back - Step back");
			
			String val = scanner.next();
			if (val.equals("sign")) {
				player.setSign(scanner.next().charAt(0));
			}
			if (val.equals("name")) {
				player.setName(scanner.next());
			}
			if (val.equals("mode")) {
				player.setSession(IPlayer.mode.valueOf(scanner.next()));
			}
			if (val.equals("back")) {
				quit = true;
			}
		}
	}
}

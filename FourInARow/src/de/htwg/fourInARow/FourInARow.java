package de.htwg.fourInARow;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.fourInARow.controller.IFourInARowController;

/* User interfaces */
import de.htwg.fourInARow.aview.gui.GraphicUI;
import de.htwg.fourInARow.aview.tui.TextUI;

/**
 * Four in a Row
 * @author David Föllenz
 * Four in a Row: Main class.
 */
public final class FourInARow {

	private FourInARow() {}
	
	/**
	 * Main method. Application entry point.
	 * Creates an playground and two player before using them to initialise the controller class.
	 * Furthermore it calls the User interfaces (Text and Graphic) 
	 * It's up to the user to decide about the implementation here. (Dependency Injection) 
	 */
	public static void main(String[] args) {
		
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");
			
		// Set up Google Guice Dependency Injector
		Injector injector = Guice.createInjector(new FourInARowModule());
				
		// Build up the application, resolving dependencies automatically by Guice
		IFourInARowController controller = injector.getInstance(IFourInARowController.class);
		
		@SuppressWarnings("unused")
		TextUI tui = injector.getInstance(TextUI.class);
		GraphicUI gui = injector.getInstance(GraphicUI.class);		
		
		// continue until the user decides to quit
		boolean quit = false;
		while (!quit) {
		    quit = tui.iterate();
		}
	}

}

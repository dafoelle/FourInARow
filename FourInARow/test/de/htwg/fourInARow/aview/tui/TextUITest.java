package de.htwg.fourInARow.aview.tui;

import org.junit.Before;
import org.junit.Test;

import de.htwg.fourInARow.controller.impl.FourInARowController;
import de.htwg.fourInARow.model.IPlayer.mode;
import de.htwg.fourInARow.model.impl.Player;
import de.htwg.fourInARow.model.impl.PlayerFactory;
import de.htwg.fourInARow.model.impl.Playground;
import de.htwg.fourInARow.model.impl.PlaygroundFactory;

public class TextUITest {

	private Playground pg = null;
	private Player p1 = null;
	private Player p2 = null;
	private FourInARowController controller = null;
	@Before
	public void setUp() throws Exception {
		pg = new Playground();
		p1 = new Player("test1", mode.human, 'X');
		p2 = new Player("test2", mode.human, 'O');
		controller = new FourInARowController(new PlaygroundFactory(), new PlayerFactory(), new PlayerFactory());
		new TextUI(controller);
	}

	@Test
	public void testHandleInputOrQuit() {
		
	}
	
}

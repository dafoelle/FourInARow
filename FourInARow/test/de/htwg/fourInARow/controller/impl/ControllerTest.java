package de.htwg.fourInARow.controller.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.fourInARow.model.ICell;
import de.htwg.fourInARow.model.IPlayer;
import de.htwg.fourInARow.model.IPlayground;
import de.htwg.fourInARow.model.impl.PlayerFactory;
import de.htwg.fourInARow.model.impl.PlaygroundFactory;

public class ControllerTest {
	String newLine = System.getProperty("line.separator");

	private IPlayground playground;
	IFourInARowController controller1;
	IPlayer player1;
	IPlayer player2;

	@Before
	public void setUp() throws Exception {
		controller1 = new FourInARowController(new PlaygroundFactory(), new PlayerFactory(), new PlayerFactory());
		controller1.getPlayground().setHeight(6);
		controller1.getPlayground().setWidth(10);
		
		controller1.getPlayground().setScannerSize(5);
		
		controller1.getPlayerOne().setName("testPlayer1");
		controller1.getPlayerOne().setSession(IPlayer.mode.computer);
		controller1.getPlayerOne().setSign('O');
		
		controller1.getPlayerTwo().setName("testPlayer2");
		controller1.getPlayerTwo().setSession(IPlayer.mode.human);
		controller1.getPlayerTwo().setSign('X');
		
	}

	@Test
	public void testGetPlayerOne() {
		IPlayer temp = controller1.getPlayerOne();
		assertEquals("testPlayer1", temp.getName());
		assertEquals("computer", temp.getSession().toString());
		assertEquals('O', temp.getSign());
	}
	
	@Test
	public void testGetPlayerTwo() {
		IPlayer temp = controller1.getPlayerTwo();
		assertEquals("testPlayer2", temp.getName());
		assertEquals("human", temp.getSession().toString());
		assertEquals('X', temp.getSign());
	}
	
	@Test
	public void testGetPlayground() {
		IPlayground temp = controller1.getPlayground();
		assertEquals(6, temp.getHeight());
		assertEquals(10, temp.getWidth());
		assertEquals(5, temp.getScannerSize());
	}
	
	@Test
	public void testGetStatus() {
		assertEquals("Welcome to 4InARow!", controller1.getStatus());
	}
	
	@Test
	public void testGetTurnMessage() {
		assertEquals("Its Player1's turn.", controller1.getTurnMessage());
	}
	
	@Test
	public void testHasFinished() {
		assertFalse(controller1.hasFinished());
	}
	
	@Test
	public void testNewGame() {
		controller1.nextMove(5);
		controller1.nextMove(5);
		assertEquals(ICell.state.checked, controller1.getPlayground().getColumn(4).getCell(0).getState());
		controller1.newGame();
		assertEquals(ICell.state.unchecked, controller1.getPlayground().getColumn(4).getCell(0).getState());
	}
	
	@Test
	public void testNextMove() {
		assertEquals(ICell.state.unchecked, controller1.getPlayground().getColumn(4).getCell(0).getState());
		controller1.nextMove(5);
		controller1.nextMove(5);
		assertFalse(controller1.nextMove(100));
		assertFalse(controller1.nextMove(-100));
		assertEquals(ICell.state.checked, controller1.getPlayground().getColumn(4).getCell(0).getState());
		fillColumnWithoutWinner(0);
		controller1.nextMove(1);
		assertFalse(controller1.nextMove(1));
		controller1.newGame();
		fillColumnWithWinner(0);
		controller1.nextMove(1);
		assertTrue(controller1.nextMove(1));
	}
	
	/**
	 * Fill column [col] so that there no one wins.
	 * @param col
	 */
	private void fillColumnWithoutWinner(int col) {
		for(int i = 0; i < 30; i++) {
			if(i % 2 == 0)
				controller1.getPlayground().addChip(col, controller1.getPlayerOne());
			else
				controller1.getPlayground().addChip(col, controller1.getPlayerTwo());
		}
	}
	
	/**
	 * Fill column [col] so that player one wins.
	 * @param col
	 */
	private void fillColumnWithWinner(int col) {
		controller1.getPlayground().addChip(col, controller1.getPlayerOne());
	}

}

package de.htwg.fourInARow.model.mock;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCell {

	Cell testCell;
	Player testPlayer;
	
	@Before
	public void setUp() throws Exception {
		testCell = new Cell();
		testPlayer = new Player("test", Player.mode.human, 'x');
	}

	@After
	public void tearDown() throws Exception {
		testCell = null;
		testPlayer = null;
	}

	@Test
	public void testGetState() {
		assertEquals("Test failed for state getter.", Cell.state.unchecked, testCell.getState());
	}
	
	@Test
	public void testSetAsChecked(){
		testCell.setAsChecked(testPlayer.getSign());
		assertEquals("Test failed for cell state setter.", Cell.state.checked, testCell.getState());
	}
	
	@Test
	public void testGetSign() {
		testCell.setAsChecked(testPlayer.getSign());
		assertEquals("Test failed for sign getter." , 'x', testCell.getSign());
	}

}

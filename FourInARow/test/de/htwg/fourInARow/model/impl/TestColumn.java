package de.htwg.fourInARow.model.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestColumn {

	Column testCol1;
	Column testCol2;
	Player testPlayer;
	
	@Before
	public void setUp() throws Exception {
		testCol1 = new Column(10);	
		testCol2 = new Column(10);
		testPlayer = new Player("test", Player.mode.human, 'x');
		
		for(int i = 0; i < 10; i++)
			testCol2.addChip(testPlayer);
	}

	@After
	public void tearDown() throws Exception {
		testCol1 = null;
		testCol2 = null;
		testPlayer = null;
	}

	@Test
	public void testHasSpace() {
		assertEquals("Test failed for hasSpace method.",  true, testCol1.hasSpace());
		assertEquals("Test failed for hasSpace method.", false, testCol2.hasSpace());
	}
	
	@Test
	public void testAddChip(){		
		assertEquals("Test failed for addChip method.",  true, testCol1.addChip(testPlayer));
		assertEquals("Test failed for addChip method.", false, testCol2.addChip(testPlayer));
	}
	
	@Test 
	public void testGetCell() {
		testCol1.addChip(testPlayer);
		assertEquals("Test failed for getCell method.", Cell.state.checked, testCol1.getCell(0).getState());
		assertEquals("Test failed for getCell method.",  Cell.state.unchecked, testCol1.getCell(2).getState());
		assertNull("Test failed for getCell method.", testCol1.getCell(-1));
		assertNull("Test failed for getCell method.",  testCol1.getCell(11));		
	}

}

package de.htwg.fourInARow.model.mock;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

	Player testPlayer1;
	Player testPlayer2;
	Playground testField;
	
	@Before
	public void setUp() throws Exception {
		testPlayer1 = new Player();	
		testPlayer2 = new Player("test2", Player.mode.computer, 'o');
		
		testField = new Playground(10, 10);
	}

	@After
	public void tearDown() throws Exception {
		testPlayer1 = null;
		testPlayer2 = null;
		testField = null;
	}

    @Test
    public void testToString() {
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer();				
		result.append("Sign: " + "o" + newLine);
		result.append("Name: " + "test2" + newLine);
		result.append("Session: " + "computer" + newLine);
		
		assertEquals(result.toString(), testPlayer2.toString());
    }
    
	@Test
	public void testGetSign() {
		assertEquals("Test failed for getSign method.", 'x', testPlayer1.getSign());
	}
	
	@Test
	public void testSetSign() {
		testPlayer1.setSign('i');
		assertEquals("Test failed for getSign method.", 'i', testPlayer1.getSign());
	}	
	
	@Test
	public void testGetName() {
		assertEquals("Test failed for getSign method.", "Player", testPlayer1.getName());
	}
	
	@Test
	public void testSetName() {
		testPlayer1.setName("TestName");
		assertEquals("Test failed for getSign method.", "TestName", testPlayer1.getName());
	}	
	
	@Test
	public void testGetSession() {
		assertEquals("Test failed for getSign method.", Player.mode.human, testPlayer1.getSession());
	}
	
	@Test
	public void testSetSession() {
		testPlayer1.setSession(Player.mode.computer);
		assertEquals("Test failed for getSign method.", Player.mode.computer, testPlayer1.getSession());
	}
	
}

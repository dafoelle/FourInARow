package de.htwg.fourInARow.model.impl;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPlayground {

	Playground testObj1 = null;
	Playground testObj2 = null;
	Playground testObj3 = null;
	
	@Before
	public void setUp() throws Exception {
		testObj1 = new Playground();
		testObj2 = new Playground(100, 0);
		testObj3 = new Playground(-1, -1000);
	}

	@After
	public void tearDown() throws Exception {
		testObj1 = null;
		testObj2 = null;
		testObj3 = null;
	}
	
	@Test
	public void testConstructorWithParams(){		
		assertEquals("Test failed for constructor with parameters.", 5, testObj2.getHeight());
		assertEquals("Test failed for constructor with parameters.", 5, testObj2.getWidth());
		assertEquals("Test failed for constructor with parameters.", 5, testObj3.getHeight());
		assertEquals("Test failed for constructor with parameters.", 5, testObj3.getWidth());
	}
	
	@Test 
	public void testAddChip() {		
		assertEquals("Test failed for addChip method.", true, testObj1.addChip(1, new Player()));
		assertEquals("Test failed for addChip method.", false, testObj1.addChip(-1, new Player()));
	}
	
	@Test 
	public void testGetColumn() {
		assertNotNull("Test failed for getColumn method.", testObj1.getColumn(1));
		assertNull("Test failed for getColumn method.", testObj1.getColumn(-100));
		assertNotNull("Test failed for getColumn method.", testObj2.getColumn(0));
	}
	
	@Test 
	public void testGetColumns() {
		assertNotNull("Test failed for getColumns method.", testObj1.getColumns());
	}
	
	@Test
	public void testClear() {
		testObj1.addChip(1, new Player());
		assertEquals(Cell.state.checked, testObj1.getColumn(1).getCell(0).getState());
		testObj1.clear();
		assertEquals(Cell.state.unchecked, testObj1.getColumn(1).getCell(0).getState());
	}
	
	@Test 
	public void testGetHeight() {
		testObj1 = new Playground(5, 6);
		assertEquals("Test failed for getHeight method.", 6, testObj1.getHeight());
	}
	
	@Test 
	public void testGetWidth() {
		testObj1 = new Playground(5, 6);
		assertEquals("Test failed for getWidth method.", 5, testObj1.getWidth());
	}
	
	@Test 
	public void testGetScannerSize() {
		assertEquals("Test failed for getScanenerSize method.", 4, testObj1.getScannerSize());
	}
	
	@Test 
	public void testSetHeight() {
		testObj1.setHeight(10);
		assertEquals("Test failed for setHeight method.", 10, testObj1.getHeight());
	}
	
	@Test 
	public void testSetWidth() {
		testObj1.setWidth(11);
		assertEquals("Test failed for setWidth method.", 11, testObj1.getWidth());
	}
	
	@Test 
	public void testSetScannerSize() {
		testObj1.setScannerSize(-2);
		assertEquals("Test failed for setScannerSize method.", 4, testObj1.getScannerSize());
		testObj1.setScannerSize(4);
		assertEquals("Test failed for setScannerSize method.", 4, testObj1.getScannerSize());
		testObj1.setScannerSize(100);
		assertEquals("Test failed for setScannerSize method.", 4, testObj1.getScannerSize());
		testObj1.setScannerSize(6);
		assertEquals("Test failed for setScannerSize method.", 6, testObj1.getScannerSize());
	}
	
	@Test
	public void testIsReady() {
		assertFalse(testObj3.isReady());
		fillGrid(testObj3);
		assertTrue(testObj3.isReady());		
	}
	
	@Test
	public void testHasFinished() {
		testObj3.clear();
		assertFalse(testObj3.hasFinished());
		fillHorizontal(testObj3);
		assertTrue(testObj3.hasFinished());
		testObj3.clear();
		fillVertical(testObj3);
		assertTrue(testObj3.hasFinished());
		testObj3.clear();
		fillDiagonal1(testObj3);
		assertTrue(testObj3.hasFinished());
		testObj3.clear();
		fillDiagonal2(testObj3);
		assertTrue(testObj3.hasFinished());
		testObj3.clear();
		assertFalse(testObj3.hasFinished());
	}
	
	@Test
	public void testToSettingString() {
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer();
		result.append("Width: " + "5" + newLine);
		result.append("Height: " + "5" + newLine);
		result.append("Scanner size: " + "4" + newLine);
		
		assertEquals(result.toString(), testObj3.toSettingString());
	}
	
	@Test
	public void testToString() {
		testObj3.clear();
		testObj3.addChip(1, new Player("test", Player.mode.human, 'O'));
		
		String newLine = System.getProperty("line.separator");
		StringBuffer result = new StringBuffer();
		result.append("+---+---+---+---+---+" + newLine);
		result.append("|   |   |   |   |   |" + newLine);
		result.append("+---+---+---+---+---+" + newLine);
		result.append("|   |   |   |   |   |" + newLine);
		result.append("+---+---+---+---+---+" + newLine);
		result.append("|   |   |   |   |   |" + newLine);
		result.append("+---+---+---+---+---+" + newLine);
		result.append("|   |   |   |   |   |" + newLine);
		result.append("+---+---+---+---+---+" + newLine);
		result.append("|   | O |   |   |   |" + newLine);
		result.append("+---+---+---+---+---+" + newLine);	
		
		assertEquals(result.toString(), testObj3.toString());
	}
	
	private void fillGrid(Playground pg) {
		for(int i=0; i < pg.getWidth(); i++) {
			while(pg.getColumn(i).hasSpace()) {
				pg.addChip(i, new Player());
			}
		}
	}
	
	private void fillHorizontal(Playground pg) {
		for(int i = 0; i < pg.getWidth(); i++) {
			pg.addChip(i, new Player());
		}
	}
	
	private void fillVertical(Playground pg) {
		for(int i = 0; i < pg.getHeight(); i++) {
			pg.addChip(0, new Player());
		}
	}
	
	private void fillDiagonal1(Playground pg) {
		Player tmp1 = new Player("test1", Player.mode.human, 'O');
		Player tmp2 = new Player("test2", Player.mode.human, 'X');
		for(int i = 0; i < pg.getScannerSize(); i++) {
			for(int j = i; j > 0; j--) {
				pg.addChip(i, tmp1);
			}
			pg.addChip(i, tmp2);
		}
	}

	private void fillDiagonal2(Playground pg) {
		Player tmp1 = new Player("test1", Player.mode.human, 'O');
		Player tmp2 = new Player("test2", Player.mode.human, 'X');
		for(int i = 0; i < pg.getScannerSize(); i++) {
			for(int j = pg.getScannerSize()-i-1; j > 0; j--) {
				pg.addChip(i, tmp1);
			}
			pg.addChip(i, tmp2);
		}
	}
}

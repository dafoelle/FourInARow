package de.htwg.util;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;

public class ConstantTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testPadding() {
		assertNotNull(Constants.COLORS);
		assertTrue(Constants.PADDING >= 0);
		assertTrue(Constants.PADDING < 1000);
	}

	@Test
	public void testColors() {
		assertNotNull(Constants.COLORS);
		assertFalse(Constants.COLORS.size() == 0);
	}
	
	@Test
	public void testGetColorForChar() {
		assertEquals(Color.YELLOW, Constants.getColorForChar('#'));
		assertEquals(Color.RED, Constants.getColorForChar('O'));
		assertEquals(Color.BLUE, Constants.getColorForChar('X'));
		assertEquals(Color.GREEN, Constants.getColorForChar('A'));
		assertEquals(Color.ORANGE, Constants.getColorForChar('B'));
		assertEquals(Color.BLACK, Constants.getColorForChar('C'));
	}
	
	@Test
	public void testGetCharForColorString() {
		assertEquals('#', Constants.getCharForColorString("Yellow"));
		assertEquals('O', Constants.getCharForColorString("Red"));
		assertEquals('X', Constants.getCharForColorString("Blue"));
		assertEquals('A', Constants.getCharForColorString("Green"));
		assertEquals('B', Constants.getCharForColorString("Orange"));
		assertEquals('C', Constants.getCharForColorString("Black"));
	}
	
	@Test
	public void testGetStringForColor() {
		assertNull(Constants.getStringForColor(Color.YELLOW));
		assertEquals("Red", Constants.getStringForColor(Color.RED));
		assertEquals("Blue", Constants.getStringForColor(Color.BLUE));
		assertEquals("Green", Constants.getStringForColor(Color.GREEN));
		assertEquals("Orange", Constants.getStringForColor(Color.ORANGE));
		assertEquals("Black", Constants.getStringForColor(Color.BLACK));
	}
}

package de.htwg.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Constants
 * @author David Föllenz, 21/05/2013
 * Static class of constants and helper functions.
 */
public final class Constants {
		
	/*
	 * important padding for GUI
	 */
	public static final int PADDING = 40;
	
	/*
	 * Map of Colors to assign to player (Color - Color as char).
	 */
	public static final Map<Character, Color> COLORS = new HashMap<Character, Color>(){
		private static final long serialVersionUID = 1L;
	{
	       put('O', Color.RED);
	       put('X', Color.BLUE);
	       put('A', Color.GREEN);
	       put('B', Color.ORANGE);
	       put('C', Color.BLACK);
	}};
	
	/*
	 * Map of Colors assigned to player (Color - Color as string).
	 */
	private static final Map<Color, String> COLORNAMES = new HashMap<Color, String>(){
		private static final long serialVersionUID = 1L;
	{
	       put(Color.RED, "Red");
	       put(Color.BLUE, "Blue");
	       put(Color.GREEN, "Green");
	       put(Color.ORANGE, "Orange");
	       put(Color.BLACK, "Black");
	}};
	       
	/*
	 * Returns the Color assigned to passed char.
	 * @param val: char to convert in color.
	 */
	public static Color getColorForChar(char val) {
		Color tmp = COLORS.get(val);
		return tmp != null ? tmp : Color.YELLOW;
	}
	
	/*
	 * Returns assigned char of color as string.
	 * @param col: string to convert in char.
	 */
	public static char getCharForColorString(String col) {
		
		Color tmpCol = null;
		for (Entry<Color, String> e : COLORNAMES.entrySet()) {
			if(e.getValue().equals(col)) {
				tmpCol = e.getKey();
			}
		}
		
		if(tmpCol != null) {
			for (Entry<Character, Color> e : COLORS.entrySet()) {
				if(e.getValue().equals(tmpCol)) {
					return e.getKey();
				}
			}
		}
		return '#';
		
	}
	
	/*
	 * Returns a string of passed color.
	 * @param val: Color to convert in string.
	 */
	public static String getStringForColor(Color val) {
		String tmp = COLORNAMES.get(val);
		return tmp != null ? tmp : null;
	}
}

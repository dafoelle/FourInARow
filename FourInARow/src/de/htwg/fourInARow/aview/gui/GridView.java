package de.htwg.fourInARow.aview.gui;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;


public class GridView {
	
	private static final int PADDING = 40;
	
	private Map<Point, Rectangle> grid;
	private int width;
	private int height;
	
	public GridView(int width, int height) {
		this.grid = new HashMap<Point, Rectangle>();
		this.width = width;
		this.height = height;
		generateColumns();
	}
	
	public Map<Point, Rectangle> getGrid() {
		return grid;
	}	
	
	private void generateColumns() {
		int xStart = 0;
		
		for(int col = 0; col < width; col++) {		
			int yStart = 0;
			for (int row = height-1; row >= 0; row--) {	
				int xEnd = PADDING;
				int yEnd = PADDING;
				grid.put(new Point(row, col), new Rectangle(xStart, yStart, xEnd, yEnd));
				yStart += PADDING;
			}	
			xStart += PADDING;
		}
	}
}
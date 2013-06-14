package de.htwg.fourInARow.aview.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.fourInARow.model.ICell;
import de.htwg.util.Constants;
import de.htwg.util.Observer.IObserver;

public class GamePanel extends JPanel implements IObserver {

	private static final long serialVersionUID = 1L;
	private IFourInARowController controller = null;
	private final int PADDING = Constants.PADDING;	
	private GridView grid = null;
	private int activeColumn = 1;
	
	public GamePanel(IFourInARowController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MouseHandler(arg0); 
			}
		});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				SelectionHandler(arg0);
			}
		}); 
		
		grid = new GridView(controller.getPlayground().getWidth(), controller.getPlayground().getHeight());
	}

	@Override
	public void update() {
		repaint();
	}
	
	public void SelectionHandler(MouseEvent event) {
		this.activeColumn = getMouseColumn(event);
	}
	
	public void MouseHandler(MouseEvent event) {
		int col = getMouseColumn(event);
		
		if(!controller.hasFinished()) {
			controller.nextMove(col);
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;	        
    	generateGridView(g2d);
    }
	
	private void generateGridView(Graphics2D g2d) {
		
		for(int col = 0; col < controller.getPlayground().getWidth(); col++) {		
			for (Entry<Point, Rectangle> e : grid.getGrid().entrySet()) {
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.drawRect(e.getValue().x, e.getValue().y, PADDING, PADDING);
				g2d.setColor(getColorForCell(e.getKey()));				
				if(g2d.getColor() != null) {
					g2d.fillOval(e.getValue().x+2, e.getValue().y+2, PADDING-4, PADDING-4);
				}
				if(controller.getPlayground().getColumn(activeColumn-1).hasSpace()) {
					Stroke oldStroke = g2d.getStroke();
					g2d.setStroke(new BasicStroke(5));				 
					g2d.setColor(Color.GREEN);
					g2d.drawRect(activeColumn*PADDING-PADDING, 0, PADDING, controller.getPlayground().getHeight()*PADDING);
					g2d.setStroke(oldStroke);
				}				
			}
		}
		this.setSize(controller.getPlayground().getWidth()*PADDING,  controller.getPlayground().getHeight()*PADDING);
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
	}
	
	private Color getColorForCell(Point key) {
		ICell tmp = controller.getPlayground().getColumn(key.y).getCell(key.x);
		if(tmp.getState() != ICell.state.unchecked) {
			return Constants.getColorForChar(tmp.getSign());
		}
		else {
			return null;
		}
	}

	private int getMouseColumn(MouseEvent event) {	 
		 return event.getX() / PADDING +1;
	 }	
}

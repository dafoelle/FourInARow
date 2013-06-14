package de.htwg.fourInARow.aview.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;

import de.htwg.fourInARow.controller.IFourInARowController;
import de.htwg.util.Observer.IObserver;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.google.inject.Inject;

public class GraphicUI extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private IFourInARowController controller;
	private JPanel grid;
	
	private JLabel lblStatus;
	private JLabel lblTurn;
	
	/**
	 * Create the frame.
	 */
	@Inject
	public GraphicUI(IFourInARowController controller) {
		this.controller = controller;
		controller.addObserver(this);
		setResizable(true);
		this.setVisible(true);
		setTitle("X in a Row");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 990, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblStatus = new JLabel("Status: ");		
		lblTurn = new JLabel("Turn message: ");
		grid = new GamePanel(this.controller);
		JScrollPane scrollBar = new JScrollPane(grid);
		scrollBar.setPreferredSize(new Dimension(1500, 1500));        
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(142)
					.addComponent(scrollBar)
					//.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(143, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTurn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblStatus)
							.addContainerGap(11, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblStatus)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTurn)
						.addGap(47)
						.addComponent(scrollBar)
						//.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(41, Short.MAX_VALUE))
			);
		
		
				
		
		//contentPane.add(scrollBar, BorderLayout.CENTER);
	
		contentPane.setLayout(gl_contentPane);
		createMenu();	
	}
	
	private void OpenPlayerConfiguration() {
		JDialog config = new ConfigurationManager(this, controller);
		config.setVisible(true);
		this.setEnabled(false);
	}
	
	private void OpenPlaygroundConfiguration() {
		JDialog config = new GridManager(this, controller);
		config.setVisible(true);
		this.setEnabled(false);
	}
	
	public final void createMenu() {

        JMenuBar menubar = new JMenuBar();

        JMenu gen = new JMenu("General");
        gen.setMnemonic(KeyEvent.VK_G);

        JMenu settings = new JMenu("Settings");
        settings.setMnemonic(KeyEvent.VK_S);

        JMenuItem playerSettings = new JMenuItem("Player...");
        playerSettings.setToolTipText("Modify player");
        
        playerSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	OpenPlayerConfiguration();
            }
        });
        
        JMenuItem playgroundSettings = new JMenuItem("Playground...");
        playgroundSettings.setToolTipText("Modify playground");
        
        playgroundSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	OpenPlaygroundConfiguration();
            }
        });
        
        settings.add(playerSettings);
        settings.add(playgroundSettings);

        JMenuItem fileNew = new JMenuItem("New Game");
        fileNew.setMnemonic(KeyEvent.VK_N);
        fileNew.setToolTipText("Start new game");
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
        ActionEvent.CTRL_MASK));
        
        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.setMnemonic(KeyEvent.VK_C);
        fileExit.setToolTipText("Exit application");
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
            ActionEvent.CTRL_MASK));

        fileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });
        
        fileNew.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		controller.newGame();
        	}
        });

        gen.add(settings);
        gen.add(fileNew);
        gen.addSeparator();
        gen.add(fileExit);

        menubar.add(gen);
        
        JMenuItem info = new JMenuItem("Info");
        gen.setMnemonic(KeyEvent.VK_I);
        
        menubar.add(info);
        

        setJMenuBar(menubar);

        setTitle("X in a Row");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	public void setGrid() {
		grid = new GamePanel(this.controller);
	}
	
	@Override
	public void update() {
		lblStatus.setText(controller.getStatus());
		lblTurn.setText(controller.getTurnMessage());
	}
}

package de.htwg.fourInARow.aview.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import de.htwg.fourInARow.controller.IFourInARowController;

public class GridManager extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtWidth;
	private JTextField txtHeight;
	private IFourInARowController controller;
	private GraphicUI parent;
	
	public GridManager(GraphicUI parent, IFourInARowController controller) {
		this.controller = controller;
		this.parent = parent;
		setTitle("Grid Configuration");
		setBounds(100, 100, 251, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtWidth = new JTextField();
			txtWidth.setColumns(10);
		}
		JLabel lbWidth  = new JLabel("Rows:   ");
		JLabel lbHeight = new JLabel("Columns:");
		
		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		
		GroupLayout glContentPanel = new GroupLayout(contentPanel);
		glContentPanel.setHorizontalGroup(
			glContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPanel.createSequentialGroup()
					.addGap(4)
					.addGroup(glContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glContentPanel.createSequentialGroup()
							.addComponent(lbWidth)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtWidth, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, glContentPanel.createSequentialGroup()
							.addComponent(lbHeight)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtHeight, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
					.addContainerGap())
		);
		glContentPanel.setVerticalGroup(
			glContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbWidth))
					.addGap(18)
					.addGroup(glContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		contentPanel.setLayout(glContentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btOk = new JButton("OK");
				btOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setBounds();
					}
				});
				btOk.setActionCommand("OK");
				buttonPane.add(btOk);
				getRootPane().setDefaultButton(btOk);
			}
			{
				JButton btCancel = new JButton("Cancel");
				btCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				btCancel.setActionCommand("Cancel");
				buttonPane.add(btCancel);
			}
		}
	}
	
	private void cancel() {
		controller.setStatusMessage("Playground configuration settings canceled.");
		parent.setEnabled(true);
		dispose();
	}
	
	private void setBounds() {
		if(txtWidth.getText().equals("") || txtHeight.getText().equals("")) {
			JOptionPane.showMessageDialog(parent, "Validation error: Please fill all fields.");
		}else {
		
			try {
				
			int width = Integer.parseInt(txtWidth.getText());
			int height = Integer.parseInt(txtHeight.getText());
			
			if(width < 0 || height < 0 || width > 7 || height > 15) {
				throw new RuntimeException("Invalid values.");
			}
			
			controller.getPlayground().setWidth(height);
			controller.getPlayground().setHeight(width);
		
			controller.setStatusMessage("Playground configuration changed.");
			parent.dispose();
			parent = new GraphicUI(this.controller);
			this.dispose();
			} catch (Exception ex){
				JOptionPane.showMessageDialog(parent, "Validation error: The you entered is not valid.");
			}
		}
		
	}
	
}

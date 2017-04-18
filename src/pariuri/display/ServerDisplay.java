package pariuri.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pariuri.logic.Game;

public class ServerDisplay extends Display{

	private JFrame frame;
	private String title;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerDisplay window = new ServerDisplay();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerDisplay() {
		title = "Server";
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {

		super.initialize();

		// Admin panel
		JPanel adminPanel = new JPanel();
		adminPanel.setLayout(new FlowLayout());
		adminPanel.setBackground(Color.orange);
		adminPanel.setPreferredSize(new Dimension(container.getWidth(),container.getHeight()/10));

		JButton adminPanelButton = new JButton("Add game");
		adminPanelButton.setPreferredSize(new Dimension(80,40));
		adminPanelButton.setFont(new Font("Arial",Font.PLAIN,8));
		JPanel addGamePanel = new JPanel();
		addGamePanel.setBackground(Color.orange);
		addGamePanel.setLayout(new FlowLayout());
		addGamePanel.setPreferredSize(new Dimension(550,40));
		addGamePanel.setBounds(80, 0, 550, 0);
		addGamePanel.setVisible(true);
		
		adminPanelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int compWidth = 120;
				
				JTextField teamA = new JTextField("teamA");
				teamA.setBounds(0,0,compWidth,40);
				
				JTextField teamB = new JTextField("teamB");;
				teamB.setBounds(compWidth,0,compWidth,40);
				
				JTextField stakeA = new JTextField("0");
				stakeA.setBounds(compWidth*2,0,compWidth,40);
				
				JTextField stakeB = new JTextField("0");
				stakeB.setBounds(compWidth*3,0,compWidth,40);
				
				JButton button = new JButton("Go");
				button.setBounds(compWidth*4, 0, 70, 40);
				
				button.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e){
						Game game = new Game(teamA.getText(),Float.parseFloat(stakeA.getText()),teamB.getText(),Float.parseFloat(stakeB.getText()));
						betsContainer.add(game.getPanel());
						betsContainer.repaint();
						button.setVisible(false);
						teamA.setVisible(false);
						teamB.setVisible(false);
						stakeA.setVisible(false);
						stakeB.setVisible(false);
					}
				});
				
				addGamePanel.add(teamA);
				addGamePanel.add(teamB);
				addGamePanel.add(stakeA);
				addGamePanel.add(stakeB);
				addGamePanel.add(button);
				addGamePanel.repaint();
			}
			
		});
		
		adminPanel.add(adminPanelButton,FlowLayout.LEFT);
		adminPanel.add(addGamePanel);
		
		//
		
		container.add(adminPanel,BorderLayout.PAGE_START);

	}

}

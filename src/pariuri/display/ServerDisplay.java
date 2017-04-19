package pariuri.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pariuri.logic.Game;
import pariuri.networking.Server;

public class ServerDisplay extends Display{



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

	public ServerDisplay() {
		games = new ArrayList<Game> ();
		initialize();
	}


	protected void initialize() {

		super.initialize();
		frame.setTitle("Pariuri Online - Server");
		
		//DE SCHIMBAT
		frame.setLocation(200,200);
		// Admin panel
		JPanel adminPanel = new JPanel();
		adminPanel.setLayout(new BorderLayout());
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
						games.add(game);
						JPanel p = (JPanel) game.getPanel().getComponent(2);

						JButton remove = new JButton("Remove");
						remove.setBackground(Color.red);
						remove.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								remove(game);

							}

						});

						JButton setScore = new JButton("Set Score");
						setScore.setBackground(Color.white);
						
						setScore.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								setScore(game);
								
							}
							
						});

						p.add(remove,BorderLayout.PAGE_START);
						p.add(setScore, BorderLayout.PAGE_END);


						betsContainer.add(game.getPanel());
						betsContainer.add(Box.createRigidArea(new Dimension(0,5)));
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

		adminPanel.add(adminPanelButton,BorderLayout.LINE_START);
		adminPanel.add(addGamePanel);

		//

		container.add(adminPanel,BorderLayout.PAGE_START);

	}

	public void remove(Game game){	
		game.getPanel().setVisible(false);
		games.remove(game);
		betsContainer.repaint();
	}
	
	public void setScore(Game game){
		JFrame frame = new JFrame("set score");
		frame.setVisible(true);
		frame.setBounds(0,0,400,100);
		frame.setLocation(700,500);
		frame.setLayout(new FlowLayout());
		frame.setAlwaysOnTop(true);
		
		JTextField scoreA = new JTextField("0");
		scoreA.setPreferredSize(new Dimension(120,40));
		
		JTextField scoreB = new JTextField("1");
		scoreB.setPreferredSize(new Dimension(120,40));
		
		JButton button = new JButton("Set");
		button.setPreferredSize(new Dimension(100,40));
		
		frame.add(scoreA);
		frame.add(scoreB);
		frame.add(button);
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				games.remove(game);
				frame.setVisible(false);
				game.setScore(Integer.parseInt(scoreA.getText()), Integer.parseInt(scoreB.getText()));
				games.add(game);
				Server.modified = true;
			}
			
		});
		
	}

	
	public ArrayList<Game> getGames() {
		if(games.size() == 0)
			return new ArrayList<Game>();
		return games;
	}
}


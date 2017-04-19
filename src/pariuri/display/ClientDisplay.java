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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.api.Component;

import javafx.scene.layout.Border;
import pariuri.logic.Game;

public class ClientDisplay extends Display{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientDisplay window = new ClientDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientDisplay() {
		games = new ArrayList<Game> ();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		super.initialize();
		
		//DE SCHIMBAT
		JFrame ticketFrame = new JFrame("My bets");
		ticketFrame.setBounds(0,0,350,frame.getHeight());
		ticketFrame.setLocationRelativeTo(frame);
		ticketFrame.setVisible(true);
		ticketFrame.setAlwaysOnTop(true);
		ticketFrame.setLocation(frame.getX()+frame.getWidth(),frame.getY());
		frame.setTitle("Pariuri online - Client");
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		userPanel.setBackground(Color.orange);
		userPanel.setPreferredSize(new Dimension(container.getWidth(),container.getHeight()/10));
		userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel helloMessage = new JLabel("Hello, user");
		helloMessage.setBounds(0,0,200,container.getHeight()/10);
		helloMessage.setPreferredSize(new Dimension(200,container.getHeight()/10));
		helloMessage.setFont(new Font("Arial",Font.PLAIN,20));
		userPanel.add(helloMessage);
		

		
		container.add(userPanel,BorderLayout.PAGE_START);
	}
	
	private void process(JPanel panel){
		JPanel container = (JPanel) panel.getComponent(2);
		container.setBackground(Color.white);
		java.awt.Component[] c = container.getComponents();
		JButton button = (JButton) c[3];
		button.setText("Add to ticket");
		button.setBackground(Color.yellow);
		JButton button2 = (JButton) c[4];
		button2.setText("Remove");
		button2.setBackground(Color.yellow);
		button2.setEnabled(false);
		
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				button2.setEnabled(true);
				button.setEnabled(false);
				
			}
			
		});	
		button2.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				button.setEnabled(true);
				button2.setEnabled(false);
			}
		});

	} 
	
	public void refresh(){
		betsContainer.removeAll();
		for(Game g : games){
			JPanel panel = (JPanel)g.getPanel();
			process(panel);
			betsContainer.add(panel);
		}
		betsContainer.repaint();
	}
	
	public void setGames(ArrayList <Game> games){
		this.games = games;
		refresh();
	}
	
	public ArrayList<Game> getGames(){
		if(games.size() == 0){
			return new ArrayList<Game>();
		}			
		return games;
	}

	public void removeAllGames() {
		betsContainer.removeAll();
		betsContainer.repaint();
		
	}


	public void removeAllAndAdd(ArrayList<Game> serverGames) {
		for(Game g : games)
			{
			g.getPanel().setVisible(false);
			}
		games.clear();
		games.addAll(serverGames);
		for(Game g: games){
			g.getPanel().setVisible(true);
		}
	}

}

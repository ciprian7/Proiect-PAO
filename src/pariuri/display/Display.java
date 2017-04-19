package pariuri.display;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pariuri.logic.Game;

public abstract class Display{

	protected String title;
	protected JFrame frame = new JFrame();
	protected JPanel container;
	public JPanel betsContainer;
	protected JPanel bottomContainer;
	protected ArrayList<Game> games;  

	
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	
	public double width = (int)screenSize.getWidth();
	public double height = (int)screenSize.getHeight();


	protected void initialize() {
		//frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setSize(670,480);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.setTitle(title);
		frame.setLocationRelativeTo(null);
		int x = (int)( (width - frame.getWidth()) / 2);
		int y = (int)( (height = frame.getHeight()) / 2);
		frame.setLocation(x, y);

		
		container = new JPanel();
		container.setBackground(Color.green);
		container.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		container.setLayout(new BorderLayout());
		
		
		betsContainer = new JPanel();
		betsContainer.setLayout(new BoxLayout(betsContainer,BoxLayout.Y_AXIS));
		
		betsContainer.setBounds(0,40,640,160);
		betsContainer.setBackground(Color.darkGray);
		
		JScrollPane scroll = new JScrollPane(betsContainer);
		betsContainer.setAutoscrolls(true);
		scroll.setPreferredSize(new Dimension(640,240));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

		
		bottomContainer = new JPanel();
		bottomContainer.setLayout(new FlowLayout());
		bottomContainer.setBackground(Color.red);
		bottomContainer.setBounds(0,100,640,80);
		
		container.add(scroll,BorderLayout.CENTER);
		container.add(bottomContainer,BorderLayout.SOUTH);

		
		
		frame.getContentPane().add(container);
		
	}

}

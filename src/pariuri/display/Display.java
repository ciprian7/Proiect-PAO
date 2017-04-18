package pariuri.display;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class Display {

	protected String title;
	protected JFrame frame = new JFrame();
	protected JPanel container;
	protected JPanel betsContainer;
	protected JPanel bottomContainer;
	
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double width = (int)screenSize.getWidth();
	public double height = (int)screenSize.getHeight();


	protected void initialize() {
		//frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setBounds(0, 0, 640, 480);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.setTitle(title);
		int x = (int)( (width - frame.getWidth()) / 2);
		int y = (int)( (height = frame.getHeight()) / 2);
		frame.setLocation(x, y);

		
		container = new JPanel();
		container.setBackground(Color.green);
		container.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		container.setLayout(new BorderLayout());
		
		betsContainer = new JPanel();
		betsContainer.setLayout(new FlowLayout());
		
		betsContainer.setBounds(0,40,640,100);
		betsContainer.setBackground(Color.white);
		
		bottomContainer = new JPanel();
		bottomContainer.setLayout(new FlowLayout());
		bottomContainer.setBackground(Color.red);
		bottomContainer.setBounds(0,100,640,200);
		
		container.add(betsContainer,BorderLayout.CENTER);
		container.add(bottomContainer,BorderLayout.SOUTH);

		
		
		frame.getContentPane().add(container);
		
	}

}

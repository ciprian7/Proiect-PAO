package pariuri.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Game {
	
	String teamA, teamB, winningTeam, userTeam;
	int scoreA, scoreB;
	float stakeA, stakeB;
	JPanel panel;
	static int bets = 0;
	static int lastHeight = 40;
	
	public Game(String teamA, float stakeA ,String teamB, float stakeB){
		
		this.teamA = teamA;
		this.stakeA = stakeA;
		this.teamB = teamB;
		this.stakeB = stakeB;
		this.scoreA = 0;
		this.scoreB = 0;
		initalize();	
	}
	
	public void initalize(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(0, lastHeight, 640, 80);
		panel.setPreferredSize(new Dimension(640,80));
		if(bets % 2 == 0)
			panel.setBackground(Color.green);
		else panel.setBackground(Color.orange);
		
		panel.setLayout(new BorderLayout());
		
		JPanel leftSide = new JPanel();
		leftSide.setBounds(0, 0, 300, 80);
		leftSide.setPreferredSize(new Dimension(300,80));
		leftSide.setLayout(new BorderLayout());
		JLabel teamA = new JLabel(this.teamA);
		teamA.setFont(new Font("Arial",Font.BOLD,10));
		JLabel stakeA = new JLabel("Stake: "+this.stakeA);
		stakeA.setFont(new Font("Arial",Font.PLAIN,9));
		
		JLabel scoreA = new JLabel(this.scoreA+"");
		scoreA.setFont(new Font("Arial",Font.BOLD,15));
		leftSide.add(stakeA,BorderLayout.LINE_START);
		leftSide.add(teamA, BorderLayout.LINE_END);
		
		JPanel rightSide = new JPanel();
		rightSide.setBounds(300, 0, 300, 80);
		rightSide.setPreferredSize(new Dimension(300,80));
		rightSide.setLayout(new BorderLayout());
		JLabel teamB = new JLabel(this.teamB);
		teamB.setFont(new Font("Arial",Font.BOLD,10));
		JLabel stakeB = new JLabel("Stake: "+this.stakeB);
		stakeB.setFont(new Font("Arial",Font.PLAIN,9));
		
		JLabel scoreB = new JLabel(this.scoreB+"");
		scoreB.setFont(new Font("Arial",Font.BOLD,15));
		rightSide.add(stakeB,BorderLayout.LINE_END);
		rightSide.add(teamB, BorderLayout.LINE_START);
		panel.add(leftSide,BorderLayout.LINE_START);
		panel.add(rightSide,BorderLayout.LINE_END);
		
		JLabel score = new JLabel();
		score.setLayout(new BorderLayout());
		score.add(scoreA,BorderLayout.LINE_START);
		score.add(scoreB, BorderLayout.LINE_END);
		panel.add(score,BorderLayout.CENTER);
		rightSide.setBackground(panel.getBackground());
		leftSide.setBackground(panel.getBackground());
		++bets;

		
	}

	public JPanel getPanel() {
		return panel;
	}
	

}

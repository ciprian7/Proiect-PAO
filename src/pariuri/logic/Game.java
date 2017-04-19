package pariuri.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Game implements Serializable{
	
	private static AtomicLong serialVersionUID = new AtomicLong();
	
	String teamA, teamB, winningTeam, userTeam;
	int scoreA, scoreB;
	float stakeA, stakeB;
	JPanel panel;
	public static int bets = 0;
	static int lastHeight = 40;
	private JLabel scoreALabel;
	private JLabel scoreBLabel;
	private String ID;
	
	public static synchronized String createID(){
		return String.valueOf(serialVersionUID.getAndIncrement());
	}
	public Game(String teamA, float stakeA ,String teamB, float stakeB){
		
		this.ID = createID();
		this.teamA = teamA;
		this.stakeA = stakeA;
		this.teamB = teamB;
		this.stakeB = stakeB;
		this.scoreA = 0;
		this.scoreB = 0;
		initalize();	
	}
	
	public Game(String teamA2, float stakeA2, String teamB2, float stakeB2, int i, int j) {
		this.teamA = teamA;
		this.stakeA = stakeA;
		this.teamB = teamB;
		this.stakeB = stakeB;
		this.scoreA = i;
		this.scoreB = j;
		initalize();	
	}

	public String getTeamA() {
		return teamA;
	}

	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	public String getTeamB() {
		return teamB;
	}

	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}

	public String getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}

	public String getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}

	public float getStakeA() {
		return stakeA;
	}

	public void setStakeA(float stakeA) {
		this.stakeA = stakeA;
	}

	public float getStakeB() {
		return stakeB;
	}

	public void setStakeB(float stakeB) {
		this.stakeB = stakeB;
	}

	public void initalize(){
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(0, 0, 640, 80);
		panel.setMaximumSize(new Dimension(640,80));
		panel.setBackground(Color.white);
		
		panel.setLayout(new BorderLayout());
		
		JPanel leftSide = new JPanel();
		leftSide.setBounds(0, 0, 260, 80);
		leftSide.setPreferredSize(new Dimension(260,80));
		leftSide.setLayout(new BorderLayout());
		JLabel teamA = new JLabel(this.teamA+"     ");
		teamA.setFont(new Font("Arial",Font.BOLD,10));
		JLabel stakeA = new JLabel(" Stake: "+this.stakeA);
		stakeA.setFont(new Font("Arial",Font.PLAIN,9));
		
		scoreALabel = new JLabel(this.scoreA+"");
		scoreALabel.setFont(new Font("Arial",Font.BOLD,15));
		leftSide.add(stakeA,BorderLayout.LINE_START);
		leftSide.add(teamA, BorderLayout.LINE_END);
		
		JPanel rightSide = new JPanel();
		rightSide.setBounds(260, 0, 260, 80);
		rightSide.setPreferredSize(new Dimension(260,80));
		rightSide.setLayout(new BorderLayout());
		JLabel teamB = new JLabel("     "+this.teamB);
		teamB.setFont(new Font("Arial",Font.BOLD,10));
		JLabel stakeB = new JLabel("Stake: "+this.stakeB+"");
		stakeB.setFont(new Font("Arial",Font.PLAIN,9));
		
		scoreBLabel = new JLabel(this.scoreB+"");
		scoreBLabel.setFont(new Font("Arial",Font.BOLD,15));
		rightSide.add(stakeB,BorderLayout.LINE_END);
		rightSide.add(teamB, BorderLayout.LINE_START);
		panel.add(leftSide,BorderLayout.LINE_START);
		panel.add(rightSide,BorderLayout.LINE_END);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BorderLayout());
		
		JLabel line = new JLabel("        -        ");
		line.setFont(new Font("Arial",Font.BOLD,20));
		scorePanel.add(scoreALabel,BorderLayout.LINE_START);
		scorePanel.add(scoreBLabel, BorderLayout.LINE_END);
		scorePanel.add(line, BorderLayout.CENTER);

		panel.add(scorePanel,BorderLayout.CENTER);
		rightSide.setBackground(panel.getBackground());
		leftSide.setBackground(panel.getBackground());
		scorePanel.setBackground(panel.getBackground());
		
		JLabel IDLabel = new JLabel("Bet #"+this.ID);
		IDLabel.setFont(new Font("Arial",Font.PLAIN,15));
		IDLabel.setForeground(Color.blue);
		panel.add(IDLabel, BorderLayout.PAGE_END);

		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setScore(int i, int j) {
		scoreA = i;
		scoreB = j;
		scoreALabel.setText(i+"");
		scoreBLabel.setText(j+"");
		scoreALabel.repaint();
		scoreBLabel.repaint();
		
	}


	@Override
	public boolean equals(Object o) {
		Game game = (Game) o;
		return this.ID.equals(game.ID);
	}
		
	public int getScoreA(){
		return this.scoreA;
	}
	
	public int getScoreB(){
		return this.scoreB;
	}
	

}

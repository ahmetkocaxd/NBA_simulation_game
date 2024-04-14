/**
 * 
 * this is the Team class that contains necessary fields of a Team. There are basic methods inside and a calculateScore
 * method which is defined below
 */

package team;

import java.util.ArrayList;

import player.Player;

public class Team {
	
	private ArrayList<Player> players;
	private int score;
	private String teamName;
	private int win = 0;
	private int lose = 0;
	private int draw = 0;
	private int matchPlayed = 0;
	private boolean home;
	
	
	public Team(String teamName) {
		
		this.teamName = teamName;
		this.score = 0;
		players = new ArrayList<>();
		
	}
	
	


	public int getMatchPlayed() {
		return matchPlayed;
	}




	public void setMatchPlayed(int matchPlayed) {
		this.matchPlayed = matchPlayed;
	}




	public int getWin() {
		return win;
	}




	public void setWin(int win) {
		this.win = win;
	}




	public int getLose() {
		return lose;
	}




	public void setLose(int lose) {
		this.lose = lose;
	}




	public int getDraw() {
		return draw;
	}




	public void setDraw(int draw) {
		this.draw = draw;
	}




	public boolean isHome() {
		return home;
	}




	public void setHome(boolean home) {
		this.home = home;
	}




	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public void addPlayer(Player player) {
		
		players.add(player);		
	}
	
	public void incrementWin() {
		this.win++;
		this.matchPlayed++;
	}
	
	public void incremnetLose() {
		this.lose++;
		this.matchPlayed++;
	}
	
	public void incrementDraw() {
		
		this.draw++;
		this.matchPlayed++;
	}
	
	/**
	 * calculates Score of a team by calculating every player's score and adding them up. 
	 * calculateScore() method of the player calculates the randomized score of the player and defined in player classes.
	 */
	public void calculateTeamScore() {
		
		int totalScore = 0;
		for(Player player : players) {
			
			player.calculateScore();
			totalScore += player.getScore();
			}
		this.setScore(totalScore);
	}
}

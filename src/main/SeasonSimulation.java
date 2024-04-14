/**
 * 
 * This is the SeasonSimulation class which implements the interface Runnable. In order to create a thread of this class and
 * manipulate its pause-resume condition, I implemented this interface.
 */

package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import swing.Season;
import team.Team;
import user.User;

public class SeasonSimulation implements Runnable {
	
	private ArrayList<Team> teams;
	private boolean pause = false;
	private Season game;
	private Team team1;
	private Team team2;
	private User user;
	

	public SeasonSimulation(ArrayList<Team> teams, Season game) {
		this.teams = teams;
		this.pause = false;
		this.game = game;
	}
	
	public void changePause() {
		
		if(pause) {
			
			this.pause = false;
			
		}else {
			this.pause = true;
		}
		
	}
	

	public void pauseSeason() {
		
		this.pause = true;
	}
	
	public void resumeSeason() {
		
		this.pause = false;
	}
	
	//this is for updating the Season frame, Season object is one of the parameters of the constructor
	private void updateFrame() {
		
		 SwingUtilities.invokeLater(() -> {
			 
			 
			 game.updateList(team1, team2, "Season.txt");
			 game.updateTable(teams);
			 
			 
		 });
		
	}
	




	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//remove old log file if exists
		File oldSeason = new File("Season.txt");
		
		if(oldSeason.exists()) {
			
			oldSeason.delete();
		}
		Collections.sort(teams, Comparator.comparingInt(Team::getMatchPlayed));
		Random random = new Random();
		int matchNumber = random.nextInt(20) + 2*teams.size();
		
		//while the least match player team's match number is smaller than match number, continue
		while(teams.get(0).getMatchPlayed() < matchNumber) {
			
			//if not paused
			if(!pause) {
				
				//get random 2 teams from teams ArrayList
				team1 = teams.get(random.nextInt(teams.size()));
				team2 = teams.get(random.nextInt(teams.size()));
				
				//if two teams are the same team or team has reached the number of matches needed, I assign
				//two teams as the first two teams in the list (which have the least number of matches)
				while(team1.equals(team2) || team1.getMatchPlayed() == matchNumber) {
					
					team1 = teams.get(0);
					team2 = teams.get(1);
				}
				
				while(team1.equals(team2) || team2.getMatchPlayed() == matchNumber) {
					team1 = teams.get(0);
					team2 = teams.get(1);
				}
				
				//calculate their randomized score
				team1.calculateTeamScore();
				team2.calculateTeamScore();
				
				//randomly determine a home and away team
				if(random.nextBoolean()) {
					
					team1.setScore((int) Math.round(team1.getScore()*1.05));
					team1.setHome(true);
					team2.setHome(false);
					
				}else {
					
					team2.setScore((int) Math.round(team2.getScore()*1.05));
					team2.setHome(true);
					team1.setHome(false);
				}
				
				//determine the winner
				if(team1.getScore() > team2.getScore()) {
					
					team1.incrementWin();
					team2.incremnetLose();
				}
				else if(team1.getScore()<team2.getScore()){
					
					team1.incremnetLose();
					team2.incrementWin();
					
				}else {
					
					team1.incrementDraw();
					team2.incrementDraw();
				}
				
				
				//sort teams from least match player to the most match player
				Collections.sort(teams, Comparator.comparingInt(Team::getMatchPlayed));
				//update the frame 
				updateFrame();
				
				//if all the matches are played, log information, show a message and activate start button in Season object
				if(teams.get(0).getMatchPlayed()  == matchNumber) {
					
					
					
					game.logLeague(teams);
					JOptionPane.showMessageDialog(null, "Season is Completed!" ,"Simulation Finished", JOptionPane.INFORMATION_MESSAGE);
					game.startButton.setVisible(true);
				}
				
				//wait for 0.1 seconds before next cycle of loop
				try {
					
					Thread.sleep(100);
					
				}catch(InterruptedException e) {
					
					e.printStackTrace();
					
				}
			
			//if pause = true, wait 0.1 seconds before checking up again.
			}else {
				
				try {
					
					Thread.sleep(100);
					
				}catch(InterruptedException e) {
					
					e.printStackTrace();
					
				}	
			}
		}
	}

}

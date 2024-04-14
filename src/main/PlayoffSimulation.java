/**
 * 
 * This is the PlayoffSimulation class which implements the interface Runnable. In order to create a thread of this class and
 * manipulate its pause-resume condition, I implemented this interface.
 */

package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.SwingUtilities;

import swing.Season;
import team.Team;
import user.User;

public class PlayoffSimulation implements Runnable{
	
	private ArrayList<Team> teams;
	//ArrayList<Team> teamsLeft;
	private boolean pause = false;
	private Season game;
	private Team team1;
	private Team team2;
	private User user;
	

	public PlayoffSimulation(ArrayList<Team> teams, Season game) {
		
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
	

	public void pausePlayoff() {
		
		this.pause = true;
	}
	
	
	public void resumePlayoff() {
		
		this.pause = false;
	}
	
	//this is for updating the Season frame, Season object is one of the parameters of the constructor
	private void updateFrame() {
		
		 SwingUtilities.invokeLater(() -> {
			 
			 
			 game.updateList(team1, team2, "Playoff.txt");
			 
			 
			
			 
			 game.updateTable(new ArrayList<>(teams.subList(0, 8)));
			 
			 
		 });
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//delete old log file if exists
		File oldSeason = new File("Playoff.txt");
		
		if(oldSeason.exists()) {
			
			oldSeason.delete();
		}
		
		//sort the teams ArrayList according to their Win number in descending order.
		Collections.sort(teams, Comparator.comparingInt(Team::getWin).reversed());
		//the 8 teams with most win numbers
		ArrayList<Team> teamsLeft = new ArrayList<>(teams.subList(0, 8));
		//a temporary ArrayList
		ArrayList<Team> temp = new ArrayList<>();
		Random random = new Random();
		
		

		/**
		 * each time a game played, I remove the teams from teamsLeft, determine the winner and add the winner into temp,
		 * and inside the loop, if size of the teamsLeft is 0, I set teamsLeft as temp. that way, the number of elements 
		 * inside the teamsLeft until the end of the loop goes like
		 * 8
		 * 6
		 * 4
		 * 2
		 * 0 -> 4(since it reached 0 I set it as temp which contains winners) 
		 * 2
		 * 0 -> 2
		 * 0 -> 1
		 * 
		 */
		
		while (teamsLeft.size() > 1) {
			
			//if not paused
			if(!pause) {
				
				//randomly select two teams
				team1 = teamsLeft.get(random.nextInt(teamsLeft.size()));
				team2 = teamsLeft.get(random.nextInt(teamsLeft.size()));
				
				//if they are the same, change one of them
				while(team1.equals(team2)) {
					
					team2 = teamsLeft.get(random.nextInt(teamsLeft.size()));
					
				}
				
				//calculate their randomized score
				team1.calculateTeamScore();
				team2.calculateTeamScore();
				
				//remove teams from teamsLeft
				teamsLeft.remove(team1);
				teamsLeft.remove(team2);
				
				
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
					temp.add(team1);
				}
				else if(team1.getScore()<team2.getScore()){
					
					team1.incremnetLose();
					team2.incrementWin();
					temp.add(team2);
					
				//if draw, I decided to pick team1 as winner
				}else {
					
					team1.incrementDraw();
					team2.incrementDraw();
					temp.add(team1);
				}
				
				//updating frame accordingly
				updateFrame();
				
				//if teamsLeft's size 0 zero, set it as temp and clear temp
				if(teamsLeft.size() == 0) {
					
					teamsLeft.addAll(temp);
					temp.clear();
					
					/**
					 * after this operation, if teamsLeft's size 1 one, that means only 1 team left which is the winner
					 * therefore I updated list and overwrite this info into log file
					 */
					
					if(teamsLeft.size() == 1) {
						
						
						//sometimes it writes before the list object is updated, so I made it wait 0.5 seconds
						try {
							
							Thread.sleep(500);
							
						}catch(InterruptedException e) {
							
							e.printStackTrace();
							
						}
						
						game.listModel.addElement(teamsLeft.get(0).getTeamName() + " is the Champion!");
						int lastIndex = game.listModel.size() - 1;
						game.list.ensureIndexIsVisible(lastIndex);
						
						 try(BufferedWriter writer = new BufferedWriter(new FileWriter("Playoff.txt", true))){
								
								writer.write(teamsLeft.get(0).getTeamName() + " is the Champion!");
								writer.newLine();
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
				}
				
				//before next cycle, wait 0.1 seconds
				try {
					
					Thread.sleep(100);
					
				}catch(InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
				//if pause, wait 0.1 seconds before checking again
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

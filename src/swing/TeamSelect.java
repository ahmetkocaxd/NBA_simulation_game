/**
 * 
 * This is TeamSelect class which is a sublass of JFrame. It is for providing user a Frame to select a team, then continue with
 * player selection. The player selection system works as described in the project. User selects players for his team
 * and computer selects player for other teams. The detailed description can be found inside the class.
 * 
 */

package swing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import player.Player;
import player.PlayerSystem;
import team.Team;
import user.User;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ItemEvent;

public class TeamSelect extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<Player> availablePlayers;
	private int actionCounter = 0;
	
	

	public ArrayList<Team> getTeams() {
		return teams;
	}



	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}



	public ArrayList<Player> getAvailablePlayers() {
		return availablePlayers;
	}



	public void setAvailablePlayers(ArrayList<Player> availablePlayers) {
		this.availablePlayers = availablePlayers;
	}



	/**
	 * Create the frame.
	 */
	
	/**
	 * user is passed in order to get and set information of the user such as setting team.
	 * @param user
	 */
	
	public TeamSelect(User user) {
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(29, 66, 138));
		panel.setBounds(0, 0, 786, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel nbaLogoLabel = new JLabel("");
		nbaLogoLabel.setIcon(new ImageIcon("src/codeImage/nbalogo.png"));
		nbaLogoLabel.setBounds(300, 0, 226, 119);
		panel.add(nbaLogoLabel);
		
		JList list = new JList();
		
		/**
		 * JList is created for user to pick a team among them. Logo of each team can be found under codeImage.
		 */
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Boston Celtics", "Brooklyn Nets", "Chicago Bulls", "Utah Jazz", 
					"Los Angeles Lakers", "Orlando Magic", "Detroit Pistons", 
					"Minnesota Timberwolves", "Wizards", "Golden State Warriors", 
					"Indiana Pacers", "Miami Heat", "Portland Trail Blazers", 
					"Toronto Raptors", "Denver Nuggets", "Atlanta Hawks"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(72, 214, 321, 218);
		contentPane.add(list);
		
		JLabel lblChooseATeam = new JLabel("CHOOSE A TEAM");
		lblChooseATeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseATeam.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblChooseATeam.setBounds(72, 129, 655, 57);
		contentPane.add(lblChooseATeam);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(72, 214, 321, 218);
		contentPane.add(scrollPane);
		
		JLabel logoLabel = new JLabel("");
		ImageIcon logoIcon = new ImageIcon();
	
		logoLabel.setBounds(500, 220, 200, 200);
		contentPane.add(logoLabel);
		
		JButton selectButton = new JButton("Select");
		
		selectButton.setForeground(new Color(255, 255, 255));
		selectButton.setBackground(new Color(2, 85, 163));
		selectButton.setEnabled(false);
		selectButton.setBounds(615, 470, 85, 25);
		contentPane.add(selectButton);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.setBounds(72, 314, 321, 30);
		contentPane.add(comboBox);
		comboBox.addItem("");
		comboBox.addItem("Start Draft");
		comboBox.setVisible(false);
				
		
		/**
		 * by changing the selected element in the JList, the Logo is adjusted accordingly.
		 */
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if(!e.getValueIsAdjusting()) {
					
					String selected = (String) list.getSelectedValue();
					
					
					ImageIcon image = new ImageIcon("src/codeImage/" + selected + ".png");
					Image scaled = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					logoLabel.setIcon(new ImageIcon(scaled));
					
					selectButton.setEnabled(true);
					
					}
				
				
				
			}
		});
		
		/**
		 * when select button is clicked, team selection is over and selected team is assigned to the user.
		 * player selection is started
		 */
		
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//getting the selected team and setting it as user's team
				user.setTeam(new Team((String) list.getSelectedValue()));
				
				//gui operations
				lblChooseATeam.setText("CHOOSE PLAYERS");
				selectButton.setVisible(false);
				comboBox.setVisible(true);
				list.setVisible(false);
				scrollPane.setVisible(false);
				
				//Reading the csv file and removing duplicates. These methods can be found under the class PlayerSystem
				PlayerSystem playerSystem = new PlayerSystem();
				playerSystem.scanCsv("2022-2023_NBA_Player_Stats-Regular.csv");
				playerSystem.removeDuplicates();
				
				//initializing availablePlayers ArrayList (class variable) to operate according to them
				setAvailablePlayers(playerSystem.getPlayers());
				
				//these print lines are to check if the process is correct, not important for implementation and
				//there are a few more below
				System.out.println(availablePlayers.size());
				
				
				/**
				 * initializing team ArrayList (class variable) in order to operate according them.
				 * it is initialized by reading the list elements, which contains names of teams.
				 */
				for(int i = 0; i < list.getModel().getSize(); i++) {
					
					String teamName = (String) list.getModel().getElementAt(i);
					
					teams.add(new Team(teamName));
					
				}
				
				
				
				//shuffling teams ArrayList randomly
				Collections.shuffle(teams);
				
				/**
				 * this is where player selection starts, this is the first cycle and it not a complete cycle,
				 * while iterating over teams, if the team element is NOT user's team, then a random available player
				 * is selected for that team according to the methods pickRandomPlayer (class method). Once the player
				 * is picked by computer, player is removed from availablePlayer ArrayList and the player added to the
				 * team element's playerList. If it is user's team's turn, break. (nothing happened for user in this part)
				 */
				for(Team team: teams) {
					
					Player selectedPlayer;
					
					
					if(team.getTeamName().equals(user.getTeam().getTeamName())) {
						
						
						break;
					}

					
					else {
						
						selectedPlayer = pickRandomPlayer(team);
						availablePlayers.remove(selectedPlayer);
						team.addPlayer(selectedPlayer);
						
					}
					
					System.out.println(selectedPlayer.getPlayerName());

				}
				System.out.println(availablePlayers.size());
				
				/**
				 * after the for loop terminates, update the comboBox since availablePlayer ArrayList has changed.
				 * This comboBox is for user to pick a player from. It shows player's names and positions.
				 */
				comboBox.removeAllItems();
				comboBox.addItem("");
				for(Player player : availablePlayers) {
					
					
					String element = String.format("%-40s  %s", player.getPlayerName(), player.getPosition() );
					comboBox.addItem(element);
					
					
				}
					
			}
		});
		
		/**
		 * this is where the rest of the player selection procedure continues.
		 * each time the selection of comboBox elements change, this method is triggered.
		 */
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(availablePlayers.size());
				
				//each time a selection is made, increment actionCounter
				actionCounter++;
				
				
				//drafting system
		
				
				//I dont know why, but it appears my first selection triggers the 3rd action for this actionListener
				if( actionCounter >= 3) {
				
					//iterate over team
					for(Team team: teams) {
						
						/**
						 * this is normally the FIRST time that the action happens, but like I said, according to the
						 * system, this is my 3rd action.
						 * if action is 3 (first selection of the user) and the team element has 1 player, which means
						 * that team made a player selection above, continue for loop with next team element.
						 *
						 */
						if(actionCounter == 3){
							if(team.getPlayers().size()==1) {
								continue;
							}	
						}
						
						//initialize a player
						Player selectedPlayer = null;
						
						
						/**
						 * if team element is same with user's team, get selected item from comboBox and get the player
						 * name from it, find the player having the same name from available players and set selected
						 * player as this player. 
						 */
						if(team.getTeamName().equals(user.getTeam().getTeamName())) {

							String selectedPlayerName = (String) comboBox.getSelectedItem();
							String[] parts = selectedPlayerName.split("\\s{2,}");
							
							
							for(Player player : availablePlayers) {
								
								if(player.getPlayerName().equals(parts[0])) {
									
									selectedPlayer = player;
									user.getTeam().addPlayer(selectedPlayer);
									
								}
							}
						}
						/**
						 * if it is not user's team, pickRandomPlayer selects a random player for team.
						 */
						else {
							
							selectedPlayer = pickRandomPlayer(team);
							
						}
						/**
						 * add the selected player to the team element and remove that player from availablePlayers arraylist
						 */
						team.addPlayer(selectedPlayer);
						System.out.println(selectedPlayer.getPlayerName());
						availablePlayers.remove(selectedPlayer);
						
						
						
						
					}
					
					
					System.out.println(availablePlayers.size());
					
					/**
					 * after the procedure, update the comboBox as follows:
					 * clear comboBox.
					 * find existingPositions for the user's team. 
					 * find the players in availablePlayers who plays at a different position than existingPositions
					 * add them to the comboBox.
					 * this way, user can pick players with different positions each time
					 * also, each team picks 5 players in my implementation.
					 */
					comboBox.removeAllItems();
					comboBox.addItem("");
					
					ArrayList<String> existingPositions = new ArrayList<>();
					
					for(Player existingPlayer: user.getTeam().getPlayers()) {
						
						existingPositions.add(existingPlayer.getPosition());
					}
					
					for(Player player : availablePlayers) {

						if(!existingPositions.contains(player.getPosition())) {
							
							String element = String.format("%-40s  %s", player.getPlayerName(), player.getPosition() );
							comboBox.addItem(element);
						}
					}
					
					
					//finally, reverse the order of teams for the next action.
					Collections.reverse(teams);
					
				}
					
				/**
				 * If teams have 5 players (I just checked the first team) set user team as the user's team from teams
				 * logTeamInfo (class method), show a message indicating draft is complete and open a Season Frame
				 */
				if(teams.get(0).getPlayers().size() == 5) {
					
					for(Team team: teams) {
						
						if(team.getTeamName().equals(user.getTeam().getTeamName())) {
							user.setTeam(team);
						}
					}
					
					logTeamInfo(teams);
					JOptionPane.showMessageDialog(TeamSelect.this, "Draft is complete!");
					Season game = new Season(user, teams);
					game.setVisible(true);
					dispose();
				}
			}
			
		});

	}

	/**
	 * this method gets a Team object parameter, check the needed positions and picks a player from availablePlayers
	 * accordingly. returns that player
	 * @param team
	 * @return
	 */
	private Player pickRandomPlayer(Team team) {
		
		ArrayList<Player> players = team.getPlayers();
		ArrayList<String> neededPositions = new ArrayList<>(Arrays.asList("C","PF","PG","SF","SG") );
		boolean check = true;
		Random random = new Random();
		Player selectedPlayer = null;
		
		for(Player element: players) {
			
			if(element != null && neededPositions.contains(element.getPosition())) {
				
				neededPositions.remove(element.getPosition());
			}
			
		}
		
		while(check) {
			
			
			int randomIndex = random.nextInt(availablePlayers.size());
			
			if(neededPositions.isEmpty()) {
				
				break;
				
			}
			
			if(neededPositions.contains(availablePlayers.get(randomIndex).getPosition())){
				
				selectedPlayer = availablePlayers.get(randomIndex);
				
				check = false;
			}	
		}
		return selectedPlayer;
	
	}
	
	/**
	 * this method takes an ArrayList consisting of teams and writes each of the team's info inside a text named
	 * -teamName-.txt 
	 * @param teams
	 */
	private void logTeamInfo(ArrayList<Team> teams) {
		
		
		
		for(Team team : teams) {
			
			File file = new File("teams", team.getTeamName()+".txt");
			int score = 0;
		
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
					
					
					
					for(Player player : team.getPlayers()) {
						
						player.calculateScore();
						score += player.getScore();
						writer.write(player.getPlayerName() + "-" + player.getPosition() + "-" + 
						String.valueOf(player.getAst()) + "-" + String.valueOf(player.getBlk()) + "-" +
						String.valueOf(player.getPts()) + "-" + String.valueOf(player.getStl()) + "-" + 
						String.valueOf(player.getTrb()) + "-" + String.valueOf(player.getScore()));	
						writer.newLine();
						
					}
					
					team.setScore(score);
					
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} 	
	}
}

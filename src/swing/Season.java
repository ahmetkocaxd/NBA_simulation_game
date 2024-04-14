/**
 * 
 * this is the Season class which is a subclass of JFrame. This creates a JFrame and simulates the game with the help of
 * PlayoffSimulation and SeasonSimualation classes.
 */

package swing;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import team.Team;
import user.User;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.PlayoffSimulation;
import main.SeasonSimulation;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Season extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane ;
	public JList list;
	public DefaultListModel<String> listModel;
	private JToggleButton PauseButton;
	private SeasonSimulation simulation;
	private PlayoffSimulation playoffSimulation;
	private JScrollPane sp;
	public JButton startButton;


	/**
	 * Create the frame.
	 */
	
	/*
	 * gets user and ArrayList<Team> objects to manipulate them
	 */
	public Season(User user, ArrayList<Team> teams) {
		
		//creating SeasonSimulation and PlayoffSimulation objects to call later
		this.simulation = new SeasonSimulation(teams, this);
		this.playoffSimulation = new PlayoffSimulation(teams, this);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(29, 66, 138));
		panel.setBounds(0, 0, 1024, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel nbaLogoLabel = new JLabel("");
		nbaLogoLabel.setIcon(new ImageIcon("src/codeImage/nbalogo.png"));
		nbaLogoLabel.setBounds(432, 0, 226, 119);
		panel.add(nbaLogoLabel);
		
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setBounds(29, 148, 525, 280);
		listModel.addElement("");
		
		//initializing JTable, no match is played, every stat is 0 (win-lose-draw)
		String[][] teamsStatus = new String[16][4];
		
		for(int i = 0; i < teams.size(); i++) {
			
			teamsStatus[i][0] = teams.get(i).getTeamName();
			teamsStatus[i][1] = "0";
			teamsStatus[i][2] = "0";
			teamsStatus[i][3] = "0";
		}
		
		String[] header = {"Team Name", "Win", "Lose", "Draw"};
		
		table = new JTable(teamsStatus, header);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setRowSelectionAllowed(false);
		table.setBounds(648, 179, 300, 256);
		
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(690, 148, 300, 280);
		contentPane.add(scrollPane);
		
		sp = new JScrollPane(list);
		sp.setBounds(29, 148, 640, 280);
		
		contentPane.add(sp);
		
		/*
		 * view team button and action. when clicked, PlayoffSimulation and SeasonSimulation is paused and
		 * TeamInfo frame is created.
		 */
		JButton viewTeamButton = new JButton("View Team");
		viewTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				simulation.pauseSeason();
				playoffSimulation.pausePlayoff();
				TeamInfo teamInfoFrame = new TeamInfo(user, simulation, playoffSimulation);
				teamInfoFrame.setVisible(true);
				
			}
		});
		viewTeamButton.setForeground(Color.WHITE);
		viewTeamButton.setBackground(new Color(2, 85, 163));
		viewTeamButton.setBounds(845, 493, 100, 25);
		contentPane.add(viewTeamButton);
		
		//pauseButton and action
		PauseButton = new JToggleButton("Pause");
		
		/*
		 * each time pause toggle button is clicked, changes the status of PlayoffSimulation and SeasonSimulation
		 * (if paused or not) and changes the button text accordingly.
		 */
		PauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				simulation.changePause();
				playoffSimulation.changePause();
				
				if(PauseButton.getText().equals("Pause")) {
					
					PauseButton.setText("Resume");
					
				}else {
					
					PauseButton.setText("Pause");
					
				}
				
			}
		});
		PauseButton.setBounds(720, 493, 100, 25);
		contentPane.add(PauseButton);
		
		startButton = new JButton("Start Playoffs");
		/*
		 * 
		 * once the startButton is clicked, creates a thread of PlayoffSimulation to start playoffs.
		 * the start button appears after the SeasonSimulation terminates.
		 */
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listModel.addElement("************************************Playoffs************************************");
				
				startButton.setVisible(false);
				Thread playoffThread = new Thread(playoffSimulation);
				playoffThread.start();
				
				
				
			}
		});
		startButton.setForeground(Color.WHITE);
		startButton.setBackground(new Color(2, 85, 163));
		startButton.setBounds(461, 495, 113, 25);
		contentPane.add(startButton);
		startButton.setVisible(false);
		
		
		//starting Season Thread
		Thread simulationThread = new Thread(simulation);
		simulationThread.start();
		
		
	}
	
	//Updating JList each time a match played. I also log the match information since it is the same String element I want
	//I call this method inside the SeasonSimulation in each iteration. And also in PlayoffSimulation
	public void updateList(Team team1, Team team2, String path) {
		
		String element = "";
		
		if(team1.isHome()) {
			
			element += "Home: " + team1.getTeamName() + " | Away: " + team2.getTeamName();
			element += " Match: " + String.valueOf(team1.getScore()) + "-" + String.valueOf(team2.getScore());
			
		}else {
			
			element += "Home: " + team2.getTeamName() + " | Away: " + team1.getTeamName();
			element += " Match: " + String.valueOf(team2.getScore()) + "-" + String.valueOf(team1.getScore());
		}
		
		if(team1.getScore() > team2.getScore()) {
			
			element += " " + team1.getTeamName() + " Won.";
		}
		
		else if(team1.getScore() < team2.getScore()) {
			
			element += " " + team2.getTeamName() + " Won.";
			
		}else {
			
			element += " Draw.";
		}	
		listModel.addElement(element);
		
		int lastIndex = listModel.size() - 1;
		list.ensureIndexIsVisible(lastIndex);
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))){
			
			writer.write(element);
			writer.newLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//updating JTable each time a match is played. I call this method inside the SeasonSimulation in each iteration.
	public void updateTable(ArrayList<Team> teams) {
		
		String[] header = {"Team Name", "Win", "Lose", "Draw"};
		
		String[][] teamsStatus = new String[16][4];
		
		for(int i = 0; i < teams.size(); i++) {
			
			teamsStatus[i][0] = teams.get(i).getTeamName();
			teamsStatus[i][1] = String.valueOf(teams.get(i).getWin());
			teamsStatus[i][2] = String.valueOf(teams.get(i).getLose());
			teamsStatus[i][3] = String.valueOf(teams.get(i).getDraw());
		}
		
		table = new JTable(teamsStatus, header);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setRowSelectionAllowed(false);
		table.setBounds(648, 179, 300, 256);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(690, 148, 300, 280);
		contentPane.add(scrollPane);
		
	}
	
	
	//for logging the win-lose-draw stats of each team. I call this method inside the SeasonSimulation when
	//the simulation terminates.
	public void logLeague(ArrayList<Team> teams) {
		
		String[] header = {"Team Name", "Win", "Lose", "Draw"};
		
		String[][] teamsStatus = new String[16][4];
		
		for(int i = 0; i < teams.size(); i++) {
			
			teamsStatus[i][0] = teams.get(i).getTeamName();
			teamsStatus[i][1] = String.valueOf(teams.get(i).getWin());
			teamsStatus[i][2] = String.valueOf(teams.get(i).getLose());
			teamsStatus[i][3] = String.valueOf(teams.get(i).getDraw());
		}
		
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("League.txt"))){
			
			for(int i = 0; i < 4; i++) {
				
				writer.write(String.format("%-25s", header[i]));
			}
			
			writer.newLine();
			
			for(int i = 0; i < 16; i++) {
				
				for(int j = 0; j < 4; j++) {
					
					writer.write(String.format("%-25s", teamsStatus[i][j]));
					
				}
				writer.newLine();
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}

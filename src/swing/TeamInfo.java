/**
 * 
 * This is the TeamInfo class which is a subclass of JFrame. It is for providing a frame containing the team information of
 * the user, which is get as parameter. It shows the user's team's players in label's by using getter methods. It also
 * shows user's team's logo by finding the png file sharing the same name with user's team saved under the codeImage.
 * It also gets SeasonSimulation and PlayoffSimulation objects as parameters in order to pass these objects to TeamInfo, 
 * and control the state of these simulations. This is described under the backButton action.
 * 
 */

package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.PlayoffSimulation;
import main.SeasonSimulation;
import user.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeamInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public TeamInfo(User user, SeasonSimulation simulation, PlayoffSimulation playoffSim) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(29, 66, 138));
		panel.setBounds(0, 0, 786, 119);
		contentPane.add(panel);
		
		JLabel nbaLogoLabel = new JLabel("");
		nbaLogoLabel.setIcon(new ImageIcon("src/codeImage/nbalogo.png"));
		nbaLogoLabel.setBounds(300, 0, 226, 119);
		panel.add(nbaLogoLabel);
		
		JLabel lblTeamInfo = new JLabel(user.getTeam().getTeamName().toUpperCase());
		lblTeamInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblTeamInfo.setBounds(63, 129, 655, 57);
		contentPane.add(lblTeamInfo);
		
		/**
		 * iterating over user's team. Each team has 5 players in my implementation.
		 * I add user's team's player's name to the String Array in for loop
		 */
		
		String[] playerInfo = new String[5];
		
		for(int i = 0; i < user.getTeam().getPlayers().size(); i++) {
			
			String element =  user.getTeam().getPlayers().get(i).getPlayerName();
			playerInfo[i] = element;
		}
		
		
		JLabel playerLbl1 = new JLabel(playerInfo[0]);
		playerLbl1.addMouseListener(new MouseAdapter() {
			@Override
			
			/**
			 * If any playerLbl is clicked, opens up the playerInfo Frame
			 * @param e
			 */
			public void mouseClicked(MouseEvent e) {
				
				PlayerInfo playerInfoFrame = new PlayerInfo(user, playerLbl1.getText());
				playerInfoFrame.setVisible(true);
			}
		});
		playerLbl1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl1.setBounds(137, 209, 368, 40);
		contentPane.add(playerLbl1);
		
		JLabel playerLbl2 = new JLabel(playerInfo[1]);
		playerLbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PlayerInfo playerInfoFrame = new PlayerInfo(user, playerLbl2.getText());
				playerInfoFrame.setVisible(true);
				
			}
		});
		playerLbl2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl2.setBounds(137, 260, 368, 40);
		contentPane.add(playerLbl2);
		
		JLabel playerLbl3 = new JLabel(playerInfo[2]);
		playerLbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PlayerInfo playerInfoFrame = new PlayerInfo(user, playerLbl3.getText());
				playerInfoFrame.setVisible(true);
			}
		});
		playerLbl3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl3.setBounds(137, 310, 361, 40);
		contentPane.add(playerLbl3);
		
		JLabel playerLbl4 = new JLabel(playerInfo[3]);
		playerLbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PlayerInfo playerInfoFrame = new PlayerInfo(user, playerLbl4.getText());
				playerInfoFrame.setVisible(true);
			}
		});
		playerLbl4.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl4.setBounds(137, 360, 368, 40);
		contentPane.add(playerLbl4);
		
		JLabel playerLbl5 = new JLabel(playerInfo[4]);
		playerLbl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PlayerInfo playerInfoFrame = new PlayerInfo(user, playerLbl5.getText());
				playerInfoFrame.setVisible(true);
			}
		});
		playerLbl5.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl5.setBounds(137, 410, 355, 40);
		contentPane.add(playerLbl5);
		
		JLabel logoLabel = new JLabel("");
		
		ImageIcon image = new ImageIcon("src/codeImage/" + user.getTeam().getTeamName()+ ".png");
		Image scaled = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		logoLabel.setIcon(new ImageIcon(scaled));
		logoLabel.setBounds(500, 220, 200, 200);
		contentPane.add(logoLabel);
		
		JButton backButton = new JButton("Back");
		
		/**
		 * this is where I manipulate PlayoffSimulation and SeasonSimulation parameters.
		 * If back button is clicked, I make each simulation to resume, since I paused them once TeamInfo
		 * Frame is opened
		 * 
		 */
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				playoffSim.resumePlayoff();
				simulation.resumeSeason();
				dispose();
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(2, 85, 163));
		backButton.setBounds(63, 485, 85, 25);
		contentPane.add(backButton);
	}
}

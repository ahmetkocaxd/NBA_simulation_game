
/**
 * 
 * this is PlayerInfo class which is a subclass of JFrame. It is for providing a JFrame containing Player information inside
 * It gets a user object and a playerName String. Then, finds the Player object having the same name with parameter 
 * playerName from the user's team's players. Then, shows the necessary information of the player in label's with using
 * getter methods.
 * 
 */

package swing;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import player.Player;
import user.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PlayerInfo(User user, String playerName) {
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
		
		Player selectedPlayer = null;
		
		for(Player player : user.getTeam().getPlayers()){
			
			if(player.getPlayerName().equals(playerName)) {
				
				selectedPlayer = player;
			}
			
		}
		
		JLabel lblPlayerInfo = new JLabel(selectedPlayer.getPlayerName());
		lblPlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblPlayerInfo.setBounds(67, 129, 655, 57);
		contentPane.add(lblPlayerInfo);
		
		JLabel lblPosition = new JLabel("Position: " + selectedPlayer.getPosition());
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPosition.setBounds(333, 196, 121, 40);
		contentPane.add(lblPosition);
		
		JLabel lblPts = new JLabel("PTS: " + selectedPlayer.getPts());
		lblPts.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPts.setBounds(145, 245, 172, 40);
		contentPane.add(lblPts);
		
		JLabel lblTrb = new JLabel("TRB: " + selectedPlayer.getTrb());
		lblTrb.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblTrb.setBounds(145, 295, 172, 40);
		contentPane.add(lblTrb);
		
		JLabel lblAst = new JLabel("AST: " + selectedPlayer.getAst());
		lblAst.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblAst.setBounds(145, 345, 172, 40);
		contentPane.add(lblAst);
		
		JLabel lblBlk = new JLabel("BLK: " + selectedPlayer.getBlk());
		lblBlk.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblBlk.setBounds(145, 395, 172, 40);
		contentPane.add(lblBlk);
		
		JLabel playerLbl1_4_1 = new JLabel("STL: " + selectedPlayer.getStl());
		playerLbl1_4_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		playerLbl1_4_1.setBounds(145, 445, 172, 40);
		contentPane.add(playerLbl1_4_1);
		
		JLabel lblPtsWeight = new JLabel("PTS weight: " + selectedPlayer.getPtsW());
		lblPtsWeight.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPtsWeight.setBounds(474, 245, 172, 40);
		contentPane.add(lblPtsWeight);
		
		JLabel lblTrbWeight = new JLabel("TRB weight:" + selectedPlayer.getTrbW());
		lblTrbWeight.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblTrbWeight.setBounds(474, 295, 172, 40);
		contentPane.add(lblTrbWeight);
		
		JLabel lblAstWeight = new JLabel("AST weight: " + + selectedPlayer.getAstW());
		lblAstWeight.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblAstWeight.setBounds(474, 345, 172, 40);
		contentPane.add(lblAstWeight);
		
		JLabel lblBlkWeight = new JLabel("BLK weight:" + selectedPlayer.getBlkW());
		lblBlkWeight.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblBlkWeight.setBounds(474, 395, 172, 40);
		contentPane.add(lblBlkWeight);
		
		JLabel lblStlWeight = new JLabel("STL weight:" + selectedPlayer.getStlW());
		lblStlWeight.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblStlWeight.setBounds(474, 445, 172, 40);
		contentPane.add(lblStlWeight);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(2, 85, 163));
		backButton.setBounds(67, 495, 85, 25);
		contentPane.add(backButton);
	}

}

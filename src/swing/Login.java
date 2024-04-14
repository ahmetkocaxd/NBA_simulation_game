/**
 * 
 *This class is subclass of JFrame for logging in. It provides login and sign up option to the user.
 *If sign up button is clicked, a new register frame is opened. If login button is clicked, userInfo page
 *is opened and the text inside the username is passed to the userInfo class in order to get the infomation
 *of the user from user.txt for necessary fields in userInfo
 * 
 * 
 */



package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;

import exception.*;
import validator.UserLoginValidator;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nicknameText;
	private JPasswordField passwordText;
	
	public JTextField getNicknameText() {
		return nicknameText;
	}

	public void setNicknameText(JTextField nicknameText) {
		this.nicknameText = nicknameText;
	}
	
	

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton signupButton = new JButton("Sign up");
		signupButton.setForeground(new Color(2, 83, 165));
		signupButton.setBackground(new Color(255, 255, 255));
		signupButton.setFont(new Font("Arial", Font.BOLD, 12));
		
		/**
		 * signup button that actives register frame
		 */
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register registerFrame = new Register();
				registerFrame.setVisible(true);
				dispose();
				}
		});
		signupButton.setBounds(508, 351, 85, 31);
		contentPane.add(signupButton);
		
		JButton loginButton = new JButton("Log in");
		
		/**
		 * login button that validates if the user with the username exists; if exists, validates if the password is correct
		 */
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					char[] password = passwordText.getPassword();
					String passwordString = new String(password);
					UserLoginValidator.validateUserLogin(nicknameText.getText(), passwordString);
					
					UserInfo userInfoFrame = new UserInfo(nicknameText.getText());
					userInfoFrame.setVisible(true);
					dispose();
				
				}catch(InvalidNicknameException e1) {
					
					JOptionPane.showMessageDialog(null, "Username Not Found, Please First Sign Up.", "Error", JOptionPane.ERROR_MESSAGE);
					nicknameText.setText("");
					passwordText.setText("");
				}catch(InvalidPasswordException e1) {
					
					JOptionPane.showMessageDialog(null, "Invalid Password, Please Try Again.", "Error", JOptionPane.ERROR_MESSAGE);
					passwordText.setText("");
				}
				
				
			}
		});
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(2, 83, 165));
		
		loginButton.setFont(new Font("Arial", Font.BOLD, 12));
		loginButton.setBounds(508, 310, 85, 31);
		contentPane.add(loginButton);
		
		nicknameText = new JTextField();
		nicknameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nicknameText.setBounds(417, 217, 176, 26);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(417, 257, 176, 26);
		contentPane.add(passwordText);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(2, 83, 165));
		panel.setBorder(null);
		panel.setBounds(-14, 0, 320, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel iconLabel = new JLabel("");
		ImageIcon img = new ImageIcon("src/codeImage/nba.jpg");
		iconLabel.setIcon(img);
		iconLabel.setBounds(-33, 27, 548, 390);
		panel.add(iconLabel);
		
		JLabel nicknameLabel = new JLabel("Username");
		nicknameLabel.setBounds(334, 224, 84, 13);
		contentPane.add(nicknameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(334, 267, 63, 13);
		contentPane.add(passwordLabel);
		
		JLabel lblNewLabel = new JLabel("Log in");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(396, 151, 145, 57);
		contentPane.add(lblNewLabel);
		
		
	}
}

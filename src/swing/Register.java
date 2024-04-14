
/**
 * This is the register class which is a subclass of JFrame. It validates every textField if the info is valid.
 * If every information is valid and sign up button is clicked, information is overwritten into a text named users.txt
 * Also, it offers user to set a profile picture. There is a default picture and user can select a png file to change it.
 * If signup button clicked, it saves the profile picture named as -username-.png.
 */

package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import validator.AgeValidator;
import validator.EmailValidator;
import validator.NameValidator;
import validator.NicknameValidator;
import validator.PasswordValidator;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.event.ActionEvent;

import exception.*;
import user.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JSpinner;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField emailText;
	private JTextField nicknameText;
	private JPasswordField passwordText;
	private User user = new User();
	
	
	

	/**
	 * Create the frame.
	 */
	public Register() {
		
		
		setResizable(false);
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameText = new JTextField();
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameText.setBounds(357, 92, 96, 26);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		surnameText = new JTextField();
		surnameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		surnameText.setBounds(357, 151, 96, 26);
		contentPane.add(surnameText);
		surnameText.setColumns(10);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		JSpinner ageText = new JSpinner(spinnerModel);
		ageText.setBounds(545, 179, 34, 26);
		contentPane.add(ageText);
		
		emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailText.setBounds(357, 215, 222, 26);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		nicknameText = new JTextField();
		nicknameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nicknameText.setBounds(357, 274, 222, 26);
		contentPane.add(nicknameText);
		nicknameText.setColumns(10);
		
		JLabel ageLabel = new JLabel("Age");
		ageLabel.setBounds(501, 185, 34, 13);
		contentPane.add(ageLabel);
		
		JLabel emailLabel = new JLabel("E-mail");
		emailLabel.setBounds(357, 187, 45, 13);
		contentPane.add(emailLabel);
		
		JLabel nicknameLabel = new JLabel("Username");
		nicknameLabel.setBounds(357, 251, 63, 13);
		contentPane.add(nicknameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(357, 310, 63, 13);
		contentPane.add(passwordLabel);
		
		
		
		ImageIcon pfpIcon = new ImageIcon("src/pfpImage/userpfp.png");
		Image scaled = pfpIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel pfp = new JLabel(new ImageIcon(scaled));
		pfp.setBounds(494, 51, 100, 100);
		contentPane.add(pfp);
		
		
		JButton signupButton = new JButton("Sign up");
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setBackground(new Color(2, 83, 165));
		
		/**
		 * saves the info of the user IF every info is valid. Also saves the profile picture by calling a
		 * method from user class.
		 * 
		 */
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					//getting password as string
					char[] password = passwordText.getPassword();
					String passwordString = new String(password);
					
					//validations
					NameValidator.validateName(nameText.getText());
					NameValidator.validateSurname(surnameText.getText());
					AgeValidator.validateAge(String.valueOf((int) ageText.getValue()));
					EmailValidator.validateEmail(emailText.getText());
					NicknameValidator.validateNickname(nicknameText.getText());
					PasswordValidator.validatePassword(passwordString);
					
					user.setAge(String.valueOf((int) ageText.getValue()));
					user.setEmail(emailText.getText());
					user.setName(nameText.getText());
					user.setNickname(nicknameText.getText());
					user.setPassword(passwordString);
					user.setSurname(surnameText.getText());
					
					
					try(BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))){
						
						NicknameValidator.validateNickNameExistance(nicknameText.getText());
						
						writer.write(user.getNickname()+" " + user.getPassword() + " " + user.getName() 
						+ " " + user.getSurname() + " " + user.getEmail() + " " + user.getAge());
						writer.newLine();
						
						//saving the pfp
						
						user.savePicture(nicknameText.getText());
						
						
						JOptionPane.showMessageDialog(Register.this, "Sign up successful!");
						Login loginFrame = new Login();
						loginFrame.setVisible(true);
						dispose();
						
					} catch (InvalidNicknameException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Username already taken, choose another one.", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}catch(InvalidNameException e1) {
					
					JOptionPane.showMessageDialog(null, "Name should be at least 3 characters and only contain letters ", "Error", JOptionPane.ERROR_MESSAGE);
					nameText.setText("");
				}catch(InvalidSurnameException e1) {
					
					JOptionPane.showMessageDialog(null, "Surname should be at least 3 characters and only contain letters ", "Error", JOptionPane.ERROR_MESSAGE);
					surnameText.setText("");
				}catch(InvalidAgeException | NumberFormatException e1) {
					
					JOptionPane.showMessageDialog(null, "Age must be at least 12", "Error", JOptionPane.ERROR_MESSAGE);
					
				}catch(InvalidEmailException e1) {
					
					JOptionPane.showMessageDialog(null, "The email address should be in the correct format. (e.g., name@domain.com)", "Error", JOptionPane.ERROR_MESSAGE);
					emailText.setText("");
				}catch(InvalidNicknameException e1) {
					
					JOptionPane.showMessageDialog(null, "Username can only include letter and number characters.", "Error", JOptionPane.ERROR_MESSAGE);
					nicknameText.setText("");
				}catch(InvalidPasswordException e1) {
					
					JOptionPane.showMessageDialog(null, "Password should be at least 8 characters, including letters, numbers, and special characters.", "Error", JOptionPane.ERROR_MESSAGE);
					passwordText.setText("");
				}
				
			}
		});
		signupButton.setBounds(494, 381, 85, 31);
		contentPane.add(signupButton);
		
		JButton backButton = new JButton("Back");
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(2, 83, 165));
		
		/**
		 * If back button is clicked, login frame gets opened.
		 */
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login loginFrame = new Login();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(357, 381, 85, 31);
		contentPane.add(backButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(2, 83, 165));
		panel.setBounds(-13, -14, 320, 507);
		contentPane.add(panel);
		
		JLabel iconLabel = new JLabel("");
		ImageIcon img = new ImageIcon("src/codeImage/nba.jpg");
		panel.setLayout(null);
		iconLabel.setIcon(img);
		iconLabel.setBounds(-33, 27, 548, 390);
		panel.add(iconLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(357, 69, 45, 13);
		contentPane.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setBounds(357, 128, 54, 13);
		contentPane.add(surnameLabel);
		
		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(419, 10, 118, 36);
		contentPane.add(lblNewLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(357, 333, 222, 26);
		contentPane.add(passwordText);
		
		
		
		JButton changePfp = new JButton("Change");
		
		/**
		 * Allows user to set a profile picture as png file. The method is defined in user class
		 */
		changePfp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				user.setProfilePicture(pfp);
			}
		});
		changePfp.setBackground(SystemColor.control);
		changePfp.setForeground(new Color(2, 83, 165));
		changePfp.setFont(new Font("Tahoma", Font.BOLD, 8));
		changePfp.setBounds(513, 156, 66, 18);
		contentPane.add(changePfp);
		
		
		
	}
	

	
	
	
}

/**
 * 
 * This is the userInfo class which is a subclass of the JFrame. It shows the user info by getting the username
 * in constructor and finds this username inside the file user.txt to get the other information by reading the file.
 * It allows user to change every info except for name, surname and username. It also checks if the changed information is
 * valid. If not, it requests a valid input. If every info is valid when save is clicked, then the information of the
 * user is saved by creating a new user.txt with writing every other user and then overwriting the updated user to the new
 * user.txt It also allows user to change and update the profile picture. The method is the same with register class.
 * 
 */

package swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.InvalidAgeException;
import exception.InvalidEmailException;
import exception.InvalidNicknameException;
import exception.InvalidPasswordException;
import user.User;
import validator.AgeValidator;
import validator.EmailValidator;
import validator.NicknameValidator;
import validator.PasswordValidator;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
//import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UserInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user = new User();


	/**
	 * Create the frame.
	 */
	public UserInfo(String nickName) {
		
		/**
		 * Finds user in text file and creates the user object. the methos is inside the user class
		 */
		User realUser = user.findUser(nickName);
	
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
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
		
		/**
		 * every label shows the information of the user with using getters methods
		 */
		
		JLabel welcomeLabel = new JLabel("WELCOME " + realUser.getName().toUpperCase()+ " " +realUser.getSurname().toUpperCase());
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		welcomeLabel.setBounds(79, 129, 655, 57);
		contentPane.add(welcomeLabel);
		
		JLabel usernameLabel = new JLabel(realUser.getNickname());
		usernameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		usernameLabel.setBounds(200, 233, 341, 25);
		contentPane.add(usernameLabel);
		
		JLabel nameLabel = new JLabel(realUser.getName());
		nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		nameLabel.setBounds(200, 268, 341, 25);
		contentPane.add(nameLabel);
		
		JLabel surnameLabel = new JLabel(realUser.getSurname());
		surnameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		surnameLabel.setBounds(200, 303, 341, 25);
		contentPane.add(surnameLabel);
		
		JLabel ageLabel = new JLabel(realUser.getAge());
		ageLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		ageLabel.setBounds(200, 338, 341, 25);
		contentPane.add(ageLabel);
		
		JLabel emailLabel = new JLabel(realUser.getEmail());
		emailLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		emailLabel.setBounds(200, 373, 341, 25);
		contentPane.add(emailLabel);
		
		JLabel passwordLabel = new JLabel(realUser.getPassword());
		passwordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		passwordLabel.setBounds(200, 408, 341, 25);
		contentPane.add(passwordLabel);
		
		
		JLabel pfpLabel = new JLabel("");
		ImageIcon pfpIcon = new ImageIcon("src/pfpImage/" + realUser.getNickname() + ".png");
		Image scaled = pfpIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		pfpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pfpLabel.setIcon(new ImageIcon(scaled));
		pfpLabel.setBounds(634, 228, 100, 100);
		contentPane.add(pfpLabel);
		
		JButton changePfpBttn = new JButton("Change");
		
		/**
		 * First set profile picture
		 * Then delete the existing profile picture of the user with name -username-.txt
		 * Finally, save the profile picture of the user with name -username-.txt
		 */
		changePfpBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				realUser.setProfilePicture(pfpLabel);
				File oldPfp = new File("src/pfpImage/" + realUser.getNickname() + ".png");
				oldPfp.delete();
				realUser.savePicture(usernameLabel.getText());
				
			}
		});
		changePfpBttn.setForeground(new Color(29, 66, 138));
		changePfpBttn.setBackground(new Color(255, 255, 255));
		changePfpBttn.setBounds(634, 340, 100, 25);
		contentPane.add(changePfpBttn);
		
		JButton changeInfoBttn = new JButton("Change Info");
		changeInfoBttn.setForeground(new Color(29, 66, 138));
		changeInfoBttn.setBackground(new Color(255, 255, 255));
		
		/*
		 * changeInfo button removes labels and creates textFields to change the info
		 * 
		 */
		changeInfoBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ageLabel.setVisible(false);
				emailLabel.setVisible(false);
				passwordLabel.setVisible(false);
				changeInfoBttn.setVisible(false);
				
				
				
				SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
				JSpinner ageSpinner = new JSpinner(spinnerModel);
				ageSpinner.setBounds(200, 338, 34, 25);
				ageSpinner.setValue(Integer.parseInt(ageLabel.getText()));
				contentPane.add(ageSpinner);
				
				JTextField emailText = new JTextField();
				emailText.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
				emailText.setBounds(200, 373, 200, 25);
				emailText.setText(emailLabel.getText());
				contentPane.add(emailText);
				emailText.setColumns(10);
				
				JTextField passwordText = new JTextField();
				passwordText.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
				passwordText.setBounds(200, 408, 200, 25);
				passwordText.setText(passwordLabel.getText());
				contentPane.add(passwordText);
				passwordText.setColumns(10);
				
				
				JButton SaveButton = new JButton("Save Changes");
				SaveButton.setForeground(new Color(29, 66, 138));
				SaveButton.setBackground(new Color(255, 255, 255));
				SaveButton.setBounds(270, 443, 120, 25);
				contentPane.add(SaveButton);
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(29, 66, 138));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBounds(82, 443, 120, 25);
				contentPane.add(cancelButton);
				
				/**
				 * save button validates the info and saves IF every info is valid
				 */
				SaveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						
						
						
						emailLabel.setText(emailText.getText());
						ageLabel.setText(String.valueOf((int) ageSpinner.getValue()));
						passwordLabel.setText(passwordText.getText());
						
						try {
							AgeValidator.validateAge(ageLabel.getText());
							EmailValidator.validateEmail(emailLabel.getText());
							PasswordValidator.validatePassword(passwordLabel.getText());
						
							updateUser(usernameLabel.getText(), passwordLabel.getText(), nameLabel.getText(), surnameLabel.getText(),
									emailLabel.getText(), ageLabel.getText());
							
							SaveButton.setVisible(false);
							cancelButton.setVisible(false);
							
							emailText.setVisible(false);
							passwordText.setVisible(false);
							ageSpinner.setVisible(false);
							
							emailLabel.setVisible(true);
							passwordLabel.setVisible(true);
							ageLabel.setVisible(true);
							changeInfoBttn.setVisible(true);
							
						}catch(InvalidAgeException | NumberFormatException e2) {
							
							JOptionPane.showMessageDialog(null, "Age must be at least 12", "Error", JOptionPane.ERROR_MESSAGE);
							
						}catch(InvalidEmailException e2) {
							
							JOptionPane.showMessageDialog(null, "The email address should be in the correct format. (e.g., name@domain.com)", "Error", JOptionPane.ERROR_MESSAGE);
							
						}catch(InvalidPasswordException e2) {
							
							JOptionPane.showMessageDialog(null, "Password should be at least 8 characters, including letters, numbers, and special characters.", "Error", JOptionPane.ERROR_MESSAGE);
							
						}
						
						
						
						
					}
				});
				
				/**
				 * cancels the operation and removes textFields, add labels again.
				 */
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						cancelButton.setVisible(false);
						SaveButton.setVisible(false);
						
						emailText.setVisible(false);
						passwordText.setVisible(false);
						ageSpinner.setVisible(false);
						
						changeInfoBttn.setVisible(true);
						ageLabel.setVisible(true);
						emailLabel.setVisible(true);
						passwordLabel.setVisible(true);
						changeInfoBttn.setVisible(true);
					}
				
			});
				
				
			}
		});
		changeInfoBttn.setBounds(82, 443, 120, 25);
		contentPane.add(changeInfoBttn);
		
		JButton startButton = new JButton("Start");
		
		/**
		 * Start button opens the teamSelect frame 
		 */
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TeamSelect teamSelect = new TeamSelect(realUser);
				teamSelect.setVisible(true);
				dispose();
				
			}
		});
		startButton.setBackground(new Color(29, 66, 138));
		startButton.setForeground(new Color(255, 255, 255));
		startButton.setBounds(360, 500, 85, 30);
		contentPane.add(startButton);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblUsername.setBounds(79, 233, 100, 25);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblName.setBounds(79, 268, 100, 25);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblSurname.setBounds(79, 303, 100, 25);
		contentPane.add(lblSurname);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblAge.setBounds(79, 338, 100, 25);
		contentPane.add(lblAge);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblEmail.setBounds(79, 373, 100, 25);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPassword.setBounds(79, 408, 100, 25);
		contentPane.add(lblPassword);
	}
	
	
	/**
	 * this method is for updating user.txt file. It creates a new users.txt with other users and adds the updated user
	 * to the file
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param email
	 * @param age
	 */
	
	public void updateUser(String nickname, String password, String name, String surname, String email, String age) {
		
		
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
			
			
			AgeValidator.validateAge(age);
			EmailValidator.validateEmail(email);
			PasswordValidator.validatePassword(password);
			
			StringBuilder output = new StringBuilder();
			
			
			String line;
			while((line = br.readLine()) != null) {
				
				String[] elements = line.split("\\s+");
				if (elements.length > 0 && !(elements[0].equals(nickname))) {
					
					output.append(line + "\n");

				}
			}
			
			String newUser = nickname + " " + password + " " + name + " " + surname + " " + email + " " + age;
			output.append(newUser + "\n");
			
			try(BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))){
				
				writer.write(output.toString());
				
				
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
}

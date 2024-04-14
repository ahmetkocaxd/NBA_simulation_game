/**
 * 
 * User class which contains necessary variables of users. It also have methods described below
 */

package user;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import team.Team;

public class User {
	
	private String nickname;
	private String password;
	private String name;
	private String surname;
	private String age;
	private String email;
	private String pfpPath = "src/pfpImage/userpfp.png";
	private Team team;
	
	
	public User() {}
	
	
	
	public User(String nickname, String password, String name, String surname, String age, String email) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
	}
	
	
	

	
	
	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}



	public String getPfpPath() {
		return pfpPath;
	}



	public void setPfpPath(String pfpPath) {
		this.pfpPath = pfpPath;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * To the the profile picture. gets a parameters as JLabel and sets the ImageIcon as the selected png from
	 * files.
	 * @param pfp
	 */
	public void setProfilePicture(JLabel pfp) {
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
		
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			
			java.io.File selectedFile = fileChooser.getSelectedFile();
			
			
			String sourcePath= selectedFile.getAbsolutePath();
			
			this.pfpPath = sourcePath;
			
			//scaling the uploaded image
			ImageIcon pfpIcon = new ImageIcon(sourcePath);
			Image pfpImage = pfpIcon.getImage();
			Image scaled = pfpImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon scaledPfpIcon = new ImageIcon(scaled);
			pfp.setIcon(scaledPfpIcon);
		
		}
	}
	
	/**
	 * to save the set profile picture. It gets a parameter as nickname in order to save the picture as -nickname-.txt
	 * @param nickname
	 */
	public void savePicture(String nickname) {
		
		
		try {
			
			Path source = Path.of(this.pfpPath);
			Path destination = Path.of("src/pfpImage/" + nickname +".png");
			
			Files.copy(source, destination);
			
		}catch (Exception e2) {
			
			e2.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * users.txt file contains every user's information and first word in each line is nickname of users.
	 * So in order to find the user with nickname, it checks every first element of each line in users.txt.
	 * If the nickname matches with an element, a user object is constructed with necessary parameters obtained
	 * from other elements of the line. the user object is returned 
	 * 
	 * @param nickname
	 * @return
	 */
	public User findUser(String nickname) {
		
		User user = new User();
		
		try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
			
			String line;
			
			while((line = br.readLine()) != null) {
				
				String[] elements = line.split("\\s+");
				
				if (elements.length > 0 && elements[0].toLowerCase().equals(nickname.toLowerCase())) {
					
					user.setNickname(elements[0]);
					user.setPassword(elements[1]);
					user.setName(elements[2]);
					user.setSurname(elements[3]);
					user.setEmail(elements[4]);
					user.setAge(elements[5]);
					user.setPfpPath("src/pfpImage/" + user.getNickname() +".png");
					}
				}
				
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		return user;
	}
	

}

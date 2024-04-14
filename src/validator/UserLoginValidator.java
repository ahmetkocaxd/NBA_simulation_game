/**
 * This is for validating the login information.
 * 
 * It takes two parameters as nickname and password and reads the file users.txt. If users.txt does not include a nickname
 * that is same with the parameter nickname, then throws InvalidNicknameException. If nickname exists, then check its password
 * in the users.txt file. If this password and the parameter password does not macth, it throws InvalidPasswordException
 * to be handled afterwards.
 * 
 */

package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.*;

public class UserLoginValidator {
	
	public static void validateUserLogin(String nickname, String password) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
			
			boolean check = true;
			String line;
			while((line = br.readLine()) != null) {
				
				String[] elements = line.split("\\s+");
				if (elements.length > 0 && elements[0].toLowerCase().equals(nickname.toLowerCase())) {
					
					check = false;
					if(!elements[1].equals(password)) {
					
						throw new InvalidPasswordException("Invalid Password, Please Try Again.");
					}
				}
			}
			
			if (check) {
				throw new InvalidNicknameException("Username Not Found, Please First Sign Up.");
			}
			
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

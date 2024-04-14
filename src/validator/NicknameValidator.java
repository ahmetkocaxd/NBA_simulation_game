/**
 * this is the validator for nickname. It has to methods. validateNickname(String nickname) validates the format of the
 * nickname and alidateNickNameExistance(String nickname) checks if the nickname is exited in users.txt or not.
 * 
 * The format of the nickname is at least one letter character .
 * 
 * To check the existance of the nickname, method reads the file users.txt and gets the first element (when seperated from
 * whitespaces) of each line, where the nicknames are stored. If there is any match between parameter and this elements,
 * an exception is throwed to be handled afterwards.
 */

package validator;

import java.io.*;

import java.io.FileReader;
import java.io.IOException;

import exception.*;

public class NicknameValidator {
	
	public static void validateNickname(String nickname) {
		
		if(!nickname.matches("[a-zA-Z0-9]+")) {
			
			throw new InvalidNicknameException("Username can only include letter and number characters.");
			
		}
	}
	
	public static void validateNickNameExistance(String nickname) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
			
			String line;
			while((line = br.readLine()) != null) {
				
				String[] elements = line.split("\\s+");
				if (elements.length > 0 && elements[0].toLowerCase().equals(nickname.toLowerCase())) {
					
					throw new InvalidNicknameException("Username already taken, choose another one.");
				}
			}
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}

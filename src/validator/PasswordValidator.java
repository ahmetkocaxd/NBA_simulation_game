
/**
 * this is for validating password format. It checks if the password includes at least 1 letter, digit and special character
 * and is at least 8 character long. if not, throws an exception to be handled afterwards.
 */

package validator;

import java.util.regex.Pattern;

import exception.*;

public class PasswordValidator {
	
	public static void validatePassword(String password) {
		
		String check = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		
		
		if(!Pattern.compile(check).matcher(password).matches()) {
			
			throw new InvalidPasswordException("Password should be at least eight characters, including letters, numbers, and special characters.");
		}
	}

}

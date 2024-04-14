
/**
 * this is a validator for email format. It checks if the format is ***@***.com with regex If not, throws a pre-defined 
 * exception InvalidEmailException to be handled afterwards.
 * 
 */

package validator;

import java.util.regex.Pattern;

import exception.*;

public class EmailValidator {
	
	public static void validateEmail(String email) {
		
		String check = "^[a-z0-9._-]+@[a-z0-9.-]+\\.com$";
		
		if(!Pattern.compile(check).matcher(email).matches()) {
			
			throw new InvalidEmailException("The email address should be in the correct format. (e.g., name@domain.com");
		}
	}

}

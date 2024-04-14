
/** 
 * this is the validator to check if the parameter name and surname are valid. It only allows letters and at least
 * 3 characters. If the format is incorrect, then throws pre-defined exceptions InvalidNameException and InvalidSurnameException
 * to be handled afterwards
 */

package validator;

import exception.*;

public class NameValidator {
	
	public static void validateName(String name) {
		
		if(!name.matches("[a-zA-Z]{3,}")) {
			
			throw new InvalidNameException("Name should be at least 3 charachters and only contain letters ");
		}
	}
	
	public static void validateSurname(String surname) {
		
		if(!surname.matches("[a-zA-Z]{3,}")) {
			
			throw new InvalidSurnameException("Surname should be at least 3 charachters and only contain letters ");
		}
	}

}

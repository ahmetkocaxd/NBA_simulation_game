/**
 * this is ageValidator if the parameter age is equal or greater than 12. If not, throws the pre-defined exception
 * InvalidAgeException to be handled afterwards.
 */

package validator;

import exception.*;

public class AgeValidator {
	
	public static void validateAge(String age) {
		
		if(Integer.parseInt(age) < 12) {
			
			throw new InvalidAgeException("Age must be at least 12");
		}
	}

}

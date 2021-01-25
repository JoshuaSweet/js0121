package sweet.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sweet.demo.validator.annotation.RentalDayCount;

/**
 * Determines if rental day count is between 1 and 364.
 * @author Sweet
 *
 */
public class RentalDayCountValidator implements ConstraintValidator<RentalDayCount, Integer> {

	private static final Logger log = LogManager.getLogger( RentalDayCountValidator.class );
	
	/**
	 * Validation failure message.  This value is acquired from IntegerRange 
	 * constraint annotation which requires it when the annotation is applied 
	 * to a property.
	 */
	String message;
	
	/**
	 * Initialize message to prevent null reference later.
	 */
	@Override
    public void initialize(RentalDayCount constraintAnnotation) {
		
		String annotationMessage = constraintAnnotation.message();
    	this.message = annotationMessage != null ? annotationMessage : "";
    	
    }

	/**
	 * Validation logic.
	 */
    @Override
    public boolean isValid( Integer value, ConstraintValidatorContext constraintContext ) {
        
    	if ( value == null ) {
    		
    		/**
    		 * We wouldn't do null validation here since it is already handled 
    		 * with the @NotNull validator. 
    		 */
    		
    		return true;
    		
    	}

    	boolean valid = false;
    	int val = value.intValue();
    	
    	/**
    	 * DEMO NOTE:
    	 * The spec didn't put a cap on rental days, but I can't think of a scenario in which 
    	 * a business would want indefinite rentals and this is supposed to be robust - putting 
    	 * a cap on rentals periods could probably prevent checkout errors.
    	 */
    	
    	if ( val >= 1 && val <= 364 ) {
    		
    		valid = true;
    		
    	}
    	
    	try {
    		
    		this.logException( val, valid );
    		
    	} catch ( IllegalArgumentException e ) {
    		
    		/**
    		 * Spec Requirement: Checkout should throw an exception with an instructive, 
    		 * user-friendly message if Rental day count is not 1 or greater.
    		 * 
    		 * Since this validation is handled through the framework, we will 
    		 * just catch and log this as info level since not passing validation 
    		 * is not critical here and expected in some cases.. not that exceptional.
    		 * If we were checking values later in a business process, we may want to
    		 * let an exception bubble up instead and fail fast if necessary.
    		 */
    		log.info( e.getMessage() ); 
    		
    	}
    	
    	
    	return valid;
    	
    }
    
    /**
     * Ensure user friendly exception is thrown if validation fails.
     * @param value
     * @param valid
     * @throws IllegalArgumentException
     */
    private void logException( int value, boolean valid ) 
    	throws IllegalArgumentException {
    	
    	if ( !valid ) {
    		
    		String exceptionMessage = this.message + " Value supplied was " + value + " which is not between 1 and 364.";
    		throw new IllegalArgumentException( exceptionMessage );
    		
    	}
    	
    }
    
}
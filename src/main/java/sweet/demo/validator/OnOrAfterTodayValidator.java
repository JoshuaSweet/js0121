package sweet.demo.validator;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sweet.demo.validator.annotation.OnOrAfterToday;;

/**
 * DEMO NOTE:
 * The annotation for this validator is commented out where it would normally apply
 * because the spec indicated historical dates needed to be tested.  This is functional
 * through.
 */

/**
 * Determines if LocalDate provided is on or after today.
 * @author Sweet
 *
 */
public class OnOrAfterTodayValidator implements ConstraintValidator<OnOrAfterToday, LocalDate> {

	private static final Logger log = LogManager.getLogger( OnOrAfterTodayValidator.class );
	
	/**
	 * Validation failure message.  This value is acquired from IntegerRange 
	 * constraint annotation which requires it when the annotation is applied 
	 * to a property.
	 */
	String message;
	
	/**
	 * Ensure message is initialized to prevent null references.
	 */
	@Override
    public void initialize(OnOrAfterToday constraintAnnotation) {
		
		String annotationMessage = constraintAnnotation.message();
    	this.message = annotationMessage != null ? annotationMessage : "";
    	
    }

	/**
	 * Validation logic.
	 */
    @Override
    public boolean isValid( LocalDate value, ConstraintValidatorContext constraintContext ) {
        
    	if ( value == null ) {
    		
    		/**
    		 * We wouldn't do null validation here since it is already handled 
    		 * with the @NotNull validator. 
    		 */
    		
    		return true;
    		
    	}

    	LocalDate today = LocalDate.now();
    	boolean valid = value.isEqual( today ) || value.isAfter( today );
    	
    	try {
    		
    		this.logException( value, valid, today );
    		
    	} catch ( IllegalArgumentException e ) {
    		    		
    		log.info( e.getMessage() ); 
    		
    	}
    	
    	return valid;
    	
    }
    
    /**
     * Ensure user friendly exception is thrown if date not in acceptable range to keep consistent with other validators.
     * @param value
     * @param valid
     * @param today
     * @throws IllegalArgumentException
     */
    private void logException( LocalDate value, boolean valid, LocalDate today ) 
    	throws IllegalArgumentException {
    	
    	if ( !valid ) {
    		
    		String exceptionMessage = this.message + " Value supplied was " + value.format( ISO_LOCAL_DATE ) + " which is not on or after today " + today.format( ISO_LOCAL_DATE ) + " .";
    		throw new IllegalArgumentException( exceptionMessage );
    		
    	}
    	
    }
    
}
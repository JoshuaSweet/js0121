package sweet.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sweet.demo.controller.MainController;
import sweet.demo.validator.annotation.IntegerRange;

/**
 * Validation handler for annotation {@link IntegerRange}.
 * @author Sweet
 *
 */
public class IntegerRangeValidator implements ConstraintValidator<IntegerRange, Integer> {
	
	private static final Logger log = LogManager.getLogger( IntegerRangeValidator.class );

	/**
	 * Min value for integer range.  This value is acquired from IntegerRange 
	 * constraint annotation which requires it when the annotation is applied 
	 * to a property.
	 */
	private Integer min;
	
	/**
	 * Max value for integer range.  This value is acquired from IntegerRange 
	 * constraint annotation which requires it when the annotation is applied 
	 * to a property.
	 */
	private Integer max;
	
	/**
	 * Validation failure message.  This value is acquired from IntegerRange 
	 * constraint annotation which requires it when the annotation is applied 
	 * to a property.
	 */
	String message = "";
	
	/**
	 * Gather data validation constraints supplied by IntegerRange.
	 */
    @Override
    public void initialize(IntegerRange constraintAnnotation) {

    	this.min = constraintAnnotation.min();
    	this.max = constraintAnnotation.max(); 
    	String annotationMessage = constraintAnnotation.message();
    	this.message = annotationMessage != null ? annotationMessage : "";
    	
    }

    /**
     * Validation logic.
     */
    @Override
    public boolean isValid( Integer value, ConstraintValidatorContext context ) {
        
    	if ( value == null ) {
    		
    		/**
    		 * We wouldn't do null validation here since it is already handled 
    		 * with the @NotNull validator. 
    		 */
    		
    		return true;
    		
    	}
    	
    	boolean valid = false;
    	boolean minValid = false;
    	boolean maxValid = false;
    	int val = value.intValue();
    	
    	if ( this.min != null && this.max != null ) {
    		
    		minValid = this.min.intValue() <= val;
    		maxValid = this.max.intValue() >= val;
    		
    	} 
    	else if ( this.min != null ) {
    		
    		minValid = this.min.intValue() <= val;
    		maxValid = true;    		
    		
    	}    	
    	else if ( this.max == null ) {

    		maxValid = this.max.intValue() >= val;
    		minValid = true;
    		
    	}

    	try {
    		
        	this.logException( val, minValid, maxValid );
    		
    	} catch ( IllegalArgumentException e ) {
    		
    		/**
    		 * Spec Requirement: Checkout should throw an exception with an instructive, 
    		 * user-friendly message if Discount percent is not in the range 0-100.
    		 * 
    		 * Since this validation is handled through the framework, we will 
    		 * just catch and log this as info level since not passing validation 
    		 * is not critical here and expected in some cases.. not that exceptional.
    		 * If we were checking values later in a business process, we may want to
    		 * let an exception bubble up instead and fail fast if necessary.
    		 */
    		log.info( e.getMessage() ); 
    		
    	}
    	
    	return minValid && maxValid;
    	
    }
    
    /**
     * Ensure user friendly exception is thrown if integer out of acceptible bounds.
     * @param value
     * @param minValid
     * @param maxValid
     * @throws IllegalArgumentException
     */
    private void logException( int value, boolean minValid, boolean maxValid )
    	throws IllegalArgumentException {
    	
    	if ( !minValid ) {
    		
    		String exceptionMessage = this.message + " Value supplied was " + value + " which is not greater than or equal to " + this.min + ".";
    		throw new IllegalArgumentException( exceptionMessage );
    		
    	}    	
    	else if ( !maxValid ) {
    		
    		String exceptionMessage = this.message + " Value supplied was " + value + " which is not less than or equal to " + this.max + ".";
    		throw new IllegalArgumentException( exceptionMessage );
    		
    	}
    	
    }
    
}
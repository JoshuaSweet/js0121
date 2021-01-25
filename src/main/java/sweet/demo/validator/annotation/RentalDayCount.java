package sweet.demo.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import sweet.demo.validator.RentalDayCountValidator;

/**
 * Provides specific integer range validator annotation to ensure consistency for a specific field which 
 * may be ubiquitous throughout system, in this case.  This can be applied to any integer value to determine
 * if it is in the validated range, but it is named uniquely to explicitly indicate it's application to 
 * rental day count data.</br></br> 
 * Example:</br></br> 
 * &#64;RentalDayCount(message="Rental day count must be between 1 and 364.")</br>
 * <b>private Integer</b> rentalDayCount;</br>
 * 
 * @author Sweet
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RentalDayCountValidator.class)
public @interface RentalDayCount {

	/**
	 * Exception failure message with default value.
	 * @return
	 */
	String message() default "Rental day count must be between 1 and 364.";

	/*boiler plate to allow for grouping sets of constraints..not used in this demo*/
    Class<?>[] groups() default { };

    /*boiler plate to allow for payload which could be useful but not used in this demo*/
    Class<? extends Payload>[] payload() default { };

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	RentalDayCount[] value();
    }
    
}

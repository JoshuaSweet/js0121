package sweet.demo.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import sweet.demo.validator.OnOrAfterTodayValidator;

/**
 * Provides date range validation to ensure LocalDate is on or after today.</br></br> 
 * Example:</br></br> 
 * &#64;OnOrAfterToday(message="Checkout date must be on or after today.") </br>
 * <b>private LocalDate</b> checkoutDate;</br>
 * 
 * @author Sweet
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnOrAfterTodayValidator.class)
public @interface OnOrAfterToday {

	/**
	 * Exception failure message with default value.
	 * @return
	 */
	String message() default "Date must be on or after today.";

	/*boiler plate to allow for grouping sets of constraints..not used in this demo*/
    Class<?>[] groups() default { };

    /*boiler plate to allow for payload which could be useful but not used in this demo*/
    Class<? extends Payload>[] payload() default { };

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	OnOrAfterToday[] value();
    }
    
}

package sweet.demo.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import sweet.demo.validator.IntegerRangeValidator;

/**
 * Generic integer range validator annotation example.  This can be applied to any integer value to determine
 * if it is in a specific range.</br></br> 
 * Example:</br></br> 
 * &#64;IntegerRange(min=0,max=100,message="Discount must be between 0 and 100.")</br>
 * <b>private Integer</b> discountPercent;</br>
 * 
 * @author Sweet
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerRangeValidator.class)
public @interface IntegerRange {

	/**
	 * Exception failure message with default value.
	 * @return
	 */
	String message() default "not in range";

	/*boiler plate to allow for grouping sets of constraints..not used in this demo*/
    Class<?>[] groups() default { };

    /*boiler plate to allow for payload which could be useful but not used in this demo*/
    Class<? extends Payload>[] payload() default { };

    /**
     * Indicates min value for validation which must be specific when annotation is used.
     * @return
     */
	int min();
	
	/**
     * Indicates max value for validation which must be specific when annotation is used.
     * @return
     */
	int max();

	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	IntegerRange[] value();
    }
    
}

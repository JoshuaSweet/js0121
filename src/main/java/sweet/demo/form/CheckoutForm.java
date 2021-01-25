package sweet.demo.form;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import sweet.demo.validator.annotation.IntegerRange;
import sweet.demo.validator.annotation.RentalDayCount;
import sweet.demo.validator.annotation.OnOrAfterToday;

/**
 * Used exclusively for getting and validating data from client to hand off to
 * {@link sweet.demo.model.RentalAgreement}.  Validation is handled through custom
 * and imported validators.
 * @author Sweet
 *
 */
public class CheckoutForm {

	@NotEmpty(message="Tool code cannot be blank. Please provide an existing tool code.")
	private String toolCode;

	/* DEMO NOTE:
	 * Rental days would probably be capped by business. 
	 * Renting equipment out indefinitely would negatively impact other customers waiting to rent.
	 * I imagine in an actual app this would be capped at a month or less.
	 * Providing the limitation is a bit more robust.
	 * */
	
	@NotNull(message="Rental day count cannot be blank.  Please provide a value greater than 0.")
	@RentalDayCount(message="Rental day count must be between 1 and 364.") 
	private Integer rentalDayCount;
	
	@NotNull(message="Discount percent cannot be blank.  Please provide a value between 0 and 100.")
	@IntegerRange(min=0,max=100,message="Discount percent must be between 0 and 100.")
	private Integer discountPercent;
	
	/* DEMO NOTE: 
	 * You wouldn't probably checkout in the past, but the spec had historical date testing 
	 * so we can leave this custom validator @OnOrAfterToday out of demo to comply with spec although 
	 * it is fully functional.
	 *  */
	
	@NotNull(message="Checkout date cannot be blank.  Please provide a date value.")
	//@OnOrAfterToday(message="Checkout date must be on or after today.") 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate checkoutDate;
	
	public CheckoutForm( String toolCode, Integer rentalDayCount, Integer discountPercent, LocalDate checkoutDate ) {
		
		this.toolCode = toolCode;
		this.rentalDayCount = rentalDayCount;
		this.discountPercent = discountPercent;
		this.checkoutDate = checkoutDate;
		
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public Integer getRentalDayCount() {
		return rentalDayCount;
	}

	public void setRentalDayCount(Integer rentalDayCount) {
		this.rentalDayCount = rentalDayCount;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	
}

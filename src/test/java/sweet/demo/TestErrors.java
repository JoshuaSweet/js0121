package sweet.demo;

/**
 * Provides test error strings for consistency.
 * @author Sweet
 *
 */
public interface TestErrors {

	final String RESPONSE_ERROR_TOOLCODE_BLANK = "Tool code cannot be blank. Please provide an existing tool code.";
	final String RESPONSE_ERROR_TOOLCODE_DNE = "Error: Tool Code does not exist.";
	
	final String RESPONSE_ERROR_RENTALDAYCOUNT_BLANK = "Rental day count cannot be blank.  Please provide a value greater than 0.";
	final String RESPONSE_ERROR_RENTALDAYCOUNT_INVALID = "Rental day count must be between 1 and 364.";
	final String RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH = "Rental day count must be a whole number from 1 to 364.";
	
	final String RESPONSE_ERROR_DISCOUNTPERCENT_BLANK = "Discount percent cannot be blank.  Please provide a value between 0 and 100.";
	final String RESPONSE_ERROR_DISCOUNTPERCENT_INVALID = "Discount percent must be between 0 and 100.";
	final String RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH = "Discount percent must be a whole number from 0 to 100.";

	final String RESPONSE_ERROR_CHECKOUTDATE_BLANK = "Checkout date cannot be blank.  Please provide a date value.";
	final String RESPONSE_ERROR_CHECKOUTDATE_INVALID = "Checkout date must be today.";
	final String RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH = "Please provide date text in yyyy-mm-dd format.";
	
}

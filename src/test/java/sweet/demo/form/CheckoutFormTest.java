package sweet.demo.form;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static javax.validation.Validation.buildDefaultValidatorFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sweet.demo.TestErrors;

/**
 * Conducts constraint violation checking on CheckoutForm.
 * @author Sweet
 *
 */
class CheckoutFormTest implements TestErrors {

	private static ValidatorFactory validatorFactory;
	private static Validator validator;
	
	private final String DATA_TOOLCODE_VALID = "ladw";
	private final String DATA_TOOLCODE_INVALID_BLANK = "";	
	private final int DATA_RENTALDAYCOUNT_VALID_MIN = 1;
	private final int DATA_RENTALDAYCOUNT_VALID_HIGH = 364;
	private final int DATA_RENTALDAYCOUNT_INVALID_TOOHIGH = 365;
	private final int DATA_RENTALDAYCOUNT_INVALID_TOOLOW = 0;	
	private final int DATA_DISCOUNTPERCENT_VALID_MIN = 0;
	private final int DATA_DISCOUNTPERCENT_VALID_MAX = 100;
	private final int DATA_DISCOUNTPERCENT_INVALID_TOOLOW = -1;
	private final int DATA_DISCOUNTPERCENT_INVALID_TOOHIGH = 101;	
	private final LocalDate DATA_CHECKOUTDATE_VALID = LocalDate.now();
	
	@BeforeAll
	public static void createValidator() {
		
	    validatorFactory = buildDefaultValidatorFactory();
	    validator = validatorFactory.getValidator();
	    
	}
	
	@AfterAll
	public static void close() {
		
	    validatorFactory.close();
	    
	}
	
	/**
	 * ALL DATA
	 */
	
	@Test
	void test_checkoutForm_allValidData_returnsNoConstrainViolations() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
	    assertTrue(violations.isEmpty());
		
	}
	
	@Test
	void test_checkoutForm_allInvalidData_returnsNoConstrainViolations() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, null );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
	    assertTrue( violations.size() == 4 );
		
	}
	
	/**
	 * TOOL CODE
	 */
	
	@Test
	void test_checkoutForm_toolCode_blank_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_toolCode_null_invalid() {

		CheckoutForm form = new CheckoutForm( null, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_toolCode_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, null );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );

		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );			
			
		}
	    		
	}
	
	/**
	 * RENTAL DAY COUNT
	 */
	
	@Test
	void test_checkoutForm_rentalDayCount_null_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, null, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_rentalDayCount_tooLow_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_rentalDayCount_tooHigh_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_INVALID_TOOHIGH, this.DATA_DISCOUNTPERCENT_VALID_MIN, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_rentalDayCount_min_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, null  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );

		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
			assertTrue( !actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_rentalDayCount_high_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_VALID_HIGH, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, null  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );

		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
			assertTrue( !actualError.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
			
		}
	    		
	}
	
	/**
	 * DISCOUNT PERCENT
	 */
	
	@Test
	void test_checkoutForm_discountPercent_null_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_VALID_MIN, null, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_discountPercent_tooLow_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_discountPercent_tooHigh_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_INVALID_TOOHIGH, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_discountPercent_min_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_VALID_MIN, null  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
			assertTrue( !actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_discountPercent_max_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_VALID_MAX, null  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
			assertTrue( !actualError.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
	    		
	}
	
	/**
	 * CHECKOUT DATE
	 */
	
	@Test
	void test_checkoutForm_checkoutDate_null_invalid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_VALID, this.DATA_RENTALDAYCOUNT_VALID_MIN, this.DATA_DISCOUNTPERCENT_VALID_MIN, null );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );
		assertTrue( violations.size() == 1 );
		
		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( actualError.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
			
		}
	    		
	}
	
	@Test
	void test_checkoutForm_checkoutDate_valid() {

		CheckoutForm form = new CheckoutForm( this.DATA_TOOLCODE_INVALID_BLANK, this.DATA_RENTALDAYCOUNT_INVALID_TOOLOW, this.DATA_DISCOUNTPERCENT_INVALID_TOOLOW, this.DATA_CHECKOUTDATE_VALID  );
		Set<ConstraintViolation<CheckoutForm>> violations = validator.validate( form );

		for ( ConstraintViolation<CheckoutForm> violation : violations ) {
			
			String actualError = violation.getMessage();
			assertTrue( !actualError.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
			
		}
	    		
	}

}

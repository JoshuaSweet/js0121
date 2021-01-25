package sweet.demo.model;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sweet.demo.TestErrors;
import sweet.demo.form.CheckoutForm;

/**
 * Tests RentalAgreementTest
 * These tests focus mostly on evaluating if instance construction of RentalAgreement
 * is calculating and storing correct values.
 * @author Sweet
 *
 */
class RentalAgreementTest implements TestErrors {

	/**
	 * Will need validation factory for checking if entity validation is working properly.
	 * Entity validation is hooked up through validation annotations on the class properties.
	 */
	private static ValidatorFactory validatorFactory;
	private static Validator validator;
	
	private final ToolType TOOLTYPE_LADDER = new ToolType( "Ladder", 1.99, true, true, false );
	private final ToolType TOOLTYPE_CHAINSAW = new ToolType( "Chainsaw", 1.49, true, false, true );
	private final ToolType TOOLTYPE_JACKHAMMER = new ToolType( "Jackhammer", 2.99, true, false, false );
	
	private final Brand BRAND_WERNER = new Brand( "Werner" );
	private final Brand BRAND_STIHL = new Brand( "Stihl" );
	private final Brand BRAND_RIDGID = new Brand( "Ridgid" );
	private final Brand BRAND_DEWALT = new Brand( "DeWalt" );
	
	private final Tool TOOL_LADW = new Tool( this.TOOLTYPE_LADDER, this.BRAND_WERNER, "LADW" );
	private final Tool TOOL_CHNS = new Tool( this.TOOLTYPE_CHAINSAW, this.BRAND_STIHL, "CHNS" );
	private final Tool TOOL_JAKR = new Tool( this.TOOLTYPE_JACKHAMMER, this.BRAND_RIDGID, "JAKR" );
	private final Tool TOOL_JAKD = new Tool( this.TOOLTYPE_JACKHAMMER, this.BRAND_DEWALT, "JAKD" );
	
	@BeforeAll
	public static void createValidator() {
		
	    validatorFactory = buildDefaultValidatorFactory();
	    validator = validatorFactory.getValidator();
	    
	}
	
	@AfterAll
	public static void close() {
		
	    validatorFactory.close();
	    
	}
	
	@Test
	void test_rentalDayCount_tooLow_produces_IllegalArgumentException() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKR.getToolCode(), 0, 101, LocalDate.of( 2015, 9, 3 ) );
		
		try {
			
			RentalAgreement agreement = new RentalAgreement( this.TOOL_LADW, form );
			
		} catch ( IllegalArgumentException e ) {
			
			String actualMessage = e.getMessage();
			assertTrue( actualMessage.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
			
		}
		
	}
	
	@Test
	void test_discountPercent_tooLow_produces_IllegalArgumentException() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKR.getToolCode(), 5, 0, LocalDate.of( 2015, 9, 3 ) );
		
		try {
			
			RentalAgreement agreement = new RentalAgreement( this.TOOL_LADW, form );
			
		} catch ( IllegalArgumentException e ) {
			
			String actualMessage = e.getMessage();
			assertTrue( actualMessage.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
		
	}
	
	@Test
	void test_spec_consoleOutput_text() {

		CheckoutForm form = new CheckoutForm( this.TOOL_LADW.getToolCode(), 3, 10, LocalDate.of( 2020, 7, 2 ) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_LADW, form );
		String actualOutput = agreement.printToConsole();
	    
	    String expectedOutput = "\nTool code: LADW\n"
	    		+ "Tool type: Ladder\n"
	    		+ "Tool brand: Werner\n"
	    		+ "Rental days: 3\n"
	    		+ "Check out date: 07/02/20\n"
	    		+ "Due date: 07/05/20\n"
	    		+ "Daily rental charge: $1.99\n"
	    		+ "Charge days: 2\n"
	    		+ "Pre-discount charge: $3.98\n"
	    		+ "Discount percent: 10%\n"
	    		+ "Discount amount: $0.40\n"
	    		+ "Final charge: $3.58\n";
	    
	    assertTrue( expectedOutput.contains( actualOutput ) );	    
	    
	}
	
	@Test
	void test_spec_test_1_discountPercent_tooHigh_produces_IllegalArgumentException() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKR.getToolCode(), 5, 101, LocalDate.of( 2015, 9, 3 ) );
		
		try {
			
			RentalAgreement agreement = new RentalAgreement( this.TOOL_LADW, form );
			
		} catch ( IllegalArgumentException e ) {
			
			String actualMessage = e.getMessage();
			assertTrue( actualMessage.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
			
		}
		
	}

	@Test
	void test_spec_test_2() {

		CheckoutForm form = new CheckoutForm( this.TOOL_LADW.getToolCode(), 3, 10, LocalDate.of( 2020, 7, 2 ) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_LADW, form );
		agreement.printToConsole();
		Set<ConstraintViolation<RentalAgreement>> violations = validator.validate( agreement );
	    assertTrue( violations.isEmpty() );
	    assertTrue( agreement.getToolCode().contains( "LADW" ) );
	    assertTrue( agreement.getType().getName().contains( "Ladder" ) );
	    assertTrue( agreement.getBrand().getName().contains( "Werner" ) );
	    assertTrue( agreement.getRentalDays() == 3 );
	    assertTrue( agreement.getCheckoutDateFormattedString().contains( "07/02/20" ) );
	    assertTrue( agreement.getDueDateFormattedString().contains( "07/05/20" ) );
	    assertTrue( agreement.getDailyRentalChargeString().contains( "$1.99" ) );
	    assertTrue( agreement.getChargeDays() == 2 );
	    assertTrue( agreement.getPreDiscountChargeString().contains( "$3.98" ) );
	    assertTrue( agreement.getDiscountPercentString().contains( "10%" ) );
	    assertTrue( agreement.getDiscountAmountString().contains( "$0.40" ) );
	    assertTrue( agreement.getFinalChargeString().contains( "$3.58" ) );
	    
	}
	
	@Test
	void test_spec_test_3() {

		CheckoutForm form = new CheckoutForm( this.TOOL_CHNS.getToolCode(), 5, 25, LocalDate.of( 2015, 7, 2) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_CHNS, form );
		agreement.printToConsole();
		Set<ConstraintViolation<RentalAgreement>> violations = validator.validate( agreement );
	    assertTrue( violations.isEmpty() );
	    assertTrue( agreement.getToolCode().contains( "CHNS" ) );
	    assertTrue( agreement.getType().getName().contains( "Chainsaw" ) );
	    assertTrue( agreement.getBrand().getName().contains( "Stihl" ) );
	    assertTrue( agreement.getRentalDays() == 5 );
	    assertTrue( agreement.getCheckoutDateFormattedString().contains( "07/02/15" ) );
	    assertTrue( agreement.getDueDateFormattedString().contains( "07/07/15" ) );
	    assertTrue( agreement.getDailyRentalChargeString().contains( "$1.49" ) );
	    assertTrue( agreement.getChargeDays() == 3 );
	    assertTrue( agreement.getPreDiscountChargeString().contains( "$4.47" ) );
	    assertTrue( agreement.getDiscountPercentString().contains( "25%" ) );
	    assertTrue( agreement.getDiscountAmountString().contains( "$1.12" ) );
	    assertTrue( agreement.getFinalChargeString().contains( "$3.35" ) );
	    
	}
	
	@Test
	void test_spec_test_4() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKD.getToolCode(), 6, 0, LocalDate.of( 2015, 9, 3) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_JAKD, form );
		agreement.printToConsole();
		Set<ConstraintViolation<RentalAgreement>> violations = validator.validate( agreement );
	    assertTrue( violations.isEmpty() );
	    assertTrue( agreement.getToolCode().contains( "JAKD" ) );
	    assertTrue( agreement.getType().getName().contains( "Jackhammer" ) );
	    assertTrue( agreement.getBrand().getName().contains( "DeWalt" ) );
	    assertTrue( agreement.getRentalDays() == 6 );
	    assertTrue( agreement.getCheckoutDateFormattedString().contains( "09/03/15" ) );
	    assertTrue( agreement.getDueDateFormattedString().contains( "09/09/15" ) );
	    assertTrue( agreement.getDailyRentalChargeString().contains( "$2.99" ) );
	    assertTrue( agreement.getChargeDays() == 3 );
	    assertTrue( agreement.getPreDiscountChargeString().contains( "$8.97" ) );
	    assertTrue( agreement.getDiscountPercentString().contains( "0%" ) );
	    assertTrue( agreement.getDiscountAmountString().contains( "$0.00" ) );
	    assertTrue( agreement.getFinalChargeString().contains( "$8.97" ) );
		
	}
	
	@Test
	void test_spec_test_5() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKR.getToolCode(), 9, 0, LocalDate.of( 2015, 7, 2) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_JAKR, form );
		agreement.printToConsole();
		Set<ConstraintViolation<RentalAgreement>> violations = validator.validate( agreement );
	    assertTrue( violations.isEmpty() );
	    assertTrue( agreement.getToolCode().contains( "JAKR" ) );
	    assertTrue( agreement.getType().getName().contains( "Jackhammer" ) );
	    assertTrue( agreement.getBrand().getName().contains( "Ridgid" ) );
	    assertTrue( agreement.getRentalDays() == 9 );
	    assertTrue( agreement.getCheckoutDateFormattedString().contains( "07/02/15" ) );
	    assertTrue( agreement.getDueDateFormattedString().contains( "07/11/15" ) );
	    assertTrue( agreement.getDailyRentalChargeString().contains( "$2.99" ) );
	    assertTrue( agreement.getChargeDays() == 5 );
	    assertTrue( agreement.getPreDiscountChargeString().contains( "$14.95" ) );
	    assertTrue( agreement.getDiscountPercentString().contains( "0%" ) );
	    assertTrue( agreement.getDiscountAmountString().contains( "$0.00" ) );
	    assertTrue( agreement.getFinalChargeString().contains( "$14.95" ) );
		
	}
	
	@Test
	void test_spec_test_6() {

		CheckoutForm form = new CheckoutForm( this.TOOL_JAKR.getToolCode(), 4, 50, LocalDate.of( 2020, 7, 2) );
		RentalAgreement agreement = new RentalAgreement( this.TOOL_JAKR, form );
		agreement.printToConsole();
		Set<ConstraintViolation<RentalAgreement>> violations = validator.validate( agreement );
	    assertTrue( violations.isEmpty() );
	    assertTrue( agreement.getToolCode().contains( "JAKR" ) );
	    assertTrue( agreement.getType().getName().contains( "Jackhammer" ) );
	    assertTrue( agreement.getBrand().getName().contains( "Ridgid" ) );
	    assertTrue( agreement.getRentalDays() == 4 );
	    assertTrue( agreement.getCheckoutDateFormattedString().contains( "07/02/20" ) );
	    assertTrue( agreement.getDueDateFormattedString().contains( "07/06/20" ) );
	    assertTrue( agreement.getDailyRentalChargeString().contains( "$2.99" ) );
	    assertTrue( agreement.getChargeDays() == 1 );
	    assertTrue( agreement.getPreDiscountChargeString().contains( "$2.99" ) );
	    assertTrue( agreement.getDiscountPercentString().contains( "50%" ) );
	    assertTrue( agreement.getDiscountAmountString().contains( "$1.50" ) );
	    assertTrue( agreement.getFinalChargeString().contains( "$1.50" ) );
		
	}

}

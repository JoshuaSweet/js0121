package sweet.demo.controller;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sweet.demo.TestErrors;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class MainControllerTest implements TestErrors {

	private static final Logger log = LogManager.getLogger( MainControllerTest.class );
	
	private final String DATA_STRING_TOOLCODE_VALID_LADW = "LADW";
	private final String DATA_STRING_TOOLCODE_DNE = "non existent tool code";
	
	private final String DATA_STRING_RENTALDAYCOUNT_VALID = "1";
	private final String DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_LOW = "0";
	private final String DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_HIGH = "365";
	private final String DATA_STRING_RENTALDAYCOUNT_INVALID_TYPE_MISMATCH = "text instead of number";
	
	private final String DATA_STRING_DISCOUNTPERCENT_VALID = "1";
	private final String DATA_STRING_DISCOUNTPERCENT_INVALID_TOO_LOW = "-1";
	private final String DATA_STRING_DISCOUNTPERCENT_INVALID_TYPE_MISMATCH = "text instead of number";
	
	private final String DATA_STRING_CHECKOUTDATE_INVALID_TYPE_MISMATCH = "text instead of date";
	private final String DATA_STRING_CHECKOUTDATE_TODAY = LocalDate.now().format( ISO_DATE );
	private final String DATA_STRING_CHECKOUTDATE_TOMORROW = LocalDate.now().plus( 1, DAYS ).format( ISO_DATE );
	private final String DATA_STRING_CHECKOUTDATE_YESTERDAY = LocalDate.now().minus( 1, DAYS ).format( ISO_DATE );
	
	private final String PARAM_DISCOUNT_PERCENT = "discountPercent";
	private final String PARAM_RENTAL_DAY_COUNT = "rentalDayCount";
	private final String PARAM_TOOL_CODE = "toolCode";
	private final String PARAM_CHECKOUT_DATE = "checkoutDate";
	
	
	
	private final String RESPONSE_SUCCESS_RENTAL_AGREEMENT = "<h1>Rental Agreement</h1>";
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mvc;
	
	@Autowired
	MockServletContext context;
	
	@BeforeAll
	public void init() {
		
		mvc = MockMvcBuilders.webAppContextSetup( webApplicationContext ).build();
		
	}

	/**
	 * ALL DATA
	 */	
	
	/**
	 * DEMO NOTE:
	 * This test originally generated invalid date error but the spec seems to indicate
	 * historical dates should be tested.  Now we assert that it does not generate the 
	 * error because the validator to prevent checkouts for past dates has been silenced to
	 * comply with spec as I understand it.
	 */
	@Test
	void test_checkoutForm_validation_allData_invalid() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_LOW )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_INVALID_TOO_LOW )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_YESTERDAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
		
	}
	
	@Test
	void test_checkoutForm_validation_allData_invalid_mismatchReportedFirst() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_LOW )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_INVALID_TOO_LOW )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_INVALID_TYPE_MISMATCH )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
		
	}
	
	@Test
	void test_checkoutForm_validation_allData_valid() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}		
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * TOOL CODE
	 */
	
	@Test
	void test_checkoutForm_validation_toolCode_blank() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, "" )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertTrue( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_toolCode_dne() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_DNE )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_TOOLCODE_DNE ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * RENTAL DAY COUNT
	 */
	
	@Test
	void test_checkoutForm_validation_rentalDayCount_blank() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, "" )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_rentalDayCount_only_tooLow() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( "rentalDayCount", "0" )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertTrue( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
		
	}
	
	@Test
	void test_checkoutForm_validation_rentalDayCount_tooLow() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_LOW )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_rentalDayCount_tooHigh() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_INVALID_TOO_HIGH )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_rentalDayCount_typeMismatch_responseError() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_INVALID_TYPE_MISMATCH )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * DISCOUNT PERCENT
	 */
	
	@Test
	void test_checkoutForm_validation_discountPercent_blank() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, "" )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_discountPercent_tooLow() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_INVALID_TOO_LOW )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_discountPercent_only_tooLow() {
		
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
		    	      .post( "/checkout" )
		    	      .param( this.PARAM_DISCOUNT_PERCENT, "-1" )
		    	      .contentType( MediaType.MULTIPART_FORM_DATA )
		    	      .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertTrue( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
		
	}
	
	@Test
	void test_checkoutForm_validation_discountPercent_tooHigh() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_INVALID_TOO_LOW )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_discountPercent_only_tooHigh() {
		
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
		    	      .multipart( "/checkout" )
		    	      .param( this.PARAM_DISCOUNT_PERCENT, "101" )
		    	      .contentType( MediaType.MULTIPART_FORM_DATA )
		    	      .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception when mocking post action." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertTrue( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
		
				
	}
	
	@Test
	void test_checkoutForm_validation_discountPercent_typeMismatch_responseError() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_INVALID_TYPE_MISMATCH )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TODAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * CHECKOUT DATE
	 */
	
	@Test
	void test_checkoutForm_validation_allData_checkoutDate_blank() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, "" )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
				
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * Tests if a checkout date in the past causes a validation error.
	 * I have silenced the custom validator that performs this check
	 * in order to comply with the spec, so we assert false for receiving the 
	 * error in this case.
	 */
	@Test
	void test_checkoutForm_validation_allData_checkoutDate_tooSoon() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_TOMORROW )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}		
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * DEMO NOTE:
	 * This was originally to test the OnOrAfterToday custom validator,
	 * but I noticed in the spec there was a requirement to test historical
	 * data so now I assert that a checkout for yesterday does not generate
	 * the OnOrAfterToday validation error.
	 */
	@Test
	void test_checkoutForm_validation_allData_checkoutDate_yesterday() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_YESTERDAY )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	@Test
	void test_checkoutForm_validation_checkoutDate_typeMismatch_responseError() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, this.DATA_STRING_TOOLCODE_VALID_LADW )
					.param( this.PARAM_RENTAL_DAY_COUNT, this.DATA_STRING_RENTALDAYCOUNT_VALID )
					.param( this.PARAM_DISCOUNT_PERCENT, this.DATA_STRING_DISCOUNTPERCENT_VALID )
					.param( this.PARAM_CHECKOUT_DATE, this.DATA_STRING_CHECKOUTDATE_INVALID_TYPE_MISMATCH )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertTrue( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
	/**
	 * RENTAL AGREEMENT RESPONSES
	 */
	
	@Test
	void test_rentalAgreement_spec_test_1() {
				
		ResultActions action = null;
		
		try {
			
			action = mvc.perform( MockMvcRequestBuilders
					.post( "/checkout" )
					.param( this.PARAM_TOOL_CODE, "JAKR" )
					.param( this.PARAM_CHECKOUT_DATE, "2015-09-03" )
					.param( this.PARAM_RENTAL_DAY_COUNT, "5" )
					.param( this.PARAM_DISCOUNT_PERCENT, "101" )
		    	    .contentType( MediaType.MULTIPART_FORM_DATA )
		    	    .accept( MediaType.MULTIPART_FORM_DATA ) );
			
		} catch ( Exception e ) {
			
			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		String response = "";
		
		try {
			
			response = action.andReturn().getResponse().getContentAsString();
			
		} catch ( UnsupportedEncodingException e ) {

			log.error( e );
			fail( "Unexpected Exception." );
			
		}
		
		assertFalse( response.contains( RESPONSE_ERROR_TOOLCODE_BLANK ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_RENTALDAYCOUNT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_BLANK ) );
		assertTrue( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_DISCOUNTPERCENT_TYPE_MISMATCH ) );
		
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_BLANK ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_INVALID ) );
		assertFalse( response.contains( RESPONSE_ERROR_CHECKOUTDATE_TYPE_MISMATCH ) );
				
	}
	
}
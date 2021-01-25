package sweet.demo.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test DateUtil.
 * @author Sweet
 *
 */
public class DateUtilTest {

	private final String ILLEGAL_ARGUMENT_EXCEPTION_EXPECTED = "Expected IllegalArgumentException.";
	private final String ILLEGAL_ARGUMENT_EXCEPTION_UNEXPECTED = "Unexpected IllegalArgumentException.";
	private final LocalDate DATE_MONDAY = LocalDate.parse( "2021-01-18" );//Monday
	private final LocalDate DATE_TUESDAY = LocalDate.parse( "2021-01-19" );//Tuesday
	private final LocalDate DATE_WEDNESDAY = LocalDate.parse( "2021-01-20" );//Wednesday
	private final LocalDate DATE_THURSDAY = LocalDate.parse( "2021-01-21" );//Thursday
	private final LocalDate DATE_FRIDAY = LocalDate.parse( "2021-01-22" );//Friday
	private final LocalDate DATE_SATURDAY = LocalDate.parse( "2021-01-23" );//Saturday
	private final LocalDate DATE_SUNDAY = LocalDate.parse( "2021-01-24" );//Sunday
	private final int PERIOD_0_DAYS = 0;
	private final int PERIOD_1_DAYS = 1;
	private final int PERIOD_2_DAYS = 2;
	private final int PERIOD_3_DAYS = 3;
	private final int PERIOD_4_DAYS = 4;
	private final int PERIOD_5_DAYS = 5;
	private final int PERIOD_6_DAYS = 6;
	private final int PERIOD_7_DAYS = 7;
	private final int PERIOD_8_DAYS = 8;
	private final int PERIOD_16_DAYS = 16;
	private final int PERIOD_179_DAYS = 179;//arbitrary big number
	private final LocalDate INDEPENDENCE_SATURDAY = LocalDate.parse( "2009-07-04" );
	private final LocalDate INDEPENDENCE_SUNDAY = LocalDate.parse( "2010-07-04" );
	private final LocalDate INDEPENDENCE_FRIDAY = LocalDate.parse( "2014-07-04" );
	private final LocalDate DATE_END_OF_YEAR = LocalDate.parse( "2014-12-31" );
	private final LocalDate LABOR_DAY_2018 = LocalDate.parse( "2018-09-03" );
	private final LocalDate LABOR_DAY_2019 = LocalDate.parse( "2019-09-02" );
	private final LocalDate LABOR_DAY_2020 = LocalDate.parse( "2020-09-07" );
		
	/**
	 * WEEKDAYS IN PERIODS
	 */
	
	/**
	 * TEST MONDAY PERIODS
	 */
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_2_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_3_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_4_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_5_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_6_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_8_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_16_DAYS );
		assertEquals( 12, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_monday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_MONDAY, this.PERIOD_179_DAYS );
		assertEquals( 129, days );		
		
	}
	
/**
 * TEST TUESDAY PERIODS
 */
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_2_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_3_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_4_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_5_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_6_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_8_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_16_DAYS );
		assertEquals( 12, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_tuesday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_TUESDAY, this.PERIOD_179_DAYS );
		assertEquals( 128, days );		
		
	}
	
	/**
	 * TEST WEDNESDAY PERIODS
	 */
		
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_2_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_3_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_5_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_6_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_8_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_16_DAYS );
		assertEquals( 12, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_wednesday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_179_DAYS );
		assertEquals( 127, days );		
		
	}
	
	/**
	 * TEST THURSDAY PERIODS
	 */
		
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_2_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_3_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_5_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_6_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_8_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_16_DAYS );
		assertEquals( 11, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_thursday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_THURSDAY, this.PERIOD_179_DAYS );
		assertEquals( 127, days );		
		
	}
	
	/**
	 * TEST FRIDAY PERIODS
	 */
		
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_2_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_3_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_5_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_6_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_8_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_16_DAYS );
		assertEquals( 10, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_friday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_FRIDAY, this.PERIOD_179_DAYS );
		assertEquals( 127, days );		
		
	}
	
	/**
	 * TEST SATURDAY PERIODS
	 */
		
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_2_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_3_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_4_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_5_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_6_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_8_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_16_DAYS );
		assertEquals( 11, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_saturday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SATURDAY, this.PERIOD_179_DAYS );
		assertEquals( 128, days );		
		
	}
	
	/**
	 * TEST SUNDAY PERIODS
	 */
		
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_0() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_1() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_2() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_2_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_3() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_3_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_4() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_4_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_5() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_5_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_6() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_6_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_7() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_7_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_8() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_8_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_16() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_16_DAYS );
		assertEquals( 12, days );		
		
	}
	
	@Test
	public void test_weekdaysInPeriod_with_startDate_sunday_period_179() {
		
		int days = DateUtil.weekdaysInPeriod( this.DATE_SUNDAY, this.PERIOD_179_DAYS );
		assertEquals( 129, days );		
		
	}
	
	/**
	 * WEEKEND DAYS IN PERIODS
	 */
	
	/**
	 * MONDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_2_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_3_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_4_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_5_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_6_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_8_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_16_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_monday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_MONDAY, this.PERIOD_179_DAYS );
		assertEquals( 50, days );		
		
	}
	
	/**
	 * TUESDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_2_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_3_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_4_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_5_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_6_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_8_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_16_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_tuesday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_TUESDAY, this.PERIOD_179_DAYS );
		assertEquals( 51, days );		
		
	}
	
	/**
	 * WEDNESDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_2_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_3_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_5_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_6_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_8_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_16_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_wednesday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_WEDNESDAY, this.PERIOD_179_DAYS );
		assertEquals( 52, days );		
		
	}
	
	/**
	 * THURSDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_2_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_3_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_5_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_6_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_8_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_16_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_thursday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_THURSDAY, this.PERIOD_179_DAYS );
		assertEquals( 52, days );		
		
	}

	/**
	 * FRIDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_2_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_3_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_4_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_5_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_6_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_8_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_16_DAYS );
		assertEquals( 6, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_friday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_FRIDAY, this.PERIOD_179_DAYS );
		assertEquals( 52, days );		
		
	}
	
	/**
	 * SATURDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_1_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_2_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_3_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_4_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_5_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_6_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_8_DAYS );
		assertEquals( 3, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_16_DAYS );
		assertEquals( 5, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_saturday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SATURDAY, this.PERIOD_179_DAYS );
		assertEquals( 51, days );		
		
	}
	
	/**
	 * SUNDAY PERIODS
	 */
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_0() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_0_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_1() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_1_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_2() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_2_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_3() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_3_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_4() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_4_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_5() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_5_DAYS );
		assertEquals( 0, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_6() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_6_DAYS );
		assertEquals( 1, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_7() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_7_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_8() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_8_DAYS );
		assertEquals( 2, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_16() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_16_DAYS );
		assertEquals( 4, days );		
		
	}
	
	@Test
	public void test_weekendDaysInPeriod_with_startDate_sunday_period_179() {
		
		int days = DateUtil.weekendDaysInPeriod( this.DATE_SUNDAY, this.PERIOD_179_DAYS );
		assertEquals( 50, days );		
		
	}
	
	/**
	 * INDEPENDENCE DAY CHECKING
	 */
	
	@Test
	public void test_includesIndependenceDay_exception_with_period_too_high() {
		
		try {
			
			boolean includesIndependenceDay = DateUtil.includesIndependenceDay( this.DATE_MONDAY, 365 );
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_EXPECTED );
			
		} catch ( IllegalArgumentException e ) {
			
			/**
			 * PASS: Expected IllegalArgumentException thrown.
			 */
			
		}		
		
	}
	
	@Test
	public void test_includesIndependenceDay_no_exception_with_period_upper_bound() {
		
		try {
			
			boolean includesIndependenceDay = DateUtil.includesIndependenceDay( this.DATE_MONDAY, 364 );
			
		} catch ( IllegalArgumentException e ) {
			
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_UNEXPECTED );
			
		}		
		
	}
	
	@Test
	public void test_includesIndependenceDay_exception_with_period_too_low() {
		
		try {
			
			boolean includesIndependenceDay = DateUtil.includesIndependenceDay( this.DATE_MONDAY, 0 );
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_EXPECTED );
			
		} catch ( IllegalArgumentException e ) {
			
			/**
			 * PASS: Expected IllegalArgumentException thrown.
			 */
			
		}
				
	}
	
	@Test
	public void test_includesIndependenceDay_no_exception_with_period_lower_bound() {
		
		try {
			
			boolean includesIndependenceDay = DateUtil.includesIndependenceDay( this.DATE_MONDAY, 1 );
			
		} catch ( IllegalArgumentException e ) {
			
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_UNEXPECTED );
			
		}		
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_5_days_prior_period_5() {
		
		LocalDate start = this.INDEPENDENCE_FRIDAY.minus( 5, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 5 );
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_5_days_prior_period_6() {
		
		LocalDate start = this.INDEPENDENCE_FRIDAY.minus( 5, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 6 );
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_5_days_prior_period_4() {
		
		LocalDate start = this.INDEPENDENCE_FRIDAY.minus( 5, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 4 );
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_5_days_after_period_4() {
		
		LocalDate start = this.INDEPENDENCE_FRIDAY.plus( 5, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 4 );
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_dayOf_period_4() {
		
		LocalDate start = this.INDEPENDENCE_FRIDAY;
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 4 );
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_dayOfOnSaturday_period_4() {
		
		LocalDate start = this.INDEPENDENCE_SATURDAY;
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 4 );
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_dayOfOnSunday_period_4() {
		
		LocalDate start = this.INDEPENDENCE_SUNDAY;
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 4 );
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_endOfYear_period_300() {
		
		LocalDate start = this.DATE_END_OF_YEAR;
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 300 );
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_previousYearToIndependenceSunday_period_300() {
		
		LocalDate start = this.INDEPENDENCE_SUNDAY.minus( 300, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 300 );//moved to next Monday
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_previousYearToIndependenceSunday_period_301() {
		
		LocalDate start = this.INDEPENDENCE_SUNDAY.minus( 300, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 301 );//moved to next Monday
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_previousYearToIndependenceSaturday_period_300() {
		
		LocalDate start = this.INDEPENDENCE_SATURDAY.minus( 300, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 300 );//moved to previous Friday
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_previousYearToIndependenceSaturday_period_299() {
		
		LocalDate start = this.INDEPENDENCE_SATURDAY.minus( 300, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 299 );//moved to previous Friday
		assertTrue( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesIndependenceDay_with_start_previousYearToIndependenceSaturday_period_298() {
		
		LocalDate start = this.INDEPENDENCE_SATURDAY.minus( 300, ChronoUnit.DAYS );
		boolean includesIndependenceDay = DateUtil.includesIndependenceDay( start, 298 );//moved to previous Friday
		assertFalse( includesIndependenceDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_previousYear_period_360() {
		
		LocalDate start = this.LABOR_DAY_2018.minus( 360, ChronoUnit.DAYS );
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 360 );
		assertTrue( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_sameYear_period_5() {
		
		LocalDate start = this.LABOR_DAY_2018.minus( 5, ChronoUnit.DAYS );
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 5 );
		assertTrue( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_tooFarBefore() {
		
		LocalDate start = this.LABOR_DAY_2018.minus( 6, ChronoUnit.DAYS );
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 5 );
		assertFalse( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_after() {
		
		LocalDate start = this.LABOR_DAY_2018.plus( 1, ChronoUnit.DAYS );
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 5 );
		assertFalse( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_dayOf() {
		
		LocalDate start = this.LABOR_DAY_2018;
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 1 );
		assertFalse( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_with_start_dayOf_period_364() {
		
		LocalDate start = this.LABOR_DAY_2018;
		boolean includesLaborDay = DateUtil.includesLaborDay( start, 364 );
		assertTrue( includesLaborDay );
		
	}
	
	@Test
	public void test_includesLaborDay_exception_with_period_too_low() {
		
		LocalDate start = this.LABOR_DAY_2018;
		
		try {
			
			boolean includesLaborDay = DateUtil.includesLaborDay( start, 0 );
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_EXPECTED );
			
		} catch ( IllegalArgumentException e ) {
			
			/**
			 * PASS: Expected exception thrown.
			 */
			
		}
		
	}
	
	@Test
	public void test_includesLaborDay_exception_with_period_too_high() {
		
		LocalDate start = this.LABOR_DAY_2018;
		
		try {
			
			boolean includesLaborDay = DateUtil.includesLaborDay( start, 365 );
			fail( this.ILLEGAL_ARGUMENT_EXCEPTION_EXPECTED );
			
		} catch ( IllegalArgumentException e ) {
			
			/**
			 * PASS: Expected exception thrown.
			 */
			
		}
		
	}
	
}

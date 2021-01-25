package sweet.demo.util;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Provides static convenience methods for date evaluation.
 * @author Sweet
 *
 */
public class DateUtil {

	private static final int DAYS_IN_WHOLE_WEEK = 7;
	private static final int DAYS_IN_WORK_WEEK = 5;
	private static final int DAYS_IN_WEEKEND = 2;
	
	/**
	 * Ensures period parameter is int between 1 and 364.
	 * @param period designates a span of days
	 * @throws IllegalArgumentException
	 */
	private static void ensurePeriodRange( int period ) 
		throws IllegalArgumentException {
		
		if ( period < 1 || period > 364 ) {
			
			throw new IllegalArgumentException( "Please provide a period between 1 and 364 days." );
			
		}
		
	};
	
	/**
	 * Determines labor day date for given year.
	 * @param year
	 * @return date of labor day
	 */
	private static LocalDate getLaborDay( int year ) {
		
		LocalDate day = LocalDate.of( year, 9, 1 );
		
		if ( day.getDayOfWeek() != DayOfWeek.MONDAY ) {
			
			LocalDate nextMonday = day.with(TemporalAdjusters.next( DayOfWeek.MONDAY ) );
			day = nextMonday;
			
		} 
		
		return day;
		
	}
	
	/**
	 * Calculate the number of weekdays occurring in a period of days from given start date (excludes start date).
	 * @param startDate
	 * @param period in days
	 * @return number of weekdays in period
	 */
	public static int weekdaysInPeriod( LocalDate startDate, int period ) {
		
		int weekdays = 0;	
		
		if ( period != 0 ) {
			
			int totalWholeWeeks = period / DateUtil.DAYS_IN_WHOLE_WEEK;
			int weekDaysFromWholeWeeks = totalWholeWeeks * DateUtil.DAYS_IN_WORK_WEEK;
			weekdays = weekDaysFromWholeWeeks;
			
			int totalDaysPartialWeek = period % DateUtil.DAYS_IN_WHOLE_WEEK;
			
			if ( totalDaysPartialWeek > 0 ) {
				
				period = totalDaysPartialWeek == 0 ? period : totalDaysPartialWeek;
				
				switch ( startDate.getDayOfWeek() ) {
				
					case SATURDAY:
						weekdays += period - 1;
						break;
						
					case SUNDAY:
						weekdays = period < 6 ? ( weekdays + period ) : ( weekdays + period - 1 );
						break;
						
					default:
						int dayOfWeek = startDate.getDayOfWeek().getValue();
						int weekDaysRemainingInWeek = 5 - dayOfWeek;
						
						if ( period >= weekDaysRemainingInWeek + 1 && period <= weekDaysRemainingInWeek + 2 ) {//period ends in weekend
							
							weekdays += weekDaysRemainingInWeek;
							
						} 
						else if ( period <= weekDaysRemainingInWeek ) {//period begins and ends in weekdays without crossing weekend
							
							weekdays += period;
							
						}	
						else if ( period > weekDaysRemainingInWeek + 2 ) {//period passes through weekend
							
							weekdays += period - 2;
							
						}
						break;
				
				}

			}
			
		}
		
		return weekdays;
		
	}	
	
	/**
	 * Calculate the number of weekend days occurring in a period of days from given start date (excludes start date).
	 * @param startDate
	 * @param period in days
	 * @return number of weekend days occurring in period
	 */
	public static int weekendDaysInPeriod( LocalDate startDate, int period ) {
		
		int weekendDays = 0;	
		
		if ( period != 0 ) {
			
			int totalWholeWeeks = period / DateUtil.DAYS_IN_WHOLE_WEEK;
			int weekendDaysFromWholeWeeks = totalWholeWeeks * DateUtil.DAYS_IN_WEEKEND;
			weekendDays = weekendDaysFromWholeWeeks;
			
			int totalDaysPartialWeek = period % DateUtil.DAYS_IN_WHOLE_WEEK;
			
			if ( totalDaysPartialWeek > 0 ) {
				
				period = totalDaysPartialWeek == 0 ? period : totalDaysPartialWeek;
				
				switch ( startDate.getDayOfWeek() ) {
				
					case SATURDAY:
						weekendDays += 1;
						break;
						
					case SUNDAY:
						weekendDays = period == 6 ? weekendDays += 1 : weekendDays;
						break;
						
					default:
						int dayOfWeek = startDate.getDayOfWeek().getValue();
						int weekDaysRemainingInWeek = 5 - dayOfWeek;
						
						if ( period == weekDaysRemainingInWeek + 1 && period <= weekDaysRemainingInWeek + 2 ) {//period ends on saturday
							
							weekendDays += 1;
							
						} 
						else if ( period >= weekDaysRemainingInWeek + 2 ) {//period ends on sunday
							
							weekendDays += 2;
							
						} 
						break;
				
				}

			}
			
		}
		
		return weekendDays;
		
	}
	
	/**
	 * If holiday date lands on Saturday, move to previous Friday.
	 * If holiday date lands on Sunday, move to following Monday.
	 * @param date holiday date
	 * @return adjusted date
	 */
	public static LocalDate adjustWeekendHoliday( LocalDate date ) {
		
		switch ( date.getDayOfWeek() ) {
				
			case SATURDAY:
				date = date.minus( 1, ChronoUnit.DAYS );
				break;
			case SUNDAY:
				date = date.plus( 1, ChronoUnit.DAYS );
				break;
		
		}
		
		return date;
		
	}
	
	/**
	 * Determines if United States Independence Day occurs in the given period excluding the start date.
	 * @param startDate
	 * @param period in days
	 * @return true if Independence Day occurs in period
	 */
	public static boolean includesIndependenceDay( LocalDate startDate, int period )
		throws IllegalArgumentException {
		
		DateUtil.ensurePeriodRange( period );

		boolean includesIndependenceDay = false;		
		LocalDate independenceDay = LocalDate.of( startDate.getYear(), 7, 4 );		
		independenceDay = DateUtil.adjustWeekendHoliday( independenceDay );
		
		if ( independenceDay.isBefore( startDate ) || independenceDay.isEqual( startDate ) ) {
			
			 independenceDay = LocalDate.of( startDate.getYear() + 1, 7, 4 );			 
			 independenceDay = DateUtil.adjustWeekendHoliday( independenceDay );
			
		}
		
		int daysUntil = (int) startDate.until( independenceDay, ChronoUnit.DAYS );
		
		if ( daysUntil <= period ) {
			
			includesIndependenceDay = true;
			
		}		
		
		return includesIndependenceDay;
		
	}
	
	/**
	 * Determines if United States Labor Day occurs in the given period excluding the start date.
	 * @param startDate
	 * @param period in days
	 * @return true if Labor Day exists within period
	 * @throws IllegalArgumentException
	 */
	public static boolean includesLaborDay( LocalDate startDate, int period )
		throws IllegalArgumentException {
		
		DateUtil.ensurePeriodRange( period );

		boolean includesLaborDay = false;		
		LocalDate laborDay = DateUtil.getLaborDay( startDate.getYear() );	
		
		if ( laborDay.isBefore( startDate ) || laborDay.isEqual( startDate ) ) {
			
			laborDay = DateUtil.getLaborDay( startDate.getYear() + 1 );
			
		}
		
		int daysUntil = (int) startDate.until( laborDay, ChronoUnit.DAYS );
		
		if ( daysUntil <= period ) {
			
			includesLaborDay = true;
			
		}		
		
		return includesLaborDay;
		
	}
		
}

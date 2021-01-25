package sweet.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.Locale;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import sweet.demo.form.CheckoutForm;
import sweet.demo.util.DateUtil;
import sweet.demo.validator.annotation.IntegerRange;
import sweet.demo.validator.annotation.RentalDayCount;

/**
 * Used to build and persist rental agreement data.
 * @author Sweet
 *
 */
@Entity
@Table(name="RENTAL_AGREEMENT", schema="RENTABLES")
public class RentalAgreement extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable=false)
	private String toolCode;
	
	@NotNull(message="Rental day count cannot be blank.  Please provide a value greater than 0.")
	@RentalDayCount(message="Rental day count must be between 1 and 364.") 
	@Column(nullable=false)
	private int rentalDays;
	
	@NotNull
	@Column(nullable=false)
	private int chargeDays;
	
	@NotNull(message="Checkout date cannot be blank.  Please provide a date value.")
	//@OnOrAfterToday(message="Checkout date must be on or after today.") 
	@Column(nullable=false)
	private LocalDate checkoutDate;
	
	@NotNull
	@Column(nullable=false)
	private LocalDate dueDate;
	
	@NotNull
	@Column(nullable=false, columnDefinition="Decimal(5,2)")
	private double dailyRentalCharge;
	
	@NotNull
	@Column(nullable=false, columnDefinition="Decimal(10,2)")
	private BigDecimal preDiscountCharge;
	
	@NotNull(message="Discount percent cannot be blank.  Please provide a value between 0 and 100.")
	@IntegerRange(min=0,max=100,message="Discount percent must be between 0 and 100.")
	@Column(nullable=false)
	private int discountPercent;
	
	@NotNull
	@Column(nullable=false, columnDefinition="Decimal(10,2)")
	private BigDecimal discountAmount;
	
	@NotNull
	@Column(nullable=false, columnDefinition="Decimal(10,2)")
	private BigDecimal finalCharge;
	
	@ManyToOne(cascade=CascadeType.DETACH)
	private Tool tool;
	
	@NotNull
	@JoinColumn(name="type_id",nullable=false)	
	@ManyToOne
	private ToolType type;
	
	@NotNull
	@JoinColumn(name="brand_id",nullable=false)	
	@ManyToOne
	private Brand brand;
	
	public RentalAgreement() {
		
		this.id = UUID.randomUUID();
		
	}
	
	public RentalAgreement( Tool tool, CheckoutForm checkoutForm ) {
		
		this.id = UUID.randomUUID();
		this.tool = tool;
		this.type = tool.getType();
		this.brand = tool.getBrand();
		this.toolCode = tool.getToolCode();		
		this.setRentalDays( checkoutForm.getRentalDayCount() );
		this.checkoutDate = checkoutForm.getCheckoutDate();
		this.dueDate = this.calculateDueDate();
		this.dailyRentalCharge = tool.getType().getDailyCharge();
		this.chargeDays = this.calculateChargeDays();
		this.setDiscountPercent( checkoutForm.getDiscountPercent() );
		this.preDiscountCharge = this.calculatePreDiscountCharge(); 
		this.discountAmount = this.calculateDiscountAmount();
		this.finalCharge = this.calculateFinalCharge(); 
		
		/* waiting until after calculations complete to round for greatest accuracy */
		
		this.preDiscountCharge = this.round( this.preDiscountCharge );
		this.discountAmount = this.round( this.discountAmount );
		this.finalCharge = this.round( this.finalCharge );
		
	}
	
	/**
	 * Calculate due date from checkout date plus rental days.
	 * @return due date
	 */
	private LocalDate calculateDueDate() {
		
		LocalDate calculatedDueDate = this.checkoutDate.plus( this.rentalDays, DAYS );
		return calculatedDueDate;
		
	}
	
	/**
	 * Calculate pre discount charge based on charge per day and total days renting.
	 * @return pre discount charge
	 */
	private BigDecimal calculatePreDiscountCharge() {
		
		BigDecimal calculatedCharge = new BigDecimal( this.dailyRentalCharge ).multiply( new BigDecimal( this.chargeDays ) );
		return calculatedCharge;
		
	}
	
	/**
	 * Calculate discount amount as percent of total charge.
	 * @return discount amount
	 */
	private BigDecimal calculateDiscountAmount() {
		
		BigDecimal calculatedAmount = this.preDiscountCharge.multiply( new BigDecimal( this.discountPercent ).divide( new BigDecimal( 100 ) ) );
		return calculatedAmount;
		
	}
	
	/**
	 * Calculate final charge as total minus discount.
	 * @return final charge
	 */
	private BigDecimal calculateFinalCharge() {
		
		BigDecimal calculatedAmount = this.preDiscountCharge.subtract( this.discountAmount );
		return calculatedAmount;
		
	}
	
	/**
	 * Calculate the total number of days to charge conditionally on if tool type
	 * participates in weekday, weekend, and/or holiday billing.
	 * @return total days that will incur charges
	 */
	private int calculateChargeDays() {
		
		int calculatedDays = 0;
		int independenceDay = DateUtil.includesIndependenceDay( this.checkoutDate, this.rentalDays ) ? 1 : 0;
		int laborDay = DateUtil.includesLaborDay( this.checkoutDate, this.rentalDays ) ? 1 : 0;
		int holidays = independenceDay + laborDay;
		int weekDays = DateUtil.weekdaysInPeriod( this.checkoutDate, this.rentalDays );
		int weekendDays = DateUtil.weekendDaysInPeriod( this.checkoutDate, this.rentalDays );
		
		ToolType toolType = this.type;
		
		if ( toolType.isWeekdayCharge() ) {
			
			calculatedDays = calculatedDays + weekDays;
			
		}
		
		if ( !toolType.isHolidayCharge() && toolType.isWeekdayCharge() ) {
			
			calculatedDays = calculatedDays - holidays;
			
		}

		if ( toolType.isWeekendCharge() ) {
			
			calculatedDays = calculatedDays + weekendDays;
			
		}
		
		return calculatedDays;
		
	}
	
	/**
	 * Convenience for rounding currency.
	 * @param amount
	 * @return rounded amount
	 */
	private BigDecimal round( BigDecimal amount ) {
		
		amount.setScale( 2, RoundingMode.HALF_UP );
		return amount;
		
	}
	
	public Tool getTool() {
		return this.tool;
	}
	
	public String getToolCode() {
		return this.tool.getToolCode();
	}
	
	@JoinColumn(name="tool_id",nullable=false)
	public UUID getToolId() {
		return this.tool.getId();
	}
	
	public ToolType getType() {
		return this.type;
	}
	
	public UUID getTypeId() {
		return this.tool.getType().getId();	
	}
	
	public Brand getBrand() {
		return this.brand;
	}
			
	public UUID getBrandId() {
		return this.tool.getBrand().getId();
	}
	
	/**
	 * DEMO NOTES: (same as note on discount percent below)
	 * This method provides a redundancy to the validation that is already in place. The rental day count is 
	 * received by the {@link CheckoutForm} which is validated enroute to {@link sweet.demo.controller.MainController} 
	 * post mapping.  The RentalAgreement is mapped to the db via hibernate and validation would occur again prior to 
	 * persisting the entity.  If, however, a developer were to create a function that generated RentalAgreements
	 * internally and circumvented validation, throwing an exception here could be a useful way 
	 * to fail fast.  It does mean we would never see ConstraintViolaitonExceptions for the rental days range, 
	 * but other validation annotations on the rental days field would be evaluated prior to persistence as normal.
	 * It's worth noting, though, that this deep in the application (in the service prior to persistence) we wouldn't "see"
	 * the constraint violations anyway like we do with the CheckoutForm at the controller.  When validation fails during
	 * a persistence attempt, you would see a transaction exception without the detailed messages provided in the annotation.
	 * I think you could create an exception handler for the transaction exception that is thrown which might give
	 * you access to the original constraint violation messages to display as needed. That seems like overkill for the demo, 
	 * though, and if this were a real app, I'm not sure the additional code to maintain would be justified anyway.  
	 * This seems sufficient for the spec.
	 */
	private void setRentalDays( int rentalDays ) {
		
		if ( rentalDays < 1 ) {
			
			throw new IllegalArgumentException( "Rental day count must be between 1 and 364." );
			
		} else {
			
			this.rentalDays = rentalDays;
			
		}
		
	}
	
	public int getRentalDays() {
		return rentalDays;
	}
	
	public LocalDate getCheckoutDate() {
		return this.checkoutDate;		
	}
	
	public String getCheckoutDateFormattedString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YY"); 
		String formattedDate = formatter.format( this.checkoutDate );
		return formattedDate;
		
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public String getDueDateFormattedString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YY"); 
		String formattedDate = formatter.format( this.dueDate );
		return formattedDate;
		
	}
	
	public double getDailyRentalCharge() {
		return this.dailyRentalCharge;
	}
	
	public String getDailyRentalChargeString() {
		
		String formattedAmount = NumberFormat.getCurrencyInstance( Locale.US ).format( this.dailyRentalCharge );
		return formattedAmount;
		
	}
	
	public int getChargeDays() {
		return this.chargeDays;
	}
	
	public BigDecimal getPreDiscountCharge() {
		return this.preDiscountCharge;
	}
	
	public String getPreDiscountChargeString() {
		
		String formattedAmount = NumberFormat.getCurrencyInstance( Locale.US ).format( this.preDiscountCharge );
		return formattedAmount;
		
	}
	
	/**
	 * DEMO NOTES: (same as rental days note above)
	 * This method provides a redundancy to the validation that is already in place. The discount percent is 
	 * received by the {@link CheckoutForm} which is validated enroute to {@link sweet.demo.controller.MainController} 
	 * post mapping.  The RentalAgreement is mapped to the db via hibernate and validation would occur again prior to 
	 * persisting the entity.  If, however, a developer were to create a function that generated RentalAgreements
	 * internally and circumvented validation, throwing and exception here could be a useful way 
	 * to fail fast.  It does mean we would never see ConstraintViolaitonExceptions for the discount percent range, 
	 * but other validation annotations on the discount percent field would be evaluated prior to persistence as normal.
	 * It's worth noting, though, that this deep in the application (in the service prior to persistence) we wouldn't "see"
	 * the constraint violations anyway like we do with the CheckoutForm at the controller.  When validation fails during
	 * a persistence attempt, you would see a transaction exception without the detailed messages provided in the annotation.
	 * I think you could create an exception handler for the transaction exception that is thrown which might give
	 * you access to the original constraint violation messages to display as needed. That seems like overkill for the demo, 
	 * though, and if this were a real app, I'm not sure the additional code to maintain would be justified anyway.  
	 * This seems sufficient for the spec.
	 */
	private void setDiscountPercent( int discountPercent ) {
		
		if ( discountPercent < 0 || discountPercent > 100 ) {
			
			throw new IllegalArgumentException( "Discount percent must be between 0 and 100." );
			
		} else {
			
			this.discountPercent = discountPercent;
			
		}
		
	}
	
	public int getDiscountPercent() {
		return this.discountPercent;
	}
	
	public String getDiscountPercentString() {
		
		String formattedAmount = NumberFormat.getPercentInstance( Locale.US ).format( ((double) this.discountPercent) * 0.01 );
		return formattedAmount;
		
	}
	
	public BigDecimal getDiscountAmount() {
		return this.discountAmount;
	}
	
	public String getDiscountAmountString() {
		
		String formattedAmount = NumberFormat.getCurrencyInstance( Locale.US ).format( this.discountAmount );
		return formattedAmount;
		
	}
	
	public BigDecimal getFinalCharge() {
		return this.finalCharge;
	}
	
	public String getFinalChargeString() {
		
		String formattedAmount = NumberFormat.getCurrencyInstance( Locale.US ).format( this.finalCharge );
		return formattedAmount;
		
	}
	
	/**
	 * SPEC COMPLIANCE:
	 * Rental Agreement method to print rental data to console.
	 * @return
	 */
	public String printToConsole() {
		
		String outPut =   "\n" +
			"Tool code: " + this.toolCode + "\n" +
			"Tool type: " + this.type.getName() + "\n" +
			"Tool brand: " + this.brand.getName() + "\n" +
			"Rental days: " + this.rentalDays + "\n" +
			"Check out date: " + this.getCheckoutDateFormattedString() + "\n" +
			"Due date: " + this.getDueDateFormattedString() + "\n" +
			"Daily rental charge: " + this.getDailyRentalChargeString() + "\n" + 
			"Charge days: " + this.chargeDays + "\n" +
			"Pre-discount charge: " + this.getPreDiscountChargeString() + "\n" +
			"Discount percent: " + this.getDiscountPercentString() + "\n" +
			"Discount amount: " + this.getDiscountAmountString() + "\n" +
			"Final charge: " + this.getFinalChargeString() + "\n";
		
		 System.out.println( outPut );
		 return outPut;//useful for testing
		
	}
	
}

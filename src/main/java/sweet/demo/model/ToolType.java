package sweet.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Basic class for managing tool type data.
 * @author Sweet
 *
 */
@Entity
@Table(name="TOOLTYPE", schema="RENTABLES")
public class ToolType extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable=false, columnDefinition="Decimal(5,2)")
	private double dailyCharge;
	
	@Column(nullable=false)
	private boolean weekdayCharge;
	
	@Column(nullable=false)
	private boolean weekendCharge;
	
	@Column(nullable=false)	
	private boolean holidayCharge;
	
	public ToolType() {
		
		this.id = UUID.randomUUID();
		
	}
	
	public ToolType( String name, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge ) {
		
		this.id = UUID.randomUUID();
		this.name = name;
		this.dailyCharge = dailyCharge;
		this.weekdayCharge = weekdayCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}

	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}

	public boolean isWeekendCharge() {
		return weekendCharge;
	}

	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}
	
	public boolean isHolidayCharge() {
		return holidayCharge;
	}

	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
	
}

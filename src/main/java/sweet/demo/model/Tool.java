package sweet.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Tools consist of a tool code and related types and brands.
 * @author Sweet
 *
 */
@Entity
@Table(name="TOOL", schema="RENTABLES")
public class Tool extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable=false, unique=true)
	private String toolCode;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="type_id",nullable=false)	
	ToolType type;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="brand_id",nullable=false)	
	Brand brand;
	
	public Tool() {
		
		this.id = UUID.randomUUID();
		
	}
	
	public Tool( ToolType type, Brand brand, String toolCode ) {
		
		this.id = UUID.randomUUID();
		this.type = type;
		this.brand = brand;
		this.toolCode = toolCode;
		
	}
	
	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	
	public ToolType getType() {
		return this.type;
	}
	
	public void setType(ToolType type) {
		this.type = type;
	}
	
	public Brand getBrand() {
		return this.brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}

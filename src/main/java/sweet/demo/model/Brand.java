package sweet.demo.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple entity for tracking rentable brands.
 * @author Sweet
 *
 */
@Entity
@Table(name="BRAND", schema="RENTABLES")
public class Brand extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable=false, unique=true)
	private String name;
	
	public Brand() {
		
		this.id = UUID.randomUUID();
		
	}
	
	public Brand( String name ) {
		
		this.id = UUID.randomUUID();
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

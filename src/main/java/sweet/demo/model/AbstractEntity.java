package sweet.demo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * Provides reusable properties and methods consistent among all entity classes.
 * @author Sweet
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type="uuid-char")
	@Column(name = "id", updatable = false, nullable = false)
	protected UUID id;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
}

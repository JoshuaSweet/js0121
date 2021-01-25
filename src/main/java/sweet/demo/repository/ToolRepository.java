package sweet.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sweet.demo.model.Tool;

/**
 * Tool CRUD.
 * @author Sweet
 *
 */
public interface ToolRepository extends CrudRepository<Tool, UUID> {

	Optional<Tool> findByToolCode(String toolCode);

	Optional<Tool> findById(UUID id);
  
}
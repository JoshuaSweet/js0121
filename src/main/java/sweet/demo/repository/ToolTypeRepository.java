package sweet.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import sweet.demo.model.ToolType;

/**
 * ToolType CRUD.
 * @author Sweet
 *
 */
public interface ToolTypeRepository extends CrudRepository<ToolType, UUID> {

  Optional<ToolType> findById(UUID id);
  
}
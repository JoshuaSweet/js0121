package sweet.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import sweet.demo.model.Brand;

/**
 * Brand CRUD.
 * @author Sweet
 *
 */
public interface BrandRepository extends CrudRepository<Brand, UUID> {

  Optional<Brand> findById(UUID id);
  
}
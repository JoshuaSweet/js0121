package sweet.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import sweet.demo.model.RentalAgreement;

/**
 * RentalAgreement CRUD.
 * @author Sweet
 *
 */
public interface RentalAgreementRepository extends CrudRepository<RentalAgreement, UUID> {

	List<RentalAgreement> findByToolCode(String toolCode);

	Optional<RentalAgreement> findById(UUID id);
  
}
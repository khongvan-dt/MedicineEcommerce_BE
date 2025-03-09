package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByDeletedAtIsNull();

    Optional<Medicine> findByIdAndDeletedAtIsNull(Long id);

    Optional<Medicine> findByCodeAndDeletedAtIsNull(String code);

    List<Medicine> findByNameAndDeletedAtIsNull(String name);

    List<Medicine> findByNameContainingAndDeletedAtIsNull(String namePattern);

    List<Medicine> findByBrandIdAndDeletedAtIsNull(Long brandId);

    List<Medicine> findByOriginAndDeletedAtIsNull(String origin);

    List<Medicine> findByManufacturerAndDeletedAtIsNull(String manufacturer);
}

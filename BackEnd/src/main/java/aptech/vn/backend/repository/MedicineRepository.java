package aptech.vn.backend.repository;

import aptech.vn.backend.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByCode(String code);
    List<Medicine> findByName(String name);
    List<Medicine> findByNameContaining(String namePattern);
    List<Medicine> findByBrand_Id(Long brandId);
    List<Medicine> findByOrigin(String origin);
    List<Medicine> findByManufacturer(String manufacturer);
}
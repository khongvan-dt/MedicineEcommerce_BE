package aptech.vn.backend.service;

import aptech.vn.backend.entity.DoctorService;
import java.util.List;
import java.util.Optional;

public interface DoctorServiceService {
    DoctorService save(DoctorService doctorService);
    List<DoctorService> findAll();
    Optional<DoctorService> findById(Long id);
    void deleteById(Long id);
    List<DoctorService> findByDoctorId(Long doctorId);
    List<DoctorService> findByServiceId(Long serviceId);
    Optional<DoctorService> findByDoctorIdAndServiceId(Long doctorId, Long serviceId);
}
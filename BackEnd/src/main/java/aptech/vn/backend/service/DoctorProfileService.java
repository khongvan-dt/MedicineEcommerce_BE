package aptech.vn.backend.service;

import aptech.vn.backend.entity.DoctorProfile;
import java.util.List;
import java.util.Optional;

public interface DoctorProfileService {
    DoctorProfile save(DoctorProfile doctorProfile);
    List<DoctorProfile> findAll();
    Optional<DoctorProfile> findById(Long id);
    void deleteById(Long id);
    Optional<DoctorProfile> findByUserId(Long userId);
    List<DoctorProfile> findBySpecialization(String specialization);
    List<DoctorProfile> findByWorkplace(String workplace);
    List<DoctorProfile> findBySpecializationContaining(String specialization);
}
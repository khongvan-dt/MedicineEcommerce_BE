package aptech.vn.backend.service;

import aptech.vn.backend.dto.DoctorServiceDTO;
import java.util.List;
import java.util.Optional;

public interface DoctorServiceService {
    List<DoctorServiceDTO> findAll();
    Optional<DoctorServiceDTO> findById(Long id);
    DoctorServiceDTO save(DoctorServiceDTO doctorServiceDTO);
    void deleteById(Long id);
    List<DoctorServiceDTO> findByDoctorId(Long doctorId);
    List<DoctorServiceDTO> findByServiceId(Long serviceId);
    Optional<DoctorServiceDTO> findByDoctorIdAndServiceId(Long doctorId, Long serviceId);
}
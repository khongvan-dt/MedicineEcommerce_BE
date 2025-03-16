package aptech.vn.backend.service;

import aptech.vn.backend.dto.DoctorServiceDTO;
import java.util.List;
import java.util.Optional;

public interface DoctorServiceService {
    List<DoctorServiceDTO.GetDoctorServiceDto> findAll();
    Optional<DoctorServiceDTO.GetDoctorServiceDto> findById(Long id);
    DoctorServiceDTO.GetDoctorServiceDto save(DoctorServiceDTO.SaveDoctorServiceDto doctorServiceDTO);
    void deleteById(Long id);
    List<DoctorServiceDTO.GetDoctorServiceDto> findByDoctorId(Long doctorId);
    List<DoctorServiceDTO.GetDoctorServiceDto> findByServiceId(Long serviceId);
    Optional<DoctorServiceDTO.GetDoctorServiceDto> findByDoctorIdAndServiceId(Long doctorId, Long serviceId);
}
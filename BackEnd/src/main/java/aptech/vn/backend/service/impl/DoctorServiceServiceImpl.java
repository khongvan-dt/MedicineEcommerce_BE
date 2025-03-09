package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DoctorServiceDTO;
import aptech.vn.backend.entity.DoctorService;
import aptech.vn.backend.mapper.DoctorServiceMapper;
import aptech.vn.backend.repository.DoctorServiceRepository;
import aptech.vn.backend.service.DoctorServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceServiceImpl implements DoctorServiceService {

    private final DoctorServiceRepository doctorServiceRepository;
    private final DoctorServiceMapper doctorServiceMapper;

    @Autowired
    public DoctorServiceServiceImpl(DoctorServiceRepository doctorServiceRepository, DoctorServiceMapper doctorServiceMapper) {
        this.doctorServiceRepository = doctorServiceRepository;
        this.doctorServiceMapper = doctorServiceMapper;
    }

    public List<DoctorServiceDTO> findAll() {
        return doctorServiceRepository.findAll().stream()
                .map(doctorServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DoctorServiceDTO> findById(Long id) {
        return doctorServiceRepository.findById(id)
                .map(doctorServiceMapper::toDto);
    }

    public DoctorServiceDTO save(DoctorServiceDTO doctorServiceDTO) {
        DoctorService doctorService = doctorServiceMapper.toEntity(doctorServiceDTO);
        DoctorService savedDoctorService = doctorServiceRepository.save(doctorService);
        return doctorServiceMapper.toDto(savedDoctorService);
    }

    public void deleteById(Long id) {
        doctorServiceRepository.deleteById(id);
    }

    public List<DoctorServiceDTO> findByDoctorId(Long doctorId) {
        return doctorServiceRepository.findByDoctor_Id(doctorId).stream()
                .map(doctorServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DoctorServiceDTO> findByServiceId(Long serviceId) {
        return doctorServiceRepository.findByService_Id(serviceId).stream()
                .map(doctorServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DoctorServiceDTO> findByDoctorIdAndServiceId(Long doctorId, Long serviceId) {
        return doctorServiceRepository.findByDoctor_IdAndService_Id(doctorId, serviceId)
                .map(doctorServiceMapper::toDto);
    }
}
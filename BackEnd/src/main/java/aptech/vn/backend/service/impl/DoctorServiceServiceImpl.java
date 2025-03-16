package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DoctorServiceDTO;
import aptech.vn.backend.entity.DoctorService;
import aptech.vn.backend.mapper.DoctorServiceMapper;
import aptech.vn.backend.repository.DoctorServiceRepository;
import aptech.vn.backend.service.DoctorServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<DoctorServiceDTO.GetDoctorServiceDto> findAll() {
        return doctorServiceRepository.findAll().stream()
                .map(doctorServiceMapper::toDoctorServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorServiceDTO.GetDoctorServiceDto> findById(Long id) {
        return doctorServiceRepository.findById(id)
                .map(doctorServiceMapper::toDoctorServiceDto);
    }

    @Override
    public DoctorServiceDTO.GetDoctorServiceDto save(DoctorServiceDTO.SaveDoctorServiceDto doctorServiceDTO) {
        DoctorService doctorService = doctorServiceMapper.toDoctorServiceEntity(doctorServiceDTO);
        DoctorService savedDoctorService = doctorServiceRepository.save(doctorService);
        return doctorServiceMapper.toDoctorServiceDto(savedDoctorService);
    }

    @Override
    public void deleteById(Long id) {
        doctorServiceRepository.deleteById(id);
    }

    @Override
    public List<DoctorServiceDTO.GetDoctorServiceDto> findByDoctorId(Long doctorId) {
        return doctorServiceRepository.findByDoctor_Id(doctorId).stream()
                .map(doctorServiceMapper::toDoctorServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorServiceDTO.GetDoctorServiceDto> findByServiceId(Long serviceId) {
        return doctorServiceRepository.findByService_Id(serviceId).stream()
                .map(doctorServiceMapper::toDoctorServiceDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorServiceDTO.GetDoctorServiceDto> findByDoctorIdAndServiceId(Long doctorId, Long serviceId) {
        return doctorServiceRepository.findByDoctor_IdAndService_Id(doctorId, serviceId)
                .map(doctorServiceMapper::toDoctorServiceDto);
    }
}
package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.mapper.DoctorProfileMapper;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DoctorProfileServiceImpl implements DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final DoctorProfileMapper doctorProfileMapper;

    @Autowired
    public DoctorProfileServiceImpl(DoctorProfileRepository doctorProfileRepository, DoctorProfileMapper doctorProfileMapper) {
        this.doctorProfileRepository = doctorProfileRepository;
        this.doctorProfileMapper = doctorProfileMapper;
    }

    @Override
    public List<DoctorProfileDTO> findAll() {
        return doctorProfileRepository.findAll().stream()
                .map(doctorProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorProfileDTO> findById(Long id) {
        return doctorProfileRepository.findById(id)
                .map(doctorProfileMapper::toDto);
    }

    @Override
    public DoctorProfileDTO save(DoctorProfileDTO doctorProfileDTO) {
        DoctorProfile doctorProfile = doctorProfileMapper.toEntity(doctorProfileDTO);
        DoctorProfile savedDoctorProfile = doctorProfileRepository.save(doctorProfile);
        return doctorProfileMapper.toDto(savedDoctorProfile);
    }

    @Override
    public void deleteById(Long id) {
        doctorProfileRepository.deleteById(id);
    }

    @Override
    public Optional<DoctorProfileDTO> findByUserId(Long userId) {
        return doctorProfileRepository.findByUser_Id(userId)
                .map(doctorProfileMapper::toDto);
    }

    @Override
    public List<DoctorProfileDTO> findBySpecializationContaining(String specialization) {
        return doctorProfileRepository.findBySpecializationContaining(specialization).stream()
                .map(doctorProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorProfileDTO> findByWorkplaceContaining(String workplace) {
        return doctorProfileRepository.findByWorkplaceContaining(workplace).stream()
                .map(doctorProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorProfileDTO> findByAccountBalanceGreaterThanEqual(BigDecimal amount) {
        return doctorProfileRepository.findByAccountBalanceGreaterThanEqual(amount).stream()
                .map(doctorProfileMapper::toDto)
                .collect(Collectors.toList());
    }
}
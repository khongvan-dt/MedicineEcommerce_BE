package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.DoctorProfileDTO;
import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.DoctorProfileMapper;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorProfileServiceImpl implements DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final UserRepository userRepository;
    private final DoctorProfileMapper doctorProfileMapper;

    @Autowired
    public DoctorProfileServiceImpl(
            DoctorProfileRepository doctorProfileRepository,
            UserRepository userRepository,
            DoctorProfileMapper doctorProfileMapper) {
        this.doctorProfileRepository = doctorProfileRepository;
        this.userRepository = userRepository;
        this.doctorProfileMapper = doctorProfileMapper;
    }

    @Override
    public List<DoctorProfileDTO.GetDoctorProfileDto> findAll() {
        return doctorProfileRepository.findAll().stream()
                .map(doctorProfileMapper::toGetDoctorProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DoctorProfileDTO.GetDoctorProfileDto> findById(Long id) {
        return doctorProfileRepository.findById(id)
                .map(doctorProfileMapper::toGetDoctorProfileDto);
    }

    @Override
    @Transactional
    public DoctorProfileDTO.GetDoctorProfileDto saveOrUpdate(DoctorProfileDTO.SaveDoctorProfileDto doctorProfileDTO) {
        DoctorProfile doctorProfile;

        if (doctorProfileDTO.getId() == null || doctorProfileDTO.getId() == 0) {
            // INSERT case
            doctorProfile = new DoctorProfile();
            doctorProfile.setCreatedAt(LocalDateTime.now());
            doctorProfile.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<DoctorProfile> existingDoctorProfile = doctorProfileRepository.findById(doctorProfileDTO.getId());
            if (existingDoctorProfile.isEmpty()) {
                throw new RuntimeException("Doctor profile not found with ID: " + doctorProfileDTO.getId());
            }
            doctorProfile = existingDoctorProfile.get();
            doctorProfile.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        User user = userRepository.findById(doctorProfileDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + doctorProfileDTO.getUserId()));
        doctorProfile.setUser(user);

        // Cập nhật các trường khác
        doctorProfile.setExperience(doctorProfileDTO.getExperience());
        doctorProfile.setSpecialization(doctorProfileDTO.getSpecialization());
        doctorProfile.setWorkplace(doctorProfileDTO.getWorkplace());
        doctorProfile.setAccountBalance(doctorProfileDTO.getAccountBalance());

        DoctorProfile savedDoctorProfile = doctorProfileRepository.save(doctorProfile);
        return doctorProfileMapper.toGetDoctorProfileDto(savedDoctorProfile);
    }

    @Override
    public void deleteById(Long id) {
        doctorProfileRepository.deleteById(id);
    }

    @Override
    public Optional<DoctorProfileDTO.GetDoctorProfileDto> findByUserId(Long userId) {
        return doctorProfileRepository.findByUser_Id(userId)
                .map(doctorProfileMapper::toGetDoctorProfileDto);
    }

    @Override
    public List<DoctorProfileDTO.GetDoctorProfileDto> findBySpecializationContaining(String specialization) {
        return doctorProfileRepository.findBySpecializationContaining(specialization).stream()
                .map(doctorProfileMapper::toGetDoctorProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorProfileDTO.GetDoctorProfileDto> findByWorkplaceContaining(String workplace) {
        return doctorProfileRepository.findByWorkplaceContaining(workplace).stream()
                .map(doctorProfileMapper::toGetDoctorProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorProfileDTO.GetDoctorProfileDto> findByAccountBalanceGreaterThanEqual(BigDecimal amount) {
        return doctorProfileRepository.findByAccountBalanceGreaterThanEqual(amount).stream()
                .map(doctorProfileMapper::toGetDoctorProfileDto)
                .collect(Collectors.toList());
    }
}
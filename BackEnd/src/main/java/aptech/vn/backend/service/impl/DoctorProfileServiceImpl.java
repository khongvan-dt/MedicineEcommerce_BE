package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.DoctorProfile;
import aptech.vn.backend.repository.DoctorProfileRepository;
import aptech.vn.backend.service.DoctorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorProfileServiceImpl implements DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;

    @Autowired
    public DoctorProfileServiceImpl(DoctorProfileRepository doctorProfileRepository) {
        this.doctorProfileRepository = doctorProfileRepository;
    }

    @Override
    public DoctorProfile save(DoctorProfile doctorProfile) {
        return doctorProfileRepository.save(doctorProfile);
    }

    @Override
    public List<DoctorProfile> findAll() {
        return doctorProfileRepository.findAll();
    }

    @Override
    public Optional<DoctorProfile> findById(Long id) {
        return doctorProfileRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        doctorProfileRepository.deleteById(id);
    }

    @Override
    public Optional<DoctorProfile> findByUserId(Long userId) {
        return doctorProfileRepository.findByUserId(userId);
    }

    @Override
    public List<DoctorProfile> findBySpecialization(String specialization) {
        return doctorProfileRepository.findBySpecialization(specialization);
    }

    @Override
    public List<DoctorProfile> findByWorkplace(String workplace) {
        return doctorProfileRepository.findByWorkplace(workplace);
    }

    @Override
    public List<DoctorProfile> findBySpecializationContaining(String specialization) {
        return doctorProfileRepository.findBySpecializationContaining(specialization);
    }
}
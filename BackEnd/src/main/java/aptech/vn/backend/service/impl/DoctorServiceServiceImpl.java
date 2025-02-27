package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.DoctorService;
import aptech.vn.backend.repository.DoctorServiceRepository;
import aptech.vn.backend.service.DoctorServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceServiceImpl implements DoctorServiceService {

    private final DoctorServiceRepository doctorServiceRepository;

    @Autowired
    public DoctorServiceServiceImpl(DoctorServiceRepository doctorServiceRepository) {
        this.doctorServiceRepository = doctorServiceRepository;
    }

    @Override
    public DoctorService save(DoctorService doctorService) {
        return doctorServiceRepository.save(doctorService);
    }

    @Override
    public List<DoctorService> findAll() {
        return doctorServiceRepository.findAll();
    }

    @Override
    public Optional<DoctorService> findById(Long id) {
        return doctorServiceRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        doctorServiceRepository.deleteById(id);
    }

    @Override
    public List<DoctorService> findByDoctorId(Long doctorId) {
        return doctorServiceRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<DoctorService> findByServiceId(Long serviceId) {
        return doctorServiceRepository.findByServiceId(serviceId);
    }

    @Override
    public Optional<DoctorService> findByDoctorIdAndServiceId(Long doctorId, Long serviceId) {
        return doctorServiceRepository.findByDoctorIdAndServiceId(doctorId, serviceId);
    }
}
package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.Consultation;
import aptech.vn.backend.entity.ConsultationStatus;
import aptech.vn.backend.repository.ConsultationRepository;
import aptech.vn.backend.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Consultation save(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return consultationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public List<Consultation> findByPatientId(Long patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    @Override
    public List<Consultation> findByDoctorId(Long doctorId) {
        return consultationRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Consultation> findByStatus(ConsultationStatus status) {
        return consultationRepository.findByStatus(status);
    }

    @Override
    public List<Consultation> findByPatientIdAndStatus(Long patientId, ConsultationStatus status) {
        return consultationRepository.findByPatientIdAndStatus(patientId, status);
    }

    @Override
    public List<Consultation> findByDoctorIdAndStatus(Long doctorId, ConsultationStatus status) {
        return consultationRepository.findByDoctorIdAndStatus(doctorId, status);
    }
}
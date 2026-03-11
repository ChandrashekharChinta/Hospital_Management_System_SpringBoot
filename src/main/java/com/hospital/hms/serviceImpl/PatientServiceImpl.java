package com.hospital.hms.serviceImpl;

import com.hospital.hms.exception.DuplicateResourceException;
import com.hospital.hms.exception.ResourceNotFoundException;
import com.hospital.hms.model.Patient;
import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new DuplicateResourceException("Patient with email " + patient.getEmail() + " already exists");
        }
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);

        // Check if email is being changed to one that already exists
        if (!existing.getEmail().equals(updatedPatient.getEmail())
                && patientRepository.existsByEmail(updatedPatient.getEmail())) {
            throw new DuplicateResourceException("Email " + updatedPatient.getEmail() + " is already in use");
        }

        existing.setFirstName(updatedPatient.getFirstName());
        existing.setLastName(updatedPatient.getLastName());
        existing.setEmail(updatedPatient.getEmail());
        existing.setPhone(updatedPatient.getPhone());
        existing.setAge(updatedPatient.getAge());
        existing.setGender(updatedPatient.getGender());
        existing.setAddress(updatedPatient.getAddress());
        existing.setBloodGroup(updatedPatient.getBloodGroup());

        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        Patient existing = getPatientById(id);
        patientRepository.delete(existing);
    }
}

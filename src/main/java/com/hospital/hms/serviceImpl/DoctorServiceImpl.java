package com.hospital.hms.serviceImpl;

import com.hospital.hms.exception.DuplicateResourceException;
import com.hospital.hms.exception.ResourceNotFoundException;
import com.hospital.hms.model.Doctor;
import com.hospital.hms.repository.DoctorRepository;
import com.hospital.hms.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor createDoctor(Doctor doctor) {
        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new DuplicateResourceException("Doctor with email " + doctor.getEmail() + " already exists");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);

        if (!existing.getEmail().equals(updatedDoctor.getEmail())
                && doctorRepository.existsByEmail(updatedDoctor.getEmail())) {
            throw new DuplicateResourceException("Email " + updatedDoctor.getEmail() + " is already in use");
        }

        existing.setFirstName(updatedDoctor.getFirstName());
        existing.setLastName(updatedDoctor.getLastName());
        existing.setEmail(updatedDoctor.getEmail());
        existing.setPhone(updatedDoctor.getPhone());
        existing.setSpecialization(updatedDoctor.getSpecialization());
        existing.setDepartment(updatedDoctor.getDepartment());
        existing.setExperienceYears(updatedDoctor.getExperienceYears());

        return doctorRepository.save(existing);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor existing = getDoctorById(id);
        doctorRepository.delete(existing);
    }
}

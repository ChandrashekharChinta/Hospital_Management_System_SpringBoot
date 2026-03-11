package com.hospital.hms.serviceImpl;

import com.hospital.hms.exception.ResourceNotFoundException;
import com.hospital.hms.model.Doctor;
import com.hospital.hms.model.MedicalRecord;
import com.hospital.hms.model.Patient;
import com.hospital.hms.repository.DoctorRepository;
import com.hospital.hms.repository.MedicalRecordRepository;
import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        Patient patient = patientRepository.findById(medicalRecord.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with id: " + medicalRecord.getPatient().getId()));

        Doctor doctor = doctorRepository.findById(medicalRecord.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with id: " + medicalRecord.getDoctor().getId()));

        medicalRecord.setPatient(patient);
        medicalRecord.setDoctor(doctor);

        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with id: " + id));
    }

    @Override
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord updatedRecord) {
        MedicalRecord existing = getMedicalRecordById(id);

        Patient patient = patientRepository.findById(updatedRecord.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with id: " + updatedRecord.getPatient().getId()));

        Doctor doctor = doctorRepository.findById(updatedRecord.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with id: " + updatedRecord.getDoctor().getId()));

        existing.setPatient(patient);
        existing.setDoctor(doctor);
        existing.setRecordDate(updatedRecord.getRecordDate());
        existing.setDiagnosis(updatedRecord.getDiagnosis());
        existing.setPrescription(updatedRecord.getPrescription());
        existing.setNotes(updatedRecord.getNotes());

        return medicalRecordRepository.save(existing);
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        MedicalRecord existing = getMedicalRecordById(id);
        medicalRecordRepository.delete(existing);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }
}

package com.hospital.hms.service;

import com.hospital.hms.model.Patient;
import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Long id, Patient patient);
    void deletePatient(Long id);
}

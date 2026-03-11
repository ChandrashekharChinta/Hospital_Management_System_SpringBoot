package com.hospital.hms.service;

import com.hospital.hms.model.MedicalRecord;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> getAllMedicalRecords();
    MedicalRecord getMedicalRecordById(Long id);
    MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord);
    void deleteMedicalRecord(Long id);
    List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId);
    List<MedicalRecord> getMedicalRecordsByDoctorId(Long doctorId);
}

package com.hospital.hms.controller;

import com.hospital.hms.model.MedicalRecord;
import com.hospital.hms.service.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // POST /api/medical-records - Create a new medical record
    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord created = medicalRecordService.createMedicalRecord(medicalRecord);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET /api/medical-records - Get all medical records
    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecords());
    }

    // GET /api/medical-records/{id} - Get medical record by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordById(id));
    }

    // GET /api/medical-records/patient/{patientId} - Get records by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getRecordsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByPatientId(patientId));
    }

    // GET /api/medical-records/doctor/{doctorId} - Get records by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecord>> getRecordsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordsByDoctorId(doctorId));
    }

    // PUT /api/medical-records/{id} - Update medical record
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id,
                                                              @Valid @RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(id, medicalRecord));
    }

    // DELETE /api/medical-records/{id} - Delete medical record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.ok("Medical record deleted successfully");
    }
}

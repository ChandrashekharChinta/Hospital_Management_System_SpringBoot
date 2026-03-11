package com.hospital.hms.serviceImpl;

import com.hospital.hms.exception.ResourceNotFoundException;
import com.hospital.hms.model.Appointment;
import com.hospital.hms.model.Doctor;
import com.hospital.hms.model.Patient;
import com.hospital.hms.repository.AppointmentRepository;
import com.hospital.hms.repository.DoctorRepository;
import com.hospital.hms.repository.PatientRepository;
import com.hospital.hms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // Validate patient exists
        Patient patient = patientRepository.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with id: " + appointment.getPatient().getId()));

        // Validate doctor exists
        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with id: " + appointment.getDoctor().getId()));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existing = getAppointmentById(id);

        // Validate patient exists
        Patient patient = patientRepository.findById(updatedAppointment.getPatient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Patient not found with id: " + updatedAppointment.getPatient().getId()));

        // Validate doctor exists
        Doctor doctor = doctorRepository.findById(updatedAppointment.getDoctor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Doctor not found with id: " + updatedAppointment.getDoctor().getId()));

        existing.setPatient(patient);
        existing.setDoctor(doctor);
        existing.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existing.setAppointmentTime(updatedAppointment.getAppointmentTime());
        existing.setReason(updatedAppointment.getReason());
        existing.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(existing);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment existing = getAppointmentById(id);
        appointmentRepository.delete(existing);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}

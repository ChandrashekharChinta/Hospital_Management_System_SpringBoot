package com.hospital.hms.service;

import com.hospital.hms.model.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
    List<Appointment> getAppointmentsByPatientId(Long patientId);
    List<Appointment> getAppointmentsByDoctorId(Long doctorId);
}

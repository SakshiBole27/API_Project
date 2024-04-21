package controller;

import Entity.Appointment;
import Entity.Doctor;
import Service.AppointmentRequest;
import Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking/doctors")
public class PatientAppointmentController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @GetMapping("/{doctorId}/availability")
    public List<String> getDoctorAvailability(@PathVariable Long doctorId, @RequestParam String date) {
        return doctorService.getDoctorAvailability(doctorId, date);
    }

    @PostMapping("/{doctorId}/appointments")
    public Appointment bookAppointment(@PathVariable Long doctorId, @RequestBody AppointmentRequest appointmentRequest) {
        return doctorService.bookAppointment(doctorId, appointmentRequest);
    }
}

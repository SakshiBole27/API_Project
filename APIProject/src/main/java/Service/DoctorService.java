package Service;

import Entity.Appointment;
import Entity.Doctor;
import Repository.AppointmentRepository;
import Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    public List<String> getDoctorAvailability(Long doctorId, String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return null;
        }
        return List.of("18:00", "18:30", "19:00", "19:30"); // Example availability
    }

    public Appointment bookAppointment(Long doctorId, AppointmentRequest appointmentRequest) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctorId);
        appointment.setPatientName(appointmentRequest.getPatientName());
        appointment.setDate(LocalDate.parse(appointmentRequest.getDate()));
        appointment.setTime(LocalTime.parse(appointmentRequest.getTime()));
        return appointmentRepository.save(appointment);
    }
}

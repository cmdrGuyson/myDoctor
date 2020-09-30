package Model;

public class Appointment {
    
    private int appointmentID, availabilitySlotID;
    private String email, status, patient;
    private AvailabilitySlot availabilitySlot;
    
    public Appointment(int availabilitySlotID, String email) {
        this.availabilitySlotID = availabilitySlotID;
        this.email = email;
        this.status = "Active";
    }

    public Appointment(int appointmentID, int availabilitySlotID, String email, String status) {
        this.appointmentID = appointmentID;
        this.availabilitySlotID = availabilitySlotID;
        this.email = email;
        this.status = status;
    }
    
    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getAvailabilitySlotID() {
        return availabilitySlotID;
    }

    public void setAvailabilitySlotID(int availabilitySlotID) {
        this.availabilitySlotID = availabilitySlotID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AvailabilitySlot getAvailabilitySlot() {
        return availabilitySlot;
    }

    public void setAvailabilitySlot(AvailabilitySlot availabilitySlot) {
        this.availabilitySlot = availabilitySlot;
    }
    
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}

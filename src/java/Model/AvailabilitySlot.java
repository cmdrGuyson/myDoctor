package Model;

public class AvailabilitySlot {

    private int availabilitySlotID ,doctorID, hospitalID, appointments;
    private String date, time, doctorName, hospitalName, status;
    
    public AvailabilitySlot(int availabilitySlotID, int doctorID, int hospitalID, String date, String time, String status, int appointments) {
        this.availabilitySlotID = availabilitySlotID;
        this.doctorID = doctorID;
        this.hospitalID = hospitalID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.appointments = appointments;
    }

    public AvailabilitySlot(int doctorID, int hospitalID, String date, String time) {
        this.doctorID = doctorID;
        this.hospitalID = hospitalID;
        this.date = date;
        this.time = time;
        this.status = "Active";
        this.appointments = 0;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }
    
    public int getAppointments() {
        return appointments;
    }

    public void setAppointments(int appointments) {
        this.appointments = appointments;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    
    public int getAvailabilitySlotID() {
        return availabilitySlotID;
    }

    public void setAvailabilitySlotID(int availabilitySlotID) {
        this.availabilitySlotID = availabilitySlotID;
    }
    
    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}

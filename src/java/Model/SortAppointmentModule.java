package Model;

import java.util.List;

public class SortAppointmentModule {
    
    private SortAppointmentStratergy sortAppointmentStratergy;

    //Set the sorting stratergy
    public void setSortAppointmentStratergy(SortAppointmentStratergy sortAppointmentStratergy) {
        this.sortAppointmentStratergy = sortAppointmentStratergy;
    }
    
    //Sort appointments using selected stratergy
    public List<Appointment> sortAppointments(List<Appointment> appointments) {
        return sortAppointmentStratergy.sort(appointments);
    }
    
}

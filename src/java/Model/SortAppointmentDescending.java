package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//Used to sort appointments in descending order of date
public class SortAppointmentDescending implements SortAppointmentStratergy {
    
    @Override
    public List<Appointment> sort(List<Appointment> appointments) {
        Collections.sort(appointments, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment a, Appointment b) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                Date a_date = null;
                Date b_date = null;
                
                try {
                    //Convert string to Date objects to be compared
                    a_date = dateFormat.parse(a.getAvailabilitySlot().getDate());
                    b_date = dateFormat.parse(b.getAvailabilitySlot().getDate());
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
                
                //Appointments are compared by number of miliseconds in date and sorted in descending order
                return (int) (b_date.getTime() - a_date.getTime());
            }
        });

        return appointments;
    }
    
}

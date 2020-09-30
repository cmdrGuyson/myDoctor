package Model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//This class is a subcalss of the TemplateSorterDoctor class and helps in sorting specialized doctors in ascending order of first name;
public class SorterSpecializedDoctorAscending extends TemplateSorterDoctor {

    public SorterSpecializedDoctorAscending(Connection connection) {
        super(connection);
    }

    //The abstract method sort() of the parent template class will be overriden to support ascending sort
    @Override
    public void sort() {

        List<Doctor> unsortedDocs = this.doctors;

        Collections.sort(unsortedDocs, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor a, Doctor b) {
                //Compare first name for doctors and re-arrange list
                return a.getFirstName().compareTo(b.getFirstName());
            }
        });
        
        this.doctors = unsortedDocs;

    }

    //Only include specialized doctors
    @Override
    public void filter(String type) {

        List<Doctor> newDocs = new ArrayList<>();
        
        for (Doctor doctor : doctors) {
            //If doctor's specialization matches user searched specialization add to new doctor list
            if (doctor.getSpecialization()!=null && doctor.getSpecialization().equals(type)) {
                newDocs.add(doctor);
            }
        }
        
        //Set new doctor list as the doctor list
        doctors = newDocs;
    }
    
}

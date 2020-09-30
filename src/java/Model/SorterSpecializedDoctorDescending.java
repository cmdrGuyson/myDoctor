package Model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//This class is a subcalss of the TemplateSorterDoctor class and helps in sorting specialized doctors in descending order of first name;
public class SorterSpecializedDoctorDescending extends TemplateSorterDoctor {

    public SorterSpecializedDoctorDescending(Connection connection) {
        super(connection);
    }

    //Include only specialized doctors
    @Override
    public void filter(String type) {

        List<Doctor> newDocs = new ArrayList<>();
        
        for (Doctor doctor : doctors) {
            //If doctor's specialization matches user searched specialization add to new doctor list
             if (doctor.getSpecialization()!=null && doctor.getSpecialization().equals(type)) {
                newDocs.add(doctor);
            }
        }
        
        doctors = newDocs;
    }

    //The abstract method sort() of the parent template class will be overriden to support descending sort
    @Override
    public void sort() {

        List<Doctor> unsortedDocs = this.doctors;

        Collections.sort(unsortedDocs, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor a, Doctor b) {
                return a.getFirstName().compareTo(b.getFirstName());
            }
        });

        //Sort in descending order
        Collections.reverse(unsortedDocs);

        this.doctors = unsortedDocs;
    }    
}

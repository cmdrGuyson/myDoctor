package Model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//This class is a subcalss of the TemplateSorterDoctor class and helps in sorting general doctors in descending order of first name
public class SorterDoctorDescending extends TemplateSorterDoctor {

    public SorterDoctorDescending(Connection connection) {
        super(connection);
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

    //Only include general doctors
    @Override
    public void filter(String type) {
        boolean childDoc = false;
        
        //Check if user want's to get children's doctors
        if (type.equals("childDoctor")) {
            childDoc = true;
        }

        List<Doctor> newDocs = new ArrayList<>();
        
        for (Doctor doctor : doctors) {
            //If doctor is a child doctor or isnt one according to user specification add to new list
            if ((doctor.isChildDoctor() == childDoc) && doctor.getSpecialization() == null) {
                newDocs.add(doctor);
            }
        }
        
        doctors = newDocs;
    }
}

package Model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//This class is a subcalss of the TemplateSorterDoctor class and helps in sorting general doctors in ascending order of first name;
public class SorterDoctorAscending extends TemplateSorterDoctor {

    public SorterDoctorAscending(Connection connection) {
        super(connection);
    }

    //The abstract method sort() of the parent template class will be overriden to support ascending sort
    @Override
    public void sort() {

        List<Doctor> unsortedDocs = this.doctors;
        
        Collections.sort(unsortedDocs, new Comparator<Doctor>() {
            @Override
            //Compare first name for doctors and re-arrange list
            public int compare(Doctor a, Doctor b) {
                return a.getFirstName().compareTo(b.getFirstName());
            }
        });

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

package Model;

//Only instantiated with the help of DoctorFactory

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralDoctor extends Doctor {
    
    private boolean childDoctor;
    
    public GeneralDoctor() {
        childDoctor = false;
    }

    @Override
    public boolean isChildDoctor() {
        return childDoctor;
    }

    @Override
    public void setChildDoctor(boolean childDoctor) {
        this.childDoctor = childDoctor;
    }
    
    @Override
    public String getSpecialization() {
        return null;
    }

    @Override
    public void setSpecialization(String specialization) {
        try {
            throw new Exception("Cannot set speciality for General Doctor");
        } catch (Exception ex) {
            Logger.getLogger(GeneralDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

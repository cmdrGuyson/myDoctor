package Model;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpecializedDoctor extends GeneralDoctor {
    
    private String specialization;

    @Override
    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    @Override
    public boolean isChildDoctor() {
        return false;
    }

    @Override
    public void setChildDoctor(boolean childDoctor) {
        try {
            throw new Exception("Cannot set childDoctor for Specialized Doctor");
        } catch (Exception ex) {
            Logger.getLogger(GeneralDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Class implemented using Template design pattern to help in sorting Doctors*/
public abstract class TemplateSorterDoctor {
    
    private final Connection connection;
    
    //List of doctors
    protected List<Doctor> doctors;
    
    public TemplateSorterDoctor(Connection connection) {
        this.connection = connection;
    }
    
    //Template method of the class
    public List<Doctor> sortDoctors(String type) {
        getDoctors();
        filter(type);
        sort();
        return doctors;
    }
    
    //Method to get doctors of a certain specialization
    public void getDoctors() {
        
        List<Doctor> docs = new ArrayList<>();
        
        try {

            /* Statement to be executed to get all doctors of a certain specialization */
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Doctor;");
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {
                Doctor doctor;
                
                //For each recieved result create a new doctor object and add to array
                
                //if doctor is a specialist
                if (results.getString("specialization") != null){
                    doctor = DoctorFactory.createDoctor("special");
                    doctor.setDoctorID(results.getInt("doctorID"));
                    doctor.setFirstName(results.getString("firstName"));
                    doctor.setLastName(results.getString("lastName"));
                    doctor.setContactNumber(results.getString("contactNumber"));
                    doctor.setSpecialization(results.getString("specialization"));
                    doctor.setImageURL(results.getString("imageURL"));
                }else{
                    doctor = DoctorFactory.createDoctor("general");
                    doctor.setDoctorID(results.getInt("doctorID"));
                    doctor.setFirstName(results.getString("firstName"));
                    doctor.setLastName(results.getString("lastName"));
                    doctor.setContactNumber(results.getString("contactNumber"));
                    boolean isChildDoc = results.getString("childDoctor").equals("true");
                    doctor.setChildDoctor(isChildDoc);
                    
                    doctor.setImageURL(results.getString("imageURL"));
                }
                
                docs.add(doctor);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        this.doctors = docs;
    }
    
    //This method will be overriden in subclasses depending on the type of doctor
    public abstract void filter(String type);
    
    //This method will be overriden in subclasses depending on the nature of the sorting mechanism
    public abstract void sort();
}

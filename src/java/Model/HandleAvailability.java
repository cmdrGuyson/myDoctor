package Model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HandleAvailability {

    private final Connection connection;

    public HandleAvailability() {
        Database_Connection database_Connection = Database_Connection.getDBConnection();
        connection = database_Connection.connectToDatabase();
    }

    /*Method to add an availability slot for a doctor in a specific hospital to the system*/
    public void addAvailabilitySlot(AvailabilitySlot slot) throws MySQLIntegrityConstraintViolationException {

        try {

            PreparedStatement pre_statement = connection.prepareStatement("SELECT * FROM AvailabilitySlot WHERE doctorID = ? AND hospitalID = ? AND slotDate = ? AND slotTime = ?");

            pre_statement.setInt(1, slot.getDoctorID());
            pre_statement.setInt(2, slot.getHospitalID());
            pre_statement.setString(3, slot.getDate());
            pre_statement.setString(4, slot.getTime());

            ResultSet results = pre_statement.executeQuery();

            //If the same availability slot exists
            while (results.next()) {
                throw new MySQLIntegrityConstraintViolationException();
            }

            /* Statement to be executed to add an availability slot */
            PreparedStatement statement = connection.prepareStatement("INSERT INTO AvailabilitySlot (doctorID, hospitalID, slotDate, slotTime, status, appointments) VALUES (?,?,?,?,?,?);");


            /* customize statement according to recieved object */
            statement.setInt(1, slot.getDoctorID());
            statement.setInt(2, slot.getHospitalID());
            statement.setString(3, slot.getDate());
            statement.setString(4, slot.getTime());
            statement.setString(5, slot.getStatus());
            statement.setInt(6, slot.getAppointments());
            

            statement.execute();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            /* This exception will be thrown if same availability slot is added to the system. */
            throw e;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*METHOD TO GET ALL AVAILABILITY SLOTS*/
    public List<AvailabilitySlot> getAllAvailabilitySlots() {
        List<AvailabilitySlot> slots = new ArrayList<>();

        try {
            //SQL statement to be executed to get all appointment slots from database
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AvailabilitySlot;");
            ResultSet results = statement.executeQuery();
            
            //ResultSet is sent to a utility function
            slots.addAll(utilGetSlots(results));
            
        } catch (SQLException e) {
            System.out.println(e);
        }

        return slots;
    }
    
    /*Get availability slots of a certain doctor for channelling*/
    public List<AvailabilitySlot> getAllAvailabilitySlots(int doctorID) {
        List<AvailabilitySlot> slots = new ArrayList<>();

        try {
            //Get current date and time
            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            
            //Statement to get all availability slots when doctorID is given (will only retrieve availability slots of a future date and having less than 5 appointments)
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AvailabilitySlot WHERE DoctorID = ? AND slotDate > ? AND appointments < 5;");
            statement.setInt(1, doctorID);
            statement.setDate(2, date);
            ResultSet results = statement.executeQuery();

            slots.addAll(utilGetSlots(results));
            
        } catch (SQLException e) {
            System.out.println(e);
        }

        return slots;
    }
    

    
    //Utility function that returns list of availability slots when SQL result set is provided
    private List<AvailabilitySlot> utilGetSlots(ResultSet results) {

        List<AvailabilitySlot> slots = new ArrayList<>();

        try {
            while (results.next()) {

                AvailabilitySlot slot = new AvailabilitySlot(results.getInt("availabilitySlotID"), results.getInt("doctorID"), results.getInt("hospitalID"), results.getString("slotDate"), results.getString("slotTime"), results.getString("status"), results.getInt("appointments"));

                //Get info on doctor
                PreparedStatement doc_statement = connection.prepareStatement("SELECT firstName, lastName FROM Doctor WHERE doctorID = ?");
                doc_statement.setInt(1, slot.getDoctorID());

                ResultSet doc_results = doc_statement.executeQuery();

                while (doc_results.next()) {
                    slot.setDoctorName(doc_results.getString("firstName") + " " + doc_results.getString("lastName"));
                    break;
                }

                //Get info on hospital
                PreparedStatement hospital_statement = connection.prepareStatement("SELECT name FROM Hospital WHERE hospitalID = ?");
                hospital_statement.setInt(1, slot.getHospitalID());

                ResultSet hospital_results = hospital_statement.executeQuery();

                while (hospital_results.next()) {
                    slot.setHospitalName(hospital_results.getString("name"));
                    break;
                }
                slots.add(slot);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return slots;

    }
    
    //Remove availability lots
    public void removeAvailabilitySlot(int slotID) throws MySQLIntegrityConstraintViolationException {

        try {

            /* Statement to be executed to remove an availability slot from the system */
            PreparedStatement statement = connection.prepareStatement("DELETE FROM AvailabilitySlot WHERE availabilitySlotID = ?;");
            statement.setInt(1, slotID);

            statement.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            //This error will be thrown if availability slot has any appointments linked to it
            throw e;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //Get information on a single availability slot
    public AvailabilitySlot getAvailabilitySlot(int slotID) {
        
        AvailabilitySlot slot = null;
        
        try{
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM AvailabilitySlot WHERE availabilitySlotID = ?;");
            statement.setInt(1, slotID);
            
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {

                slot = new AvailabilitySlot(results.getInt("availabilitySlotID"), results.getInt("doctorID"), results.getInt("hospitalID"), results.getString("slotDate"), results.getString("slotTime"), results.getString("status"), results.getInt("appointments"));

                //Get info on doctor
                PreparedStatement doc_statement = connection.prepareStatement("SELECT firstName, lastName FROM Doctor WHERE doctorID = ?");
                doc_statement.setInt(1, slot.getDoctorID());

                ResultSet doc_results = doc_statement.executeQuery();

                while (doc_results.next()) {
                    slot.setDoctorName(doc_results.getString("firstName") + " " + doc_results.getString("lastName"));
                    break;
                }

                //Get info on hospital
                PreparedStatement hospital_statement = connection.prepareStatement("SELECT name FROM Hospital WHERE hospitalID = ?");
                hospital_statement.setInt(1, slot.getHospitalID());

                ResultSet hospital_results = hospital_statement.executeQuery();

                while (hospital_results.next()) {
                    slot.setHospitalName(hospital_results.getString("name"));
                    break;
                }
                
                break;
            }
            
        }catch (SQLException e) {
            System.out.println(e);
        }
        
        return slot;
    }
}

package Model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;
import java.util.List;
import java.util.TreeSet;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class HandleDoctor {

    private final Connection connection;

    public HandleDoctor() {
        Database_Connection database_Connection = Database_Connection.getDBConnection();
        connection = database_Connection.connectToDatabase();
    }

    /*Method to add doctors into the system*/
    public void addDoctor(Doctor doctor) {

        try {

            /* Statement to be executed to add a doctor */
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Doctor (firstName, lastName, specialization, contactNumber, imageURL, childDoctor) VALUES (?,?,?,?,?,?);");

            /* customize statement according to recieved doctor object */
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());

            if (doctor.getSpecialization() != null) {
                //Doctor is a specialized doctor
                statement.setString(3, doctor.getSpecialization());
                statement.setString(6, null);
            } else {
                // Doctor is a general doctor
                statement.setString(3, null);
                statement.setString(6, String.valueOf(doctor.isChildDoctor()));
            }

            statement.setString(4, doctor.getContactNumber());
            statement.setString(5, doctor.getImageURL());

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*Method to get all doctors in the system*/
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        try {

            /* Statement to be executed to get all doctors of the system */
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Doctor;");
            ResultSet results = statement.executeQuery();

            //Utitlity method is used to make code more resuable and follow DRY principles
            doctors.addAll(utilGetDoc(results));

        } catch (SQLException e) {
            System.out.println(e);
        }

        return doctors;
    }

    /*Method to sort doctors*/
    public List<Doctor> sortDoctors(String type, String order) {

        //If user is filtering for General doctors or General doctors who are also child doctors in ascending order
        if (order.equals("Ascending") && (type.equals("general") || type.equals("childDoctor"))) {
            TemplateSorterDoctor sorter = new SorterDoctorAscending(connection);
            return sorter.sortDoctors(type);

        } //If user is filtering for General doctors or General doctors who are also child doctors in descending order
        else if (order.equals("Descending") && (type.equals("general") || type.equals("childDoctor"))) {
            TemplateSorterDoctor sorter = new SorterDoctorDescending(connection);
            return sorter.sortDoctors(type);

        }//If user wants to filter specialized doctors in ascending order     
        else if (order.equals("Ascending")) {
            TemplateSorterDoctor sorter = new SorterSpecializedDoctorAscending(connection);
            return sorter.sortDoctors(type);

        }//If user wants to filter specialized doctors in descending order   
        else {
            TemplateSorterDoctor sorter = new SorterSpecializedDoctorDescending(connection);
            return sorter.sortDoctors(type);
        }
    }

    /*Method to search for doctors using search key*/
    public List<Doctor> searchDoctor(String searchString) {

        List<Doctor> doctors = new ArrayList<>();

        //searchString is broken into keywords by whitespaces.
        String[] keywords = searchString.trim().split("\\s+");

        //creating a REGEXP that can be used to query.
        String searchExpression = String.join("|", keywords);

        try {

            //Searching through firstName and find doctors
            PreparedStatement statementA = connection.prepareStatement("SELECT * FROM Doctor WHERE firstName REGEXP ?;");
            statementA.setString(1, searchExpression);

            ResultSet resultsA = statementA.executeQuery();

            doctors.addAll(utilGetDoc(resultsA));

            //Searching through lastName and find doctors
            PreparedStatement statementB = connection.prepareStatement("SELECT * FROM Doctor WHERE lastName REGEXP ?;");
            statementB.setString(1, searchExpression);

            ResultSet resultsB = statementB.executeQuery();

            doctors.addAll(utilGetDoc(resultsB));

            //Removing duplicates from list by comparing doctorID and creating a tree set
            List<Doctor> unique = doctors.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Doctor::getDoctorID))), ArrayList::new));

            doctors = unique;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return doctors;
    }

    /*Method to remove doctors from system*/
    public void removeDoctor(int doctorID) throws MySQLIntegrityConstraintViolationException {

        try {

            /* Statement to be executed to remove a doctor from the system */
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Doctor WHERE doctorID = ?;");
            statement.setInt(1, doctorID);

            statement.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            throw e;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Utility function to get a doctor list when a result set is given
    private List<Doctor> utilGetDoc(ResultSet results) {
        List<Doctor> doctors = new ArrayList<>();

        try {
            while (results.next()) {
                Doctor doctor;

                //For each recieved result create a new doctor object and add to array
                
                //if doctor is a specialist
                if (results.getString("specialization") != null) {
                    
                    //Use DoctorFactory to create a specialized doctor
                    doctor = DoctorFactory.createDoctor("special");
                    doctor.setDoctorID(results.getInt("doctorID"));
                    doctor.setFirstName(results.getString("firstName"));
                    doctor.setLastName(results.getString("lastName"));
                    doctor.setContactNumber(results.getString("contactNumber"));
                    doctor.setSpecialization(results.getString("specialization"));
                    doctor.setImageURL(results.getString("imageURL"));
                    
                //If doctor is a general doctor    
                } else {
                    
                    //Use doctor factory to create a general doctor
                    doctor = DoctorFactory.createDoctor("general");
                    doctor.setDoctorID(results.getInt("doctorID"));
                    doctor.setFirstName(results.getString("firstName"));
                    doctor.setContactNumber(results.getString("contactNumber"));
                    doctor.setLastName(results.getString("lastName"));

                    //Assign boolean for child doctor
                    boolean isChildDoc = results.getString("childDoctor").equals("true");
                    doctor.setChildDoctor(isChildDoc);

                    doctor.setImageURL(results.getString("imageURL"));
                }
                
                //Add to doctors list to be returned
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return doctors;
    }
}

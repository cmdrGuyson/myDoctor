package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandleAppointment {

    private final Connection connection;

    public HandleAppointment() {
        Database_Connection database_Connection = Database_Connection.getDBConnection();
        connection = database_Connection.connectToDatabase();
    }

    //Method to add an appointment
    public void addAppointment(Appointment appointment) {
        try {

            /* Statement to be executed to add an appointment */
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Appointment (availabilitySlotID, email, status) VALUES (?,?,?)");
            statement.setInt(1, appointment.getAvailabilitySlotID());
            statement.setString(2, appointment.getEmail());
            statement.setString(3, appointment.getStatus());

            statement.execute();

            /*Statement to increase appointment count of slot */
            PreparedStatement statementSlot = connection.prepareStatement("UPDATE AvailabilitySlot SET appointments = appointments + 1 WHERE availabilitySlotID = ?;");
            statementSlot.setInt(1, appointment.getAvailabilitySlotID());

            statementSlot.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //Method to get all appointments of a user
    public List<Appointment> getAppointments(String email) {

        List<Appointment> appointments = new ArrayList<>();

        try {
            
            //Statement to be executed to get appointments made by a specific user
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment WHERE email = ?;");
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();
            
            //Get list of appointments from result set using utility function
            appointments.addAll(utilGetAppointments(results));

        } catch (SQLException e) {
            System.out.println(e);
        }

        return appointments;

    }
    
    //Method to get all appointments
    public List<Appointment> getAppointments() {

        List<Appointment> appointments = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment");

            ResultSet results = statement.executeQuery();
            
            appointments.addAll(utilGetAppointments(results));

        } catch (SQLException e) {
            System.out.println(e);
        }

        return appointments;

    }

    //Utility function to get appointments list when ResultSet is given
    private List<Appointment> utilGetAppointments(ResultSet results) {

        List<Appointment> appointments = new ArrayList<>();

        try {

            while(results.next()){
                
                Appointment appointment = new Appointment(results.getInt("appointmentID"), results.getInt("availabilitySlotID"), results.getString("email"), results.getString("status"));
                
                HandleAvailability handleAvailability = new HandleAvailability();
                
                //Get information on availability slot appointment was made for
                appointment.setAvailabilitySlot(handleAvailability.getAvailabilitySlot(appointment.getAvailabilitySlotID()));
                
                //Get patient name
                PreparedStatement statement = connection.prepareStatement("SELECT firstName, lastName FROM User WHERE email = ?;");
                statement.setString(1, appointment.getEmail());
                ResultSet res = statement.executeQuery();
                
                while(res.next()){
                    appointment.setPatient(res.getString("firstName")+" "+res.getString("lastName"));
                    break;
                }
                
                appointments.add(appointment);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }

        return appointments;
    }

}

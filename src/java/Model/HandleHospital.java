package Model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandleHospital {

    private final Connection connection;

    public HandleHospital() {
        Database_Connection database_Connection = Database_Connection.getDBConnection();
        connection = database_Connection.connectToDatabase();
    }

    /*Method to add hospitals into the system*/
    public void addHospital(Hospital hospital) {

        try {

            /* Statement to be executed to add a hospital */
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Hospital (name, address) VALUES (?,?)");

            /* customize statement according to recieved hospital object */
            statement.setString(1, hospital.getName());
            statement.setString(2, hospital.getAddress());

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /*Method to get all hospital in the system*/
    public List<Hospital> getAllHospitals() {

        List<Hospital> hospitals = new ArrayList<>();

        try {

            /* Statement to be executed to get all hospitals of the system */
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hospital;");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                //For each recieved result create a new hospital object and add to array
                Hospital hospital = new Hospital(results.getInt("hospitalID"), results.getString("name"), results.getString("address"));
                hospitals.add(hospital);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return hospitals;
    }

    /*Method to remove hospitals from system*/
    public void removeHospital(int hospitalID) throws MySQLIntegrityConstraintViolationException {

        try {

            /* Statement to be executed to remove a hospital from the system */
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Hospital WHERE hospitalID = ?;");
            statement.setInt(1, hospitalID);

            statement.executeUpdate();

        } catch (MySQLIntegrityConstraintViolationException e) {
            //This error will be thrown if the hospital has any availability slots attached to it
            throw e;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

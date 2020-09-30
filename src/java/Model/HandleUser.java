package Model;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandleUser {

    private final Connection connection;

    public HandleUser() {
        //Get the singleton database_connection by using the static method
        Database_Connection database_Connection = Database_Connection.getDBConnection();
        //connect to the database by using the object
        connection = database_Connection.connectToDatabase();
    }

    /* Method to register users */
    public void registerUser(User user) throws MySQLIntegrityConstraintViolationException {

        try {

            /* Statement to be executed to register specific user */
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (email, password, firstName, lastName, contactNumber, NIC, type) VALUES (?,?,?,?,?,?,?); ");

            /* customize statement according to recieved user object */
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getContactNumber());
            statement.setString(6, user.getNIC());
            statement.setString(7, user.getType());

            statement.execute();

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            /* This exception will be thrown if users with same email exists within the system. */
            throw e;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /* User login */
    public String authenticateUser(User user) {

        try {

            /* Statement to be executed to get all users */
            PreparedStatement statement = connection.prepareStatement("SELECT email, password, type FROM user");
            ResultSet results = statement.executeQuery();

            while (results.next()) {

                //From each result obtained a user object is created with the credentials, this is then cross referenced with user entered credentials (stored in user object recieved in arguments)
                // Once found type of user is returned
                User selectedUser = new User(results.getString("email"), results.getString("password"), results.getString("type"));

                // If the user exists on the database
                if (user.getEmail().equals(selectedUser.getEmail()) && user.getPassword().equals(selectedUser.getPassword())) {

                    //According to the type of user return the user type as string
                    if (selectedUser.getType().equals("admin")) {
                        return "admin";
                    } else if (selectedUser.getType().equals("user")) {
                        return "user";
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        // If user is not found return "invalid"
        return "invalid";
    }
}

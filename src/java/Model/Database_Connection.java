package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Singleton pattern*/
public class Database_Connection {

    // Eager initialization method to be thread friendly (eagerly created instance)
    private final static Database_Connection DATABASE_CONNECTION = new Database_Connection();    
    private Connection connection;
    private final String URL, username, password;

    // Private constructor to make sure class cannot be instantiated seperately.
    private Database_Connection() {
        URL = "jdbc:mysql://localhost:3306/myDoctor";
        username = "root";
        password = "";
    }
    
    //Static method to get the created instance of the singleton object
    public static Database_Connection getDBConnection() {
        return DATABASE_CONNECTION;
    }

    /* To connect to the database and get the connection*/
    public Connection connectToDatabase() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // New connection is made to the database.
            connection = DriverManager.getConnection(URL, username, password);

        } catch (ClassNotFoundException | SQLException e) {
            // Any errors are caught and displayed
            e.printStackTrace();
        }

        // The connection is returned
        return connection;
    }
}

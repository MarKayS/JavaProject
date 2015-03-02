package persistance;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    // database URL
    private static final String dbURL = "jdbc:mysql://localhost:3306/dobbelsteen";
    private static final String username = "root";
    private static final String password = "admin";
    // declare Connection for accessing and querying database
    private Connection connection;

    // constructor connects to database
    public DBConnection() {
        // connect to database books 
        try {

            // establish connection to database
            connection = (Connection) DriverManager.getConnection(dbURL, username, password);

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } // handle exceptions closing statement and connection
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
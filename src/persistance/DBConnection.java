package persistance;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    // database URL
    private static final String dbURL = "jdbc:mysql://db4free.net/jsokoban";
    private static final String username = "jsoko";
    private static final String password = "sokoban123";
    // declare Connection for accessing and querying database
    private static Connection connection = null;

    // constructor connects to database
    public static void connect(){

        try {
            if(connection == null || connection.isClosed()){
                // establish connection to database
                connection = DriverManager.getConnection(dbURL, username, password);
            }

        } catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } // handle exceptions closing statement and connection
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }

    public static Connection getConnection() throws SQLException {
        return connection;
    }
}
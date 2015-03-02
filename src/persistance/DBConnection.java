package persistance;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

    // database URL
    private static final String dbURL = "jdbc:mysql://wm48.wedos.net/d24820_jsoko";
    private static final String username = "a24820_jsoko";
    private static final String password = "sokoban123";
    private static final String driver = "com.mysql.jdbc.Driver";
    // declare Connection for accessing and querying database
    private Connection connection;

    // constructor connects to database
    public DBConnection() throws ClassNotFoundException {
        // connect to database books

        // establish connection to database
        connection = null;
        Class.forName("com.mysql.jdbc.Driver");
    }
           // connection = (Connection) DriverManager.getConnection(dbURL, username, password);


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
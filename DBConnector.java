package exhibition;

import java.sql.*;
import javax.swing.*;

public class DBConnector {

    private static final String DB_PATH = "VUE_Exhibition.accdb";
    private static final String DB_URL = "jdbc:ucanaccess://" + DB_PATH;

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            JOptionPane.showMessageDialog(null, "Database connection successful.");
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database:\n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
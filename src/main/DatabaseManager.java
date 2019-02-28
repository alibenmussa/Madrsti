package main;

import main.views.dialog.Dialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static final String DB = "jdbc:mysql://localhost/madrsti";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showAlert("Connection Error", "Madrsti can't connect to database");
            Main.closeApplication();
            return null;
        }
    }
}

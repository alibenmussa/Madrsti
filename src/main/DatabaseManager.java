package main;

import main.views.dialog.Dialog;

import java.sql.*;
import java.util.ArrayList;

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

    public static ResultSet executeSQLResultSet(String query, ArrayList<String> data) {
        int length = data.size();
        try {
            Connection con = createConnection();
            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 0; i < length; i++) {
                ps.setString(i, data.get(i));
            }
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static int executeSQLRows(String query, ArrayList<String> data) {
        int length = data.size();
        try {
            Connection con = createConnection();
            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 0; i < length; i++) {
                ps.setString(i, data.get(i));
            }
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            return 0;
        }
    }

}

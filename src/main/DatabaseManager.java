package main;

import main.views.dialog.Dialog;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    public static final String DB = "jdbc:mysql://localhost/madrsti";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showAlert("Connection Error", "Madrsti can't connect to database");
            Main.closeApplication();
            return null;
        }
    }

    public static ResultSet executeSQLResultSet(String query, ArrayList<String> data) {

        try {
            Connection con = createConnection();
            PreparedStatement ps = con.prepareStatement(query);
            if (data != null) {
                for (int i = 1; i <= data.size(); i++){
                    ps.setString(i, data.get(i - 1));
                }
            }
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static int executeSQLRows(String query, ArrayList<String> data) {

        try {
            Connection con = createConnection();
            PreparedStatement ps = con.prepareStatement(query);
            if (data != null) {
                for (int i = 1; i <= data.size(); i++) {
                    ps.setString(i, data.get(i - 1));
                }
            }
            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            return 0;
        }
    }

}

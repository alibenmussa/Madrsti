package main;

import main.views.dialog.Dialog;

import java.sql.*;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class DatabaseManager {
    public static final String DB = "jdbc:mysql://localhost/madrsti";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Dialog.showAlert("Connection Error", "Madrsti can't connect to database");
            return null;
        }
    }

    public static ResultSet executeSQLResultSet(String query, ArrayList<String> data) {
        Connection con = createConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                if (data != null) {
                    for (int i = 1; i <= data.size(); i++) {
                        ps.setString(i, data.get(i - 1));
                    }
                }
                ResultSet rs = ps.executeQuery();
                return rs;
            } catch (SQLException ex) {

                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public static int executeSQLRows(String query, ArrayList<String> data) {
        Connection con = createConnection();
        if (con != null) {
            try {
                PreparedStatement ps = con.prepareStatement(query);
                if (data != null) {
                    for (int i = 1; i <= data.size(); i++) {
                        ps.setString(i, data.get(i - 1));
                    }
                }
                int rs = ps.executeUpdate();
                return rs;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public static ArrayList<String> getResultOneRow(String query, ArrayList<String> data, int output) {
        ArrayList<String> result = new ArrayList<>();
        ResultSet rs = DatabaseManager.executeSQLResultSet(query, data);
        if (rs != null) {
            try {
                while (rs.next()) {
                    for (int i = 1; i <= output; i++) {
                        result.add(rs.getString(i));
                    }
                }
            } catch (SQLException ex) {

            }
        }
        return result;
    }

    public static void addComboBoxData(ComboBox comboBox, String query, ArrayList<String> data) {
        ArrayList<String> items = getResultOneRow(query, data, 1);
        comboBox.getItems().clear();
        comboBox.setVisibleRowCount(6);
        for (int i = 0; i < items.size(); i++) {
            comboBox.getItems().add(items.get(i));
        }
    }
}

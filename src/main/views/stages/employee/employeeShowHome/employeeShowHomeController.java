package main.views.stages.employee.employeeShowHome;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.DatabaseManager;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class employeeShowHomeController implements Initializable {
    @FXML
    private Label students;

    @FXML
    private Label classes;

    @FXML
    private Label subjects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String Studentquery = "SELECT COUNT(*) FROM students";
        result(Studentquery,students,"  STUDENT");

        String Subjectsquery = "SELECT COUNT(*) FROM subjects";
        result( Subjectsquery, subjects,"  SUBJECT");

        String Staffquery = "SELECT COUNT(*) FROM classes";
        result(Staffquery, classes,"  CLASS");
    }
    private void result(String query, Label count,String label) {
        ResultSet result = DatabaseManager.executeSQLResultSet(query, null);
        try {
            while (result.next()) {
                count.setText(result.getString(1) + label);
            }

        } catch (SQLException e) {
        }
    }
}

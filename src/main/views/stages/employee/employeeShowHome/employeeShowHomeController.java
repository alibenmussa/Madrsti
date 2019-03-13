package main.views.stages.employee.employeeShowHome;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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

    @FXML
    private LineChart<Number, Number> employeeChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        String Studentquery = "SELECT COUNT(*) FROM students";
        result(Studentquery,students,"  STUDENT");

        String Subjectsquery = "SELECT COUNT(*) FROM subjects";
        result( Subjectsquery, subjects,"  SUBJECT");

        String Staffquery = "SELECT COUNT(*) FROM classes";
        result(Staffquery, classes,"  CLASS");

        getChartData();
    }



    public void getChartData() {
        employeeChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Series 1");
        series.getData().add(new XYChart.Data<>(1, 20));
        series.getData().add(new XYChart.Data<>(2, 100));
        series.getData().add(new XYChart.Data<>(3, 80));
        series.getData().add(new XYChart.Data<>(4, 180));
        series.getData().add(new XYChart.Data<>(5, 20));
        series.getData().add(new XYChart.Data<>(6, -10));
        employeeChart.getData().add(series);

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

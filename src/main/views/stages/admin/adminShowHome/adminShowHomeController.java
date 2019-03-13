package main.views.stages.admin.adminShowHome;

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

public class adminShowHomeController implements Initializable {
    @FXML
    private Label student;

    @FXML
    private Label teacher;

    @FXML
    private Label employee;
    @FXML
    private LineChart<Number, Number> adminChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String Studentquery = "SELECT COUNT(*) FROM students";
        result(Studentquery,student,"  STUDENT");

        String Subjectsquery = "select count(*) as Teacher FROM staff where type='Teacher'";
        result( Subjectsquery, teacher,"  TEACHER");

        String Staffquery = "select count(*) as Employee FROM staff where type='Employee'";
        result(Staffquery, employee,"  EMPLOYEE");
        getChartData();

    }


    public void getChartData() {
        adminChart.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Series 1");
        series.getData().add(new XYChart.Data<>(1, 20));
        series.getData().add(new XYChart.Data<>(2, 100));
        series.getData().add(new XYChart.Data<>(3, 80));
        series.getData().add(new XYChart.Data<>(4, 180));
        series.getData().add(new XYChart.Data<>(5, 20));
        series.getData().add(new XYChart.Data<>(6, -10));
        adminChart.getData().add(series);

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

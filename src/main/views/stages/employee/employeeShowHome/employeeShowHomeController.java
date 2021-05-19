package main.views.stages.employee.employeeShowHome;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import main.DB.DatabaseManager;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowHomeController implements Initializable {
    @FXML
    private Label students;

    @FXML
    private Label classes;

    @FXML
    private Label subjects;

    @FXML
    private LineChart<String, Number> employeeChart;


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
        geyXYChartData(employeeChart);

    }

    public static void geyXYChartData(LineChart<String, Number> employeeChart) {
        String query = "SELECT grades.`name`, IFNULL(AVG(((first_midterm_exam + first_final_exam + second_midterm_exam + second_final_exam)"+
                "/ full_mark) * 100), 0) FROM grades LEFT JOIN  subjects USING(grade_id) LEFT JOIN results USING(subject_id)"+
                "GROUP BY subjects.`grade_id` ORDER BY grades.`name`";
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        try {

        while (rs.next()) {

            list1.add(rs.getString(1));
            list2.add(rs.getInt(2));

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

        employeeChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Series 1");
        series.getData().add(new XYChart.Data<>(list1.get(0), list2.get(0)));
        series.getData().add(new XYChart.Data<>(list1.get(1), list2.get(1)));
        series.getData().add(new XYChart.Data<>(list1.get(2), list2.get(2)));
        series.getData().add(new XYChart.Data<>(list1.get(3), list2.get(3)));
        series.getData().add(new XYChart.Data<>(list1.get(4), list2.get(4)));
        series.getData().add(new XYChart.Data<>(list1.get(5), list2.get(5)));
        employeeChart.setLegendVisible(false);
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

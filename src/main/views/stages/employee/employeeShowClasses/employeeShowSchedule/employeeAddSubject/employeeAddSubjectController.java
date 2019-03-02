package main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeAddSubject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.DatabaseManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeAddSubjectController {

    @FXML
    private ComboBox subject;

    @FXML
    private ComboBox teacher;

    @FXML
    private ComboBox day;

    @FXML
    private ComboBox time;

    private String grade;
    private String clas;

    public void initialize(String gradeID, String className) {
        grade = gradeID;
        clas = className;
        day.setVisibleRowCount(5);
        time.setVisibleRowCount(5);
        String subjectQuery = "SELECT DISTINCT `subjects`.`name` FROM `subjects` WHERE `grade_id` = ?";
        String teacherQuery = "SELECT DISTINCT `staff`.`full_name` FROM `staff` WHERE `type` = 'Teacher'";
        ArrayList<String> data1 = new ArrayList<>();
        data1.add(gradeID);
        DatabaseManager.addComboBoxData(subject, subjectQuery, data1);
        DatabaseManager.addComboBoxData(teacher, teacherQuery, null);

    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {
        ArrayList<String> data = new ArrayList<>();
        String selectedSubject = String.valueOf(subject.getSelectionModel().getSelectedItem());
        String selectedTeacher = String.valueOf(teacher.getSelectionModel().getSelectedItem());
        String selectedDay = String.valueOf(day.getSelectionModel().getSelectedIndex() + 1);
        String selectedTime = String.valueOf(time.getSelectionModel().getSelectedIndex() + 1);
        System.out.println(subject.getValue());
        System.out.println(subject.getSelectionModel().getSelectedItem());
        data.add(selectedSubject);
        data.add(selectedTeacher);
        data.add(grade);
        data.add(clas);
        data.add(selectedDay);
        data.add(selectedTime);
        System.out.println(data);
        /*int rs = DatabaseManager.executeSQLRows("INSERT INTO `schedules` VALUES(?, ?, ?, ?, ?, ?)", data);
        if (rs == 1) {
            Dialog.success = true;
            Dialog.closeDialogWindow();
        } else {
            System.out.println("Error Input");
        }*/
        Dialog.closeDialogWindow();
    }

    @FXML
    public void employeeCancelAddCancel(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

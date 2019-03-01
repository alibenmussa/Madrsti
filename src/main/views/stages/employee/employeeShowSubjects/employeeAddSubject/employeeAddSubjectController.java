package main.views.stages.employee.employeeShowSubjects.employeeAddSubject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import main.DatabaseManager;
import main.views.dialog.Dialog;
import main.views.stages.template.Grade;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeAddSubjectController {

    @FXML
    private TextField subject;

    @FXML
    private ComboBox grade;

    @FXML
    private TextField fullMark;

    @FXML
    private TextField passingMark;

    ObservableList<Grade> gradesName;

    public void initialize(ArrayList<String> data) {
        ResultSet rs1 = DatabaseManager.executeSQLResultSet("SELECT `grades`.`name` FROM grades", null);
        if (rs1 != null) {
            try {
                while (rs1.next()) {
                    grade.getItems().add(rs1.getString(1));
                }
            } catch (SQLException ex) {

            }
        }
    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        data1.add(grade.getSelectionModel().getSelectedItem().toString());
        String rs1 = DatabaseManager.getResultOneRow("SELECT `grade_id` FROM `grades` WHERE `name` = ?", data1,1).get(0);
        data2.add(subject.getText());
        data2.add(rs1);
        System.out.println(rs1);
        data2.add(fullMark.getText());
        data2.add(passingMark.getText());
        int rs2 = DatabaseManager.executeSQLRows("INSERT INTO `subjects`(`name`, `grade_id`, `full_mark`, `passing_mark`) VALUES(?, ?, ?, ?)", data2);
        if (rs2 == 1) {
            Dialog.success = true;
            Dialog.closeDialogWindow();
        } else {
            System.out.println("Error Input");
        }
    }

    @FXML
    public void employeeCancelAddCancel(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

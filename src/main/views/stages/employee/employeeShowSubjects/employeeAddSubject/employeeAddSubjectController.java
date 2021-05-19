package main.views.stages.employee.employeeShowSubjects.employeeAddSubject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;

import java.util.ArrayList;

public class employeeAddSubjectController {

    @FXML
    private TextField subject;

    @FXML
    private ComboBox grade;

    @FXML
    private TextField fullMark;

    @FXML
    private TextField passingMark;

    public void initialize() {
        String gradeQuery = "SELECT `name` FROM `grades`";
        ComboFacade.ComboAddData("ComboBox",grade, gradeQuery, null);
    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {
        ArrayList<String> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        data1.add(grade.getSelectionModel().getSelectedItem().toString());
        String rs1 = DatabaseManager.getResultOneRow("SELECT `grade_id` FROM `grades` WHERE `name` = ?", data1,1).get(0);
        data2.add(subject.getText());
        data2.add(rs1);
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

package main.views.stages.employee.employeeShowSubjects.employeeAddSubject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeAddSubjectController implements Initializable {

    @FXML
    private TextField subject;

    @FXML
    private ComboBox<?> grade;

    @FXML
    private TextField fullMark;

    @FXML
    private TextField passingMark;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {
        Dialog.success = true;
        Dialog.closeDialogWindow();
    }

    @FXML
    public void employeeCancelAddCancel(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

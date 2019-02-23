package main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeAddSubject;

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
    private ComboBox<?> teacher;

    @FXML
    private TextField subject;

    @FXML
    private ComboBox<?> day;

    @FXML
    private ComboBox<?> time;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {

    }

    @FXML
    public void employeeCancelAddCancel(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

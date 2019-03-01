package main.views.stages.employee.employeeShowClasses.employeeAddClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeAddClassController {

    @FXML
    private TextField clas;

    @FXML
    private TextField capacity;

    @FXML
    private ComboBox grade;

    public void initialize() {

    }

    @FXML
    public void employeeSaveAddClass(ActionEvent event) {

    }

    @FXML
    public void employeeCancelAddClass(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

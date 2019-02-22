package main.views.stages.employee.employeeShowClasses.employeeAddClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeAddClassController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void employeeSaveAddClass(ActionEvent event) {

    }

    @FXML
    public void employeeCancelAddClass(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

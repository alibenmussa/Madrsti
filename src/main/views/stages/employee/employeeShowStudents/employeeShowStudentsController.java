package main.views.stages.employee.employeeShowStudents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<?> year;

    @FXML
    private String reihe;

    @FXML
    private String teacher;

    @FXML
    private String parallel;

    @FXML
    private ComboBox<?> clas;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("blah");
    }

    @FXML
    void employeeAddStudent(ActionEvent event) throws Exception {
        Dialog.show("Add Students", "/main/views/stages/employee/employeeShowStudents/employeeAddStudents/employeeAddStudents.fxml");
    }

}

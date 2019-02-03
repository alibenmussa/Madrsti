package main.views.stages.admin.adminShowStudents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class adminShowStudentsController implements Initializable {
    @FXML
    private TextField search;

    @FXML


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void adminAddStudents(ActionEvent event) throws Exception {
        Dialog.show("Add Students", "/main/views/stages/admin/adminShowStudents/adminAddStudents/adminAddStudents.fxml");
    }

}

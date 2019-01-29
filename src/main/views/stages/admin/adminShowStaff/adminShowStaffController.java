package main.views.stages.admin.adminShowStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class adminShowStaffController implements Initializable {
    @FXML
    private TextField search;

    @FXML


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void adminAddEmployee(ActionEvent event) throws Exception {
        Dialog.show("/main/views/stages/admin/adminShowStaff/adminAddStaff/adminAddStaff.fxml");
    }
}

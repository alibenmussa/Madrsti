package main.views.stages.admin.adminShowStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class adminShowStaffController implements Initializable {
    @FXML
    private TextField search;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void adminAddStaff(ActionEvent event) throws Exception {
        Dialog.show("Add Staff", "/main/views/stages/admin/adminShowStaff/adminAddStaff/adminAddStaff.fxml");
    }
}

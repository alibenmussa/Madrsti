package main.views.stages.admin.adminShowEmployees;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.views.dialog.Dialog;

public class adminShowEmployeesController {



    @FXML
    void adminAddEmployee(ActionEvent event) throws Exception {
        Dialog.show("/main/views/stages/admin/adminShowEmployees/adminAddEmployee/adminAddEmployee.fxml");
    }
}

package main.views.stages.admin.adminNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

public class adminNavbarController {
    @FXML
    void adminShowHome(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

    @FXML
    void adminShowEmployees(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
    }

    @FXML
    void adminShowStudents(ActionEvent event) throws Exception {
        boolean delete = Dialog.showConfirm("Do you sure delete this employee from here?");
        System.out.println(delete);
    }

    @FXML
    void adminShowResults(ActionEvent event) {

    }




}

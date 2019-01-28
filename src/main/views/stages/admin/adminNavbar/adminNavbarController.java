package main.views.stages.admin.adminNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Main;
import main.StagesManager;

public class adminNavbarController {
    @FXML
    void adminShowHome(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

    @FXML
    void adminShowEmployees(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowEmployees/adminShowEmployees.fxml");
    }

    @FXML
    void adminShowStudents(ActionEvent event) {

    }

    @FXML
    void adminShowResults(ActionEvent event) {

    }




}

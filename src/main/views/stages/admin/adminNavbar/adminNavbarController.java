package main.views.stages.admin.adminNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import main.Main;
import main.login.AuthenticationLogin;

public class adminNavbarController {
    @FXML
    void adminShowHome(ActionEvent event) {
        Main.FXMLLoaderPane(AuthenticationLogin.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

    @FXML
    void adminShowEmployees(ActionEvent event) {
        Main.FXMLLoaderPane(AuthenticationLogin.stageContent, "/main/views/stages/admin/adminShowEmployees/adminShowEmployees.fxml");
    }

    @FXML
    void adminShowResults(ActionEvent event) {

    }

    @FXML
    void adminShowStudents(ActionEvent event) {

    }


}

package main.views.stages.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.login.AuthenticationLogin;
import main.views.Admin;

import java.net.URL;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private AnchorPane header;
    @FXML
    private AnchorPane navbar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/titlebar.fxml");
        Main.shadowMaker(navbar);
        AuthenticationLogin.navbarLoader(navbar, "/main/views/stages/admin/adminNavbar/adminNavbar.fxml");


    }

}

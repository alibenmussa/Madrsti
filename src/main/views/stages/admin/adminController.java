package main.views.stages.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    @FXML
    private ScrollPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AuthenticationLogin.stageContent = content;
        //جلب شريط العنوان
        Main.FXMLLoaderPane(header, "/main/titlebar.fxml");
        //جلب القائمة الجانبية
        Main.FXMLLoaderPane(navbar, "/main/views/stages/admin/adminNavbar/adminNavbar.fxml");
        Main.shadowMaker(navbar);
        //جلب الصفحة الرئيسية
        Main.FXMLLoaderPane(AuthenticationLogin.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

}

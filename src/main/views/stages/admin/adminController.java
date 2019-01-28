package main.views.stages.admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.StagesManager;

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
        StagesManager.stageContent = content;
        //جلب شريط العنوان
        Main.FXMLLoaderPane(header, "/main/titlebar.fxml");
        //جلب القائمة الجانبية
        Main.FXMLLoaderPane(navbar, "/main/views/stages/admin/adminNavbar/adminNavbar.fxml");
        Main.shadowMaker(navbar);
        //جلب الصفحة الرئيسية
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

}

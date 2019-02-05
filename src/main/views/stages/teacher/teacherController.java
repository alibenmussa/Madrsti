package main.views.stages.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.StagesManager;

import java.net.URL;
import java.util.ResourceBundle;

public class teacherController implements Initializable {
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
        Main.FXMLLoaderPane(navbar, "/main/views/stages/teacher/teacherNavbar/teacherNavbar.fxml");
        Main.shadowMaker(navbar);
        //جلب الصفحة الرئيسية
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/teacher/teacherShowHome/teacherShowHome.fxml");
    }

}

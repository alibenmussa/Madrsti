package main.views.stages.teacher.teacherNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

public class teacherNavbarController {
    @FXML
    private Button teacherShowHome;
    @FXML
    private Button teacherShowProfile;


    @FXML
    void teacherShowHome(ActionEvent event) {
        addActiveButton(teacherShowHome);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/teacher/teacherShowHome");
    }


    @FXML
    void teacherShowProfile(ActionEvent event) {
        addActiveButton(teacherShowProfile);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/teacher/teacherShowProfile/teacherShowProfile.fxml");
    }


    private void addActiveButton(Button currentButton) {
        teacherShowHome.getStyleClass().remove("active");
        teacherShowProfile.getStyleClass().remove("active");
        currentButton.getStyleClass().add("active");
    }


}

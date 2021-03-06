package main.views.stages.admin.adminNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

public class adminNavbarController {
    @FXML
    private Button adminShowHome;
    @FXML
    private Button adminShowStaff;
    @FXML
    private Button adminShowStudents;
    @FXML
    private Button adminShowResults;


    @FXML
    void adminShowHome(ActionEvent event) {
        addActiveButton(adminShowHome);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowHome/adminShowHome.fxml");
    }

    @FXML
    void adminShowStaff(ActionEvent event) {
        addActiveButton(adminShowStaff);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
    }

    @FXML
    void adminShowStudents(ActionEvent event) {
        addActiveButton(adminShowStudents);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStudents/adminShowStudents.fxml");
        /*boolean delete = Dialog.showConfirm("Are you sure you want to delete this employee?");
        Madrsti.out.println(delete);*/
    }

    @FXML
    void adminShowResults(ActionEvent event) {
        addActiveButton(adminShowResults);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowResults/adminShowResults.fxml");

    }

    private void addActiveButton(Button currentButton) {
        adminShowHome.getStyleClass().remove("active");
        adminShowStaff.getStyleClass().remove("active");
        adminShowStudents.getStyleClass().remove("active");
        adminShowResults.getStyleClass().remove("active");
        currentButton.getStyleClass().add("active");
    }


}

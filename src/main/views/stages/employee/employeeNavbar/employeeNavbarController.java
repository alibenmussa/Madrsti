package main.views.stages.employee.employeeNavbar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

public class employeeNavbarController {
    @FXML
    private Button employeeShowHome;
    @FXML
    private Button employeeShowStudents;
    @FXML
    private Button employeeShowClasses;
    @FXML
    private Button employeeShowResults;
    @FXML
    private Button employeeShowSubjects;


    @FXML
    void employeeShowHome(ActionEvent event) {
        addActiveButton(employeeShowHome);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowHome/employeeShowHome.fxml");
    }


    @FXML
    void employeeShowStudents(ActionEvent event) {
        addActiveButton(employeeShowStudents);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
    }

    @FXML
    void employeeShowClasses(ActionEvent event) {
        addActiveButton(employeeShowClasses);
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowClasses/employeeShowClasses.fxml");

    }

    @FXML
    void employeeShowSubjects(ActionEvent event) throws Exception {
        addActiveButton(employeeShowResults);
        Dialog.show("Profile", "/main/views/stages/employee/employeeShowClasses/employeeShowClasses.fxml");

    }

    @FXML
    void employeeShowResults(ActionEvent event) {
        addActiveButton(employeeShowResults);

    }


    private void addActiveButton(Button currentButton) {
        employeeShowHome.getStyleClass().remove("active");
        employeeShowStudents.getStyleClass().remove("active");
        employeeShowClasses.getStyleClass().remove("active");
        employeeShowSubjects.getStyleClass().remove("active");
        employeeShowResults.getStyleClass().remove("active");
        currentButton.getStyleClass().add("active");
    }


}

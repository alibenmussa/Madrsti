package main.views.stages.employee.employeeShowClasses.employeeShowSchedule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeShowScheduleController implements Initializable {
    @FXML
    private VBox classesList;

    @FXML
    private Label name;

    @FXML
    private GridPane schedule;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createScheduleTable(String clas) {
        name.setText("1st Primary - Group " + clas);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 7; j++) {
                Button btn = new Button("Arabic");
                btn.getStyleClass().addAll("gray-button", "cell-schedule");
                btn.setStyle("-fx-max-height: Infinity; -fx-max-width: Infinity");
                btn.setOnAction(e -> {
                    try {
                        Dialog.showConfirm("Do you want to delete this subject?");
                    } catch (Exception ex) {

                    }
                });
                schedule.add(btn, i, j);
            }
        }

    }

    @FXML
    void addSubject(ActionEvent event) throws Exception {
        Dialog.show("Add Subject", "/main/views/stages/employee/employeeShowClasses/employeeShowSchedule/employeeAddSubject/employeeAddSubject.fxml");
    }

    @FXML
    void goBack(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowClasses/employeeShowClasses.fxml");
    }




}

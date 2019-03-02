package main.views.stages.employee.employeeShowClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeShowScheduleController;
import main.views.stages.employee.employeeShowClasses.employeeAddClass.employeeAddClassController;
import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowClassesController implements Initializable {
    @FXML
    VBox classesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet rs1 = DatabaseManager.executeSQLResultSet("SELECT DISTINCT `classes`.`grade_id`, `grades`.name FROM `classes` INNER JOIN `grades` USING(grade_id)", null);
        if (rs1 != null) {
            try {
                while (rs1.next()) {
                    AnchorPane ap = new AnchorPane();
                    String currentGradeId = rs1.getString(1);
                    String currentGradeName = rs1.getString(2);
                    ap.getStyleClass().addAll("content");
                    Label label = new Label(currentGradeName);
                    label.getStyleClass().add("title-h2");
                    label.setPadding(new Insets(0, 0, 35, 0));

                    GridPane gp = new GridPane();
                    gp.setHgap(30);
                    gp.setVgap(30);

                    int i = 0;
                    ArrayList<String> data = new ArrayList<>();
                    data.add(currentGradeId);
                    ResultSet rs2 = DatabaseManager.executeSQLResultSet("SELECT `classes`.`class_id` FROM `classes` WHERE `grade_id` = ?", data);
                    if (rs2 != null) {
                        try {
                            while (rs2.next()) {
                                String currentClass = rs2.getString(1);
                                Button btn = new Button(currentClass);
                                btn.getStyleClass().addAll("gray-button", "big-button");
                                btn.setOnAction(e -> {
                                    showClassSchedule(currentGradeId, currentGradeName, currentClass);
                                });
                                GridPane.setHalignment(btn, HPos.CENTER);
                                gp.add(btn, (i % 5), (i / 5));
                                i++;
                            }
                        } catch (SQLException ex) {

                        }
                    }

                    VBox vb = new VBox(label, gp);
                    vb.setPadding(new Insets(25, 15, 15, 15));
                    setAnchorsToEdge(vb);
                    ap.getChildren().add(vb);
                    classesList.getChildren().add(ap);
                }
            } catch (SQLException ex) {

            }
        }
    }


    private void setAnchorsToEdge(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    @FXML
    void addClass(ActionEvent event) {
        String path = "/main/views/stages/employee/employeeShowClasses/employeeAddClass/employeeAddClass.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        boolean addSubject = Dialog.showAndPass("Add Subject", loader.getRoot());
        if (addSubject) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowClasses/employeeShowClasses.fxml");
        }
    }

    private void showClassSchedule(String gradeId, String gradeName, String className) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/employee/employeeShowClasses/employeeShowSchedule/employeeShowSchedule.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        employeeShowScheduleController controller = loader.getController();
        controller.initialize(gradeId, gradeName, className);
        StagesManager.stageContent.setContent(loader.getRoot());
        StagesManager.stageContent.setVvalue(0);

    }
}

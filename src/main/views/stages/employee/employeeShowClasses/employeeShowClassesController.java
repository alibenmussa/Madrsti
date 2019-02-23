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
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeShowScheduleController;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeShowClassesController implements Initializable {
    @FXML
    VBox classesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane ap = new AnchorPane();

        ap.getStyleClass().addAll("content");
        Label label = new Label("2st Primary");
        label.getStyleClass().add("title-h2");
        label.setPadding(new Insets(0, 0, 35, 0));

        GridPane gp = new GridPane();
        gp.setHgap(30);
        gp.setVgap(30);

        int i = 0;
        for (char c = 'A'; c < 'E'; c++) {
            String clas = String.valueOf(c);
            Button btn = new Button(clas);
            btn.getStyleClass().addAll("gray-button", "big-button");
            btn.setOnAction(e -> {
                try {
                    showClassSchedule(clas);
                } catch (Exception ex) {

                }
            });
            GridPane.setHalignment(btn, HPos.CENTER);
            gp.add(btn, (i % 5), (i / 5));
            i++;
        }

        VBox vb = new VBox(label, gp);
        vb.setPadding(new Insets(15));
        setAnchorsToEdge(vb);
        ap.getChildren().add(vb);
        classesList.getChildren().add(ap);
    }


    private void setAnchorsToEdge(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    @FXML
    void addClass(ActionEvent event) throws Exception {
        Dialog.show("Add Class", "/main/views/stages/employee/employeeShowClasses/employeeAddClass/employeeAddClass.fxml");
    }

    private void showClassSchedule(String clas) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main/views/stages/employee/employeeShowClasses/employeeShowSchedule/employeeShowSchedule.fxml"));
        loader.load();
        employeeShowScheduleController sc = loader.getController();
        sc.createScheduleTable(clas);
        StagesManager.stageContent.setContent(loader.getRoot());

    }
}

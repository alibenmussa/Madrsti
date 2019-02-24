package main.views.stages.employee.employeeShowSubjects;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeShowScheduleController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowSubjectsController implements Initializable {
    @FXML
    private VBox classesList;
    @FXML
    private GridPane subjects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> ar = new ArrayList<>();
        ar.add("Math");
        ar.add("Arabic Language");
        ar.add("English");
        ar.add("Islam");
        for (int i = 0; i < 5; i++) {
            AnchorPane ap = new AnchorPane();
            ap.getStyleClass().addAll("content");
            VBox vb = new VBox(15);
            vb.setPadding(new Insets(25, 15, 15, 15));
            Label label = new Label("2st Primary");
            label.getStyleClass().add("title-h2");
            label.setPadding(new Insets(0, 0, 20, 0));
            vb.getChildren().add(label);
            for (int j = 0; j < ar.size(); j++) {
                HBox hb = new HBox(20);
                hb.setAlignment(Pos.CENTER_LEFT);
                Button btn = new Button();
                btn.getStyleClass().add("delete-button");
                btn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CLOSE));
                Label subject = new Label(ar.get(j));
                subject.getStyleClass().add("small-subject");
                hb.getChildren().addAll(btn, subject);
                vb.getChildren().add(hb);
            }
            setAnchorsToEdge(vb);
            ap.getChildren().add(vb);
            ap.setPrefHeight(60.0);
            subjects.add(ap, (i % 2), (i / 2));
        }

    }

    private void setAnchorsToEdge(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    @FXML
    void addSubject(ActionEvent event) throws Exception {
        Dialog.show("Add Subject", "/main/views/stages/employee/employeeShowSubjects/employeeAddSubject/employeeAddSubject.fxml");
    }
}

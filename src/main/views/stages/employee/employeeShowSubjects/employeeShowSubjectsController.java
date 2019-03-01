package main.views.stages.employee.employeeShowSubjects;

import main.StagesManager;
import main.views.stages.employee.employeeShowSubjects.employeeAddSubject.employeeAddSubjectController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.DatabaseManager;
import main.Main;
import main.views.dialog.Dialog;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowSubjectsController implements Initializable {
    @FXML
    private VBox classesList;
    @FXML
    private GridPane subjects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet rs1 = DatabaseManager.executeSQLResultSet("SELECT DISTINCT `subjects`.`grade_id`, `grades`.name FROM subjects INNER JOIN grades USING(grade_id)", null);
        if (rs1 != null) {
            try {
                int position = 0;
                while (rs1.next()) {
                    AnchorPane ap = new AnchorPane();
                    ap.getStyleClass().addAll("content");
                    VBox vb = new VBox(15);
                    vb.setPadding(new Insets(25, 15, 15, 15));
                    Label label = new Label(rs1.getString(2));
                    label.getStyleClass().add("title-h2");
                    label.setPadding(new Insets(0, 0, 20, 0));
                    vb.getChildren().add(label);
                    String currentGrade = rs1.getString(1);
                    ArrayList<String> data1 = new ArrayList<>();
                    data1.add(currentGrade);
                    ResultSet rs2 = DatabaseManager.executeSQLResultSet("SELECT `name`, `subject_id` FROM `subjects` WHERE `grade_id` = ?", data1);
                    while (rs2.next()) {
                        HBox hb = new HBox(20);
                        hb.setAlignment(Pos.CENTER_LEFT);
                        Button btn = new Button();
                        btn.getStyleClass().add("delete-button");
                        btn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CLOSE));
                        Label subject = new Label(rs2.getString(1));
                        ArrayList<String> data2 = new ArrayList<>();
                        data2.add(rs2.getString(2));
                        btn.setOnAction(e -> {
                            boolean delete = Dialog.showConfirm("Delete Subject", "Are you sure you want to delete " + currentGrade);
                            if (delete) {
                                int rowsAffected = DatabaseManager.executeSQLRows("DELETE FROM `subjects` WHERE `subject_id` = ?", data2);
                                if (rowsAffected > 0) {
                                    Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowSubjects/employeeShowSubjects.fxml");
                                }
                            }
                        });
                        subject.getStyleClass().add("small-subject");
                        hb.getChildren().addAll(btn, subject);
                        vb.getChildren().add(hb);
                    }
                    setAnchorsToEdge(vb);
                    ap.getChildren().add(vb);
                    ap.setPrefHeight(60.0);
                    subjects.add(ap, (position % 2), (position / 2));
                    position++;
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
    void addSubject(ActionEvent event) {
        String path = "/main/views/stages/employee/employeeShowSubjects/employeeAddSubject/employeeAddSubject.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("pla");
        }
        employeeAddSubjectController controller = loader.getController();
        controller.initialize();
        boolean addSubject = Dialog.showAndPass("Add Subject", loader.getRoot());
        if (addSubject) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowSubjects/employeeShowSubjects.fxml");
        }
    }
}

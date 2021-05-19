package main.views.stages.admin.adminShowResults;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.StagesManager;
import main.views.stages.admin.adminShowResults.adminShowClassResult.adminShowClassResultController;
import main.views.stages.template.ComboForm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminShowResultsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private GridPane subjects;

    @FXML
    private ComboBox<ComboForm> type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboFacade.ComboAddData("ComboBoxWithId",type, "SELECT `grade_id`, `name` FROM `grades`", null);
        type.getItems().add(0, new ComboForm("-1", "All"));
        type.getSelectionModel().selectFirst();
        setupGrid(null);
    }

    private void setupGrid(String key) {
        String selectedGrade =  type.getValue().getId();

        subjects.getChildren().clear();
        String query1 = null;
        if (key == null) {
            if (selectedGrade.equals("-1")) {
                query1 = "SELECT DISTINCT `schedules`.`grade_id`, `grades`.`name`, `schedules`.`subject_id`, `subjects`.`name` FROM `schedules` INNER JOIN `grades` USING(`grade_id`) INNER JOIN `subjects` USING(`subject_id`) ORDER BY `schedules`.`grade_id`, `schedules`.`class_id`, `subjects`.`name` ASC";
            } else {
                query1 = "SELECT DISTINCT `schedules`.`grade_id`, `grades`.`name`, `schedules`.`subject_id`, `subjects`.`name` FROM `schedules` INNER JOIN `grades` USING(`grade_id`) INNER JOIN `subjects` USING(`subject_id`) WHERE `schedules`.`grade_id` = '" + selectedGrade + "'ORDER BY `schedules`.`grade_id`, `schedules`.`class_id`, `subjects`.`name` ASC";
            }
        } else {
            if (selectedGrade.equals("-1")) {
                query1 = "SELECT DISTINCT `schedules`.`grade_id`, `grades`.`name`, `schedules`.`subject_id`, `subjects`.`name` FROM `schedules` INNER JOIN `grades` USING(`grade_id`) INNER JOIN `subjects` USING(`subject_id`) WHERE `subjects`.`name` LIKE '" + key + "%' ORDER BY `schedules`.`grade_id`, `schedules`.`class_id`, `subjects`.`name` ASC";
            } else {
                query1 = "SELECT DISTINCT `schedules`.`grade_id`, `grades`.`name`, `schedules`.`subject_id`, `subjects`.`name` FROM `schedules` INNER JOIN `grades` USING(`grade_id`) INNER JOIN `subjects` USING(`subject_id`) WHERE `subjects`.`name` LIKE '" + key + "%' AND `schedules`.`grade_id` = '" + selectedGrade + "'ORDER BY `schedules`.`grade_id`, `schedules`.`class_id`, `subjects`.`name` ASC";

            }
        }
        ResultSet rs1 = DatabaseManager.executeSQLResultSet(query1, null);
        if (rs1 != null) {
            try {
                int position = 0;
                while (rs1.next()) {
                    AnchorPane ap = new AnchorPane();
                    ap.getStyleClass().addAll("content");
                    VBox vb = new VBox(15);
                    vb.setPadding(new Insets(25, 15, 15, 15));

                    String currentGradeName = rs1.getString(2);
                    String currentSubjectId = rs1.getString(3);
                    String currentSubjectName = rs1.getString(4);

                    Label subjectName = new Label(currentSubjectName);
                    subjectName.getStyleClass().add("title-h2");
                    Label gradeName = new Label(currentGradeName);
                    gradeName.getStyleClass().add("small-subject");
                    gradeName.setPadding(new Insets(0, 0, 20, 0));
                    vb.getChildren().addAll(subjectName, gradeName);

                    String currentGradeId = rs1.getString(1);
                    GridPane gp = new GridPane();
                    gp.setHgap(30);
                    gp.setVgap(30);

                    int i = 0;
                    ArrayList<String> data2 = new ArrayList<>();
                    data2.add(rs1.getString(1));
                    data2.add(currentSubjectId);
                    ResultSet rs2 = DatabaseManager.executeSQLResultSet("SELECT DISTINCT `class_id` FROM `schedules` WHERE `grade_id` = ? AND `subject_id` = ? ORDER BY `class_id` ASC", data2);
                    if (rs2 != null) {
                        try {
                            while (rs2.next()) {
                                String currentClass = rs2.getString(1);
                                Button btn = new Button(currentClass);
                                btn.getStyleClass().addAll("gray-button", "big-button");
                                btn.setOnAction(e -> {
                                    showClassResult(currentGradeId, currentGradeName, currentClass, currentSubjectId, currentSubjectName);
                                });
                                GridPane.setHalignment(btn, HPos.CENTER);
                                gp.add(btn, (i % 2), (i / 2));
                                i++;
                            }
                        } catch (SQLException ex) {

                        }
                    }

                    vb.getChildren().add(gp);
                    setAnchorsToEdge(vb);
                    ap.getChildren().add(vb);
                    ap.setPrefHeight(60.0);
                    subjects.add(ap, (position % 2), (position / 2));
                    position++;
                }
            } catch (SQLException ex) {

            }
        } else {
            subjects.setVisible(false);
        }
    }

    private void setAnchorsToEdge(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    private void showClassResult(String gradeId, String gradeName, String className, String subjectId, String subjectName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/admin/adminShowResults/adminShowClassResult/adminShowClassResult.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        adminShowClassResultController controller = loader.getController();
        controller.initialize(gradeId, gradeName, className, subjectId, subjectName);
        StagesManager.stageContent.setContent(loader.getRoot());
        StagesManager.stageContent.setVvalue(0);

    }

    @FXML
    void keySearch(KeyEvent event) {
        setupGrid(search.getText());
    }

    @FXML
    void searchType(ActionEvent event) {
        setupGrid(search.getText());
    }

}

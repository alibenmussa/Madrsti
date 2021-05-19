package main.views.stages.teacher.teacherShowHome;

import javafx.geometry.HPos;
import main.StagesManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.DB.DatabaseManager;
import main.views.stages.teacher.teacherShowHome.teacherShowResult.teacherShowResultController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class teacherShowHomeController implements Initializable {
    @FXML
    private VBox classesList;
    @FXML
    private GridPane subjects;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add(StagesManager.userId);
        ResultSet rs1 = DatabaseManager.executeSQLResultSet("SELECT DISTINCT `schedules`.`grade_id`, `grades`.`name`, `schedules`.`subject_id`, `subjects`.`name` FROM `schedules` INNER JOIN `grades` USING(`grade_id`) INNER JOIN `subjects` USING(`subject_id`) WHERE `schedules`.`staff_id` = ? ORDER BY `grade_id` ASC", data1);
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
                    ArrayList<String> data = new ArrayList<>();
                    data.add(currentGradeId);
                    data.add(StagesManager.userId);
                    ResultSet rs2 = DatabaseManager.executeSQLResultSet("SELECT `class_id` FROM `schedules` WHERE `grade_id` = ? AND `staff_id` = ? ORDER BY `class_id` ASC", data);
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
        }
    }

    private void setAnchorsToEdge(Node node) {
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }

    private void showClassResult(String gradeId, String gradeName, String className, String subjectId, String subjectName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/teacher/teacherShowHome/teacherShowResult/teacherShowResult.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        teacherShowResultController controller = loader.getController();
        controller.initialize(gradeId, gradeName, className, subjectId, subjectName);
        StagesManager.stageContent.setContent(loader.getRoot());
        StagesManager.stageContent.setVvalue(0);

    }

}

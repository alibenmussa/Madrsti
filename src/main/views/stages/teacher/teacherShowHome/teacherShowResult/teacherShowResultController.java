package main.views.stages.teacher.teacherShowHome.teacherShowResult;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class teacherShowResultController {
    @FXML
    private VBox classesList;

    @FXML
    private Button editButton;

    @FXML
    private Label subject;

    @FXML
    private Label clas;

    @FXML
    private GridPane resultTable;

    @FXML
    private Button cancelEdit;

    @FXML
    private HBox buttons;

    boolean editToggle = false;

    public String gradeId;
    public String gradeName;
    public String className;
    public String subjectId;
    public String subjectName;



    public void initialize(String gradeId, String gradeName, String className, String subjectId, String subjectName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.className = className;
        this.subjectId = subjectId;
        this.subjectName = subjectName;

        buttons.getChildren().remove(cancelEdit);

        subject.setText(subjectName);
        clas.setText("Group " + className + " - " + gradeName);

        ArrayList<String> data1 = new ArrayList<>();
        data1.add(subjectId);
        data1.add(gradeId);
        data1.add(className);
        String query1 = "SELECT `students`.`full_name`, `first_midterm_exam`, `first_final_exam`, `second_midterm_exam`, `second_final_exam`, `results`.`student_id` FROM `results` INNER JOIN `students` USING(`student_id`) WHERE `subject_id` = ? AND `grade_id` = ? AND `class_id` = ?";
        ResultSet rs1 = DatabaseManager.executeSQLResultSet(query1, data1);
        if (rs1 != null) {
            int position = 1;
            try {
                while (rs1.next()) {
                    Label studentName = new Label(rs1.getString(1));
                    studentName.setAlignment(Pos.CENTER_LEFT);
                    studentName.getStyleClass().add("cell-day");
                    TextField firstMidtermExam = new TextField(rs1.getString(2));
                    TextField firstFinalExam = new TextField(rs1.getString(3));
                    TextField secondMidtermExam = new TextField(rs1.getString(4));
                    TextField secondFinalExam = new TextField(rs1.getString(5));

                    String studentId = rs1.getString(6);

                    firstMidtermExam.setAlignment(Pos.CENTER);
                    firstFinalExam.setAlignment(Pos.CENTER);
                    secondMidtermExam.setAlignment(Pos.CENTER);
                    secondFinalExam.setAlignment(Pos.CENTER);

                    resultTable.add(studentName, 0, position);
                    resultTable.add(firstMidtermExam, 1, position);
                    resultTable.add(firstFinalExam, 2, position);
                    resultTable.add(secondMidtermExam, 3, position);
                    resultTable.add(secondFinalExam, 4, position);

                    firstMidtermExam.setDisable(true);
                    firstFinalExam.setDisable(true);
                    secondMidtermExam.setDisable(true);
                    secondFinalExam.setDisable(true);

                    editButton.setOnAction(e -> {
                        if (editToggle == true) {
                            editButton.setText("EDIT");
                            buttons.getChildren().remove(cancelEdit);
                            firstMidtermExam.setDisable(true);
                            firstFinalExam.setDisable(true);
                            secondMidtermExam.setDisable(true);
                            secondFinalExam.setDisable(true);
                            editToggle = false;

                            ArrayList<String> data2 = new ArrayList<>();
                            data2.add(firstMidtermExam.getText());
                            data2.add(firstFinalExam.getText());
                            data2.add(secondMidtermExam.getText());
                            data2.add(secondFinalExam.getText());
                            data2.add(studentId);
                            data2.add(subjectId);


                            String query2 = "UPDATE `results` SET `first_midterm_exam` = ?, `first_final_exam` = ?, `second_midterm_exam` = ?, `second_final_exam` = ? WHERE `student_id` = ? AND `subject_id` = ? AND year = 2019";
                            int rowsAffected = DatabaseManager.executeSQLRows(query2, data2);
                            if (rowsAffected > 0) {
                                Dialog.showAlert("Successful", "Your data has been updated!");
                                initialize(gradeId, gradeName, className, subjectId, subjectName);
                            }
                        } else {
                            editButton.setText("SAVE");
                            buttons.getChildren().add(1, cancelEdit);
                            firstMidtermExam.setDisable(false);
                            firstFinalExam.setDisable(false);
                            secondMidtermExam.setDisable(false);
                            secondFinalExam.setDisable(false);
                            editToggle = true;
                        }
                    });

                    cancelEdit.setOnAction(e -> {
                        editButton.setText("EDIT");
                        buttons.getChildren().remove(cancelEdit);
                        firstMidtermExam.setDisable(true);
                        firstFinalExam.setDisable(true);
                        secondMidtermExam.setDisable(true);
                        secondFinalExam.setDisable(true);
                        editToggle = false;
                    });

                    position++;
                }
            } catch (SQLException ex) {
            }
        } else {
            resultTable.setVisible(false);
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/teacher/teacherShowHome/teacherShowHome.fxml");
    }
}

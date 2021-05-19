package main.views.stages.employee.employeeShowStudents.employeeShowStudentResult;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class employeeShowStudentResultController {
    @FXML
    private Label fullName;

    @FXML
    private Label grade;

    @FXML
    private Label state;

    @FXML
    private Label amount;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private GridPane resultTable;

    public void initialize(String id) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);

        ArrayList<String> data = new ArrayList<>();
        data.add(id);

        String query1 = "SELECT `full_name`, `state`, `students`.`grade_id`, `students`.`class_id` FROM `students` WHERE `student_id` = ?";
        ResultSet rs1 = DatabaseManager.executeSQLResultSet(query1, data);
        if (rs1 != null) {
            try {
                while (rs1.next()) {
                    fullName.setText(rs1.getString(1));
                    state.setText(rs1.getString(2));
                    grade.setText(rs1.getString(3) + "st Primary - Group " + rs1.getString(4));
                }
            } catch (SQLException e) {

            }
        }

        String query2 = "SELECT `subjects`.`name`, `first_midterm_exam`, `first_final_exam`, `second_midterm_exam`, `second_final_exam` FROM `results` INNER JOIN `subjects` USING(`subject_id`) WHERE `student_id` = ?";
        ResultSet rs2 = DatabaseManager.executeSQLResultSet(query2, data);
        if (rs2 != null) {
            int position = 1;
            try {
                while (rs2.next()) {
                    /*Label subjectName = new Label(rs2.getString(1));
                    subjectName.setAlignment(Pos.CENTER_LEFT);
                    subjectName.getStyleClass().add("cell-day");*/
                    TextField subjectName = new TextField(rs2.getString(1));
                    TextField firstMidtermExam = new TextField(rs2.getString(2));
                    TextField firstFinalExam = new TextField(rs2.getString(3));
                    TextField secondMidtermExam = new TextField(rs2.getString(4));
                    TextField secondFinalExam = new TextField(rs2.getString(5));

                    subjectName.setAlignment(Pos.CENTER);
                    firstMidtermExam.setAlignment(Pos.CENTER);
                    firstFinalExam.setAlignment(Pos.CENTER);
                    secondMidtermExam.setAlignment(Pos.CENTER);
                    secondFinalExam.setAlignment(Pos.CENTER);

                    resultTable.add(subjectName, 0, position);
                    resultTable.add(firstMidtermExam, 1, position);
                    resultTable.add(firstFinalExam, 2, position);
                    resultTable.add(secondMidtermExam, 3, position);
                    resultTable.add(secondFinalExam, 4, position);

                    subjectName.setDisable(true);
                    firstMidtermExam.setDisable(true);
                    firstFinalExam.setDisable(true);
                    secondMidtermExam.setDisable(true);
                    secondFinalExam.setDisable(true);

                    position++;
                }
            } catch (SQLException ex) {
            }
        } else {
            resultTable.setVisible(false);
        }

        String query3 = "SELECT AVG(((`first_midterm_exam` + `first_final_exam` + `second_midterm_exam` + `second_final_exam`) / `full_mark`) * 100) FROM `results` INNER JOIN `subjects` USING(`subject_id`) WHERE `student_id` = ? GROUP BY `student_id`";
        ArrayList<String> output = DatabaseManager.getResultOneRow(query3, data, 1);
        if (output.size() != 0) {
            amount.setText("THE SUM OF MARKS IS " + output.get(0) + "% GREAT");
        } else {
            amount.setText("NO MARKS FOR THIS STUDENT");
        }
    }

    @FXML
    public void employeeOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

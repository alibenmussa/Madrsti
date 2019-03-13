package main.views.stages.employee.employeeShowClasses.employeeShowSchedule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeAddSubject.employeeAddSubjectController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowScheduleController {
    @FXML
    private VBox classesList;

    @FXML
    private Label name;

    @FXML
    private GridPane schedule;

    static String grade;
    static String nameGrade;
    static String clas;

    public void initialize(String gradeId, String gradeName, String className) {
        System.out.println("hello");
        grade = gradeId;
        nameGrade = gradeName;
        clas = className;
        name.setText("Group " + className + " - " + gradeName);
        ArrayList<String> data = new ArrayList<>();
        data.add(gradeId);
        data.add(className);
        ResultSet rs1 = DatabaseManager.executeSQLResultSet("SELECT `subjects`.`name`, `day`, `time`, `staff_id`, `schedules`.`subject_id` FROM `schedules` INNER JOIN `subjects` USING(`subject_id`) WHERE `schedules`.`grade_id` = ? AND `class_id` = ?", data);
        if (rs1 != null) {
            try {
                while (rs1.next()) {
                    String subjectName = rs1.getString(1);
                    int day = rs1.getInt(2);
                    int time = rs1.getInt(3);
                    String staffId = rs1.getString(4);
                    String subjectId = rs1.getString(5);

                    Button btn = new Button(subjectName);
                    btn.setWrapText(true);
                    btn.setTextAlignment(TextAlignment.CENTER);
                    btn.getStyleClass().addAll("gray-button", "cell-schedule");
                    btn.setOnAction(e -> {
                        ArrayList<String> data2 = new ArrayList<>();
                        data2.add(subjectId);
                        data2.add(staffId);
                        data2.add(gradeId);
                        data2.add(className);
                        boolean deleteSubject = Dialog.showConfirm("Delete Subject", "Do you want to delete " + subjectName + "?");
                        if (deleteSubject) {
                            int rowsAffected = DatabaseManager.executeSQLRows("DELETE FROM `schedules` WHERE `subject_id` = ? AND `staff_id` = ? AND `grade_id` = ? AND `class_id` = ?", data2);
                            if (rowsAffected > 0) {
                                btn.setVisible(false);
                            }
                        }
                    });
                    schedule.add(btn, day, time);
                }
            } catch (SQLException ex) {
            }
        }
    }

    @FXML
    void addSubject(ActionEvent event) {
        String path = "/main/views/stages/employee/employeeShowClasses/employeeShowSchedule/employeeAddSubject/employeeAddSubject.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        employeeAddSubjectController controller = loader.getController();
        controller.initialize(grade, clas);
        boolean addSubject = Dialog.showAndPass("Add Subject", loader.getRoot());
        if (addSubject) {
            initialize(grade, nameGrade, clas);

        }
    }

    @FXML
    void goBack(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowClasses/employeeShowClasses.fxml");
    }




}

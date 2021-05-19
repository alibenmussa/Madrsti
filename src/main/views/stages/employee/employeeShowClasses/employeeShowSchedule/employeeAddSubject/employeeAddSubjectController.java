package main.views.stages.employee.employeeShowClasses.employeeShowSchedule.employeeAddSubject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;
import main.views.stages.template.ComboForm;

import java.util.ArrayList;

public class employeeAddSubjectController {

    @FXML
    private ComboBox<ComboForm> subject;

    @FXML
    private ComboBox<ComboForm> teacher;

    @FXML
    private ComboBox day;

    @FXML
    private ComboBox time;

    private String grade;
    private String clas;

    public void initialize(String gradeID, String className) {
        grade = gradeID;
        clas = className;
        day.setVisibleRowCount(5);
        time.setVisibleRowCount(5);
        String subjectQuery = "SELECT DISTINCT `subject_id`, `name` FROM `subjects` WHERE `grade_id` = ?";
        String teacherQuery = "SELECT DISTINCT `staff_id`, `full_name` FROM `staff` WHERE `type` = 'Teacher'";
        ArrayList<String> data1 = new ArrayList<>();
        data1.add(gradeID);
//        DatabaseManager.addComboBoxData(subject, subjectQuery, data1);
        ComboFacade.ComboAddData("ComboBoxWithId",subject, subjectQuery, data1);
        ComboFacade.ComboAddData("ComboBoxWithId",teacher, teacherQuery, null);

    }

    @FXML
    void employeeSaveAddSubject(ActionEvent event) {
        ArrayList<String> data = new ArrayList<>();
        String selectedSubject = subject.getValue().getId();
        String selectedTeacher = teacher.getValue().getId();
        String selectedDay = String.valueOf(day.getSelectionModel().getSelectedIndex() + 1);
        String selectedTime = String.valueOf(time.getSelectionModel().getSelectedIndex() + 1);

        data.add(selectedSubject);
        data.add(selectedTeacher);
        data.add(grade);
        data.add(clas);
        data.add(selectedDay);
        data.add(selectedTime);

        int rs = DatabaseManager.executeSQLRows("INSERT INTO `schedules` VALUES(?, ?, ?, ?, ?, ?)", data);
        if (rs == 1) {
            Dialog.success = true;
            Dialog.closeDialogWindow();
        } else {
            System.out.println("Error Input");
        }
        Dialog.closeDialogWindow();
    }

    @FXML
    public void employeeCancelAddCancel(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

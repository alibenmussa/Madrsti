package main.views.stages.employee.employeeShowClasses.employeeAddClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;
import java.util.ArrayList;

public class employeeAddClassController {

    @FXML
    private TextField clas;

    @FXML
    private TextField capacity;

    @FXML
    private ComboBox grade;

    public void initialize() {
        String gradeQuery = "SELECT `name` FROM `grades`";
        ComboFacade.ComboAddData("ComboBox",grade, gradeQuery, null);

    }

    @FXML
    public void employeeSaveAddClass(ActionEvent event) {
        ArrayList<String> data = new ArrayList<>();
        String selectedGrade = String.valueOf(grade.getSelectionModel().getSelectedIndex() + 1);
        data.add(selectedGrade);
        data.add(clas.getText());
        data.add(capacity.getText());
        int rs = DatabaseManager.executeSQLRows("INSERT INTO `classes` VALUES(?, ?, ?)", data);
        if (rs == 1) {
            Dialog.success = true;
            Dialog.closeDialogWindow();
        } else {
            System.out.println("Error Input");
        }

    }

    @FXML
    public void employeeCancelAddClass(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

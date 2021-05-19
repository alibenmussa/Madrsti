package main.views.stages.employee.employeeShowStudents;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.admin.adminShowStudents.adminShowStudentsController;
import main.views.stages.employee.employeeShowStudents.employeeEditStudent.employeeEditStudentController;
import main.views.stages.employee.employeeShowStudents.employeeShowStudentInformation.employeeShowStudentInformationController;
import main.views.stages.employee.employeeShowStudents.employeeShowStudentResult.employeeShowStudentResultController;
import main.views.stages.template.ComboForm;
import main.views.stages.template.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<ComboForm> grades;

    @FXML
    private ComboBox<ComboForm> clas;
    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> name;

    @FXML
    private TableColumn<Student, String> grade;

    @FXML
    private TableColumn<Student, String> classs;

    @FXML
    private TableColumn operations;

    ObservableList<Student> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String gradeQuery = "SELECT `grade_id`, `name` FROM `grades`";
        ComboFacade.ComboAddData("ComboBoxWithId",grades, gradeQuery, null);
        grades.getItems().add(0, new ComboForm("-1", "All"));
        grades.getSelectionModel().selectFirst();
        clas.setDisable(true);
        clas.getItems().clear();
//        clas.disableProperty().bind(grades.valueProperty().isNull());
        grades.setOnAction(e -> {
            clas.setDisable(false);
            if (grades.getValue().getId() == "-1") {
                clas.setDisable(true);
                clas.getItems().clear();
                clas.setPromptText("Class");
            } else {
                clas.setDisable(false);
                ArrayList<String> data = new ArrayList<>();
                data.add(grades.getValue().getId());
                String query = "SELECT `class_id`, `class_id` FROM classes WHERE grade_id = ?";
                ComboFacade.ComboAddData("ComboBoxWithId",clas, query, data);
                clas.getItems().add(0, new ComboForm("-1", "All"));
                clas.getSelectionModel().selectFirst();
                clas.setOnAction(x -> {
                    setupTable(null);
                });
            }

            setupTable(null);
        });



            setupTable(null);
    }


    public void setupTable(String key) {
        String selectedGrade = grades.getValue().getId();
        String selectedClass = "-1";
        if (!clas.isDisable()){
            selectedClass = clas.getValue().getId();
        }

        studentsTable.getItems().clear();
        String query1 = null;
        String where = "";
        if (key != null) {
            where += "WHERE `students`.`full_name` LIKE '" + key + "%'";
            if (!selectedGrade.equals("-1")) {
                where += " AND students.grade_id = '" + selectedGrade + "'";
                if (!selectedClass.equals("-1")) {
                    where += " AND `students`.`class_id` = '" + selectedClass + "'";
                }
            }
        } else {
            if (!selectedGrade.equals("-1")) {
                where += " WHERE students.grade_id = '" + selectedGrade + "'";
                if (!selectedClass.equals("-1")) {
                    where += " AND `students`.`class_id` = '" + selectedClass + "'";
                }
            }
        }
        name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade_id"));
        classs.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        operations.setCellFactory((Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>) p -> new ButtonsCell(this));
        studentsTable.setItems(adminShowStudentsController.getStudentsList(where));
    }

    @FXML
    void keySearch(KeyEvent event) {
        setupTable(search.getText());
    }

    @FXML
    void employeeAddStudent(ActionEvent event) {
        String path = "/main/views/stages/employee/employeeShowStudents/employeeAddStudent/employeeAddStudent.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {

        }

        boolean addStudent = Dialog.showAndPass("Add Student", loader.getRoot());
        if (addStudent) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

    public void employeeEditStudent(String id) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/views/stages/employee/employeeShowStudents/employeeEditStudent/employeeEditStudent.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        employeeEditStudentController controller = loader.getController();
        controller.initialize(id);

        boolean editStudent = Dialog.showAndPass("Edit Student", loader.getRoot());
        if (editStudent) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

    public void employeeShowStudent(String id) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/views/stages/employee/employeeShowStudents/employeeShowStudentInformation/employeeShowStudentInformation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }

        employeeShowStudentInformationController controller = loader.getController();
        controller.initialize(id);
        boolean resultStudent = Dialog.showAndPass("Student Information", loader.getRoot());
        if (resultStudent) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

    public void employeeShowResult(String id) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/views/stages/employee/employeeShowStudents/employeeShowStudentResult/employeeShowStudentResult.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }

        employeeShowStudentResultController controller = loader.getController();
        controller.initialize(id);
        boolean showStudent = Dialog.showAndPass("Student Result", loader.getRoot());
        if (showStudent) {
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

    public void employeeDeleteStudent(String id) {
        ArrayList<String> list = new ArrayList<>();
        list.add(id);
        String query = "DELETE FROM `students` WHERE `student_id` = ?";
        boolean deleteStudent = Dialog.showConfirm("Delete Student","Are you sure you want to delte this student?");
        if(deleteStudent) {
            int affectedRows = DatabaseManager.executeSQLRows(query, list);
            if (affectedRows > 0) {
                setupTable(null);
            }
        }
    }


}
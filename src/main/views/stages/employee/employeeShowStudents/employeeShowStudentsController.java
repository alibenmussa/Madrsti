package main.views.stages.employee.employeeShowStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.employee.employeeShowStudents.employeeEditStudent.employeeEditStudentController;
import main.views.stages.employee.employeeShowStudents.employeeShowStudentInformation.employeeShowStudentInformationController;
import main.views.stages.template.Students;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox year;

    @FXML
    private ComboBox clas;
    @FXML
    private TableView<Students> studentsTable;

    @FXML
    private TableColumn<Students, String> name;

    @FXML
    private TableColumn<Students, String> grade;

    @FXML
    private TableColumn<Students, String> classs;

    @FXML
    private TableColumn operations;

    ObservableList<Students> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
    }


    public void setupTable() {
        name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade_id"));
        classs.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        operations.setCellFactory((Callback<TableColumn<Students, Boolean>, TableCell<Students, Boolean>>) p -> new ButtonsCell(this));
        studentsTable.setItems(getStudentList());
    }


    public ObservableList<Students> getStudentList() {
        data  = FXCollections.observableArrayList();
        String query = "SELECT * FROM `students`";
        ResultSet rw = DatabaseManager.executeSQLResultSet(query,null);
        try {
            while (rw.next()) {
                Students students = new Students();
                students.setStu_id(rw.getString("student_id"));
                students.setPhone_number(rw.getInt("phone_number"));
                students.setBirthday(rw.getDate("birthday"));
                students.setGrade_id(rw.getString("grade_id"));
                students.setFull_name(rw.getString("full_name"));
                students.setState(rw.getString("state"));
                students.setGender(rw.getString("gender"));
                students.setLiving_address(rw.getString("address"));
                students.setClass_id(rw.getString("class_id"));
                students.setNationality(rw.getString("nationality"));
                students.setHealth_status(rw.getString("health_status"));
                students.setNotes(rw.getString("notes"));
                students.setRelative_name(rw.getString("relative_name"));
                students.setRelation(rw.getString("relation"));

                data.add(students);
            }
        } catch (Exception e){

        }
        return data;
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
        boolean showStudent = Dialog.showAndPass("Student Information", loader.getRoot());
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
                setupTable();
            }
        }
    }
}
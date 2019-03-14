package main.views.stages.admin.adminShowStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.admin.adminShowStudents.adminShowStudentInformation.adminShowStudentInformationController;
import main.views.stages.employee.employeeShowStudents.employeeShowStudentResult.employeeShowStudentResultController;

import main.views.stages.template.ComboForm;
import main.views.stages.template.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<ComboForm> grades;

    @FXML
    private String reihe;

    @FXML
    private String teacher;

    @FXML
    private String parallel;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String gradeQuery = "SELECT `grade_id`, `name` FROM `grades`";
        DatabaseManager.addComboBoxDataWithId(grades, gradeQuery, null);
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
                DatabaseManager.addComboBoxDataWithId(clas, query, data);
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

    public static ObservableList<Student> getStudentsList(String whereQuery) {
        ObservableList<Student> data = FXCollections.observableArrayList();

        String query = "SELECT * FROM `students` INNER JOIN `grades` USING(`grade_id`) " + whereQuery;
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStu_id(rs.getString("student_id"));
                    student.setPhone_number(rs.getInt("phone_number"));
                    student.setBirthday(rs.getDate("birthday"));
                    student.setFull_name(rs.getString("full_name"));
                    student.setState(rs.getString("state"));
                    student.setGender(rs.getString("gender"));
                    student.setLiving_address(rs.getString("address"));
                    student.setClass_id(rs.getString("class_id"));
                    student.setNationality(rs.getString("nationality"));
                    student.setHealth_status(rs.getString("health_status"));
                    student.setNotes(rs.getString("notes"));
                    student.setRelative_name(rs.getString("relative_name"));
                    student.setRelation(rs.getString("relation"));



                    //grade name ----> ya nooop :))
                    student.setGrade_id(rs.getString("name"));


                    data.add(student);
                }
            } catch (SQLException ex) {
            }
        }
        return data;
    }

    public void showStudent(String Id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/admin/adminShowStudents/adminShowStudentInformation/adminShowStudentInformation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        adminShowStudentInformationController controller = loader.getController();
        controller.initialize(Id);
        boolean showStudent = Dialog.showAndPass("Show Student", loader.getRoot());
        if (showStudent) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStudent/adminShowStudent.fxml");
        }

    }
    public void adminShowResult(String id) {
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

}

package main.views.stages.admin.adminShowStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import main.views.stages.admin.adminShowStudents.adminShowStudentInformation.adminShowStudentInformationController;
import main.views.stages.template.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<?> year;

    @FXML
    private String reihe;

    @FXML
    private String teacher;

    @FXML
    private String parallel;

    @FXML
    private ComboBox<?> clas;

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
        setUpTable();
    }


    public void setUpTable() {



        name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade_id"));
        classs.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        operations.setCellFactory((Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>) p -> new ButtonsCell(this));



        studentsTable.setItems(getStudentsList(""));
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

}

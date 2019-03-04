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
import main.views.stages.template.Students;

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
    private TableView<Students> studentsTable;

    @FXML
    private TableColumn<Students, String> name;

    @FXML
    private TableColumn<Students, String> grade;

    @FXML
    private TableColumn<Students, String> classs;

    @FXML
    private TableColumn<Students, Button> operations;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTable();
    }


    public void setUpTable() {



        this.name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        this.grade.setCellValueFactory(new PropertyValueFactory<>("grade_id"));
        this.classs.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        this.operations.setCellValueFactory(new PropertyValueFactory<Students, Button>(""));

        Callback<TableColumn<Students, Button>, TableCell<Students, Button>> cellFactory
                = //
                new Callback<TableColumn<Students, Button>, TableCell<Students, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<Students, Button> param) {
                        final TableCell<Students, Button> cell = new TableCell<Students, Button>() {

                            final Button showBtn = new Button("E");


                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    showBtn.setOnAction(event -> {
                                        Students selected = getTableView().getItems().get(getIndex());
                                        showStudent(selected.getStu_id());
                                    });
                                    setGraphic(showBtn);


                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        operations.setCellFactory(cellFactory);

        studentsTable.setItems(getStudentsList());
    }

    public static ObservableList<Students> getStudentsList() {
        ObservableList<Students> data = FXCollections.observableArrayList();

        String query = "SELECT * FROM `students` INNER JOIN `grades` USING(`grade_id`)";
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Students students = new Students();
                    students.setStu_id(rs.getString("student_id"));
                    students.setPhone_number(rs.getInt("phone_number"));
                    students.setBirthday(rs.getDate("birthday"));
                    students.setFull_name(rs.getString("full_name"));
                    students.setState(rs.getString("state"));
                    students.setGender(rs.getString("gender"));
                    students.setLiving_address(rs.getString("address"));
                    students.setClass_id(rs.getString("class_id"));
                    students.setNationality(rs.getString("nationality"));
                    students.setHealth_status(rs.getString("health_status"));
                    students.setNotes(rs.getString("notes"));
                    students.setRelative_name(rs.getString("relative_name"));
                    students.setRelation(rs.getString("relation"));



                    //grade name ----> ya nooop :))
                    students.setGrade_id(rs.getString("name"));


                    data.add(students);
                }
            } catch (SQLException ex) {
            }
        }
        return data;
    }

    private void showStudent(String Id) {
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

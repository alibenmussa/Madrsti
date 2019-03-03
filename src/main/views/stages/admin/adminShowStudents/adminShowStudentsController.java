package main.views.stages.admin.adminShowStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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

    ObservableList<Students> data;

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
                                        ShowStudent(selected.getStu_id());
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

        studentsTable.setItems(getStudentList());





    }


    public ObservableList<Students> getStudentList() {

        data  = FXCollections.observableArrayList();

        String query = "SElECT * FROM `students`";
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


        }catch (Exception e){
            e.printStackTrace();

        }
        return data;
    }
    private void ShowStudent(String Id) {
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

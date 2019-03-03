package main.views.stages.employee.employeeShowStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import main.views.stages.employee.employeeShowStudents.employeeEditStudent.employeeEditStudentController;
import main.views.stages.employee.employeeShowStudents.employeeShowStudentInformation.employeeShowStudentInformationController;
import main.views.stages.template.Staff;
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
    private TableColumn<Students, Button> operations;

    ObservableList<Students> data;

    public employeeShowStudentsController() {
    }


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

                            final Button editBtn = new Button("E");
                            final Button deleteBtn = new Button("D");
                            final Button showBtn = new Button("S");
                            HBox h = new HBox(editBtn,deleteBtn,showBtn);


                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    deleteBtn.setOnAction(event -> {
                                        Students selectedItem = getTableView().getItems().get(getIndex());
                                        System.out.println(selectedItem  == null);
                                        ArrayList<String> list = new ArrayList<>();
                                        list.add(selectedItem.getStu_id());
                                        System.out.println(list);
                                        String query = "DELETE FROM `students` WHERE student_id = ?";
                                        if(Dialog.showConfirm("blah","delete")) {
                                            int affectedRows = DatabaseManager.executeSQLRows(query, list);
                                        }
                                        setUpTable();
                                    });
                                    editBtn.setOnAction(event -> {
                                        Students Item = getTableView().getItems().get(getIndex());
                                        EditStaff(Item.getStu_id());
                                    });
                                    showBtn.setOnAction(event -> {
                                        Students selected = getTableView().getItems().get(getIndex());
                                        ShowStaff(selected.getStu_id());
                                    });
                                    setGraphic(h);


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
                System.out.println("blahblah");
                Students students = new Students();
                students.setStu_id(rw.getString("student_id"));
                students.setPhone_number(rw.getInt("phone_number"));
                students.setBirthday(rw.getDate("birthday"));
                students.setEmail(rw.getString("email"));
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
                int t = rw.getInt("grade_id");
                if (t == 1)
                    students.setGrade_id("1st Primary");
                else if (t == 2)
                    students.setGrade_id("2st Primary");
                else if (t == 3)
                    students.setGrade_id("3st Primary");
                else if (t == 4)
                    students.setGrade_id("4st Primary");
                else if (t == 5)
                    students.setGrade_id("5st Primary");
                else if (t == 6)
                    students.setGrade_id("6st Primary");

                data.add(students);
            }


        }catch (Exception e){
            e.printStackTrace();

        }
        return data;
    }

    @FXML
    void employeeAddStudent(ActionEvent event) throws Exception {
        String path = "/main/views/stages/employee/employeeShowStudents/employeeAddStudent/employeeAddStudent.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        /*adminAddStaffController controller = loader.getController();
        controller.initialize();*/
        boolean addStudent = Dialog.showAndPass("Add Student", loader.getRoot());
        if (addStudent) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

    private void EditStaff(String Id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/employee/employeeShowStudents/employeeEditStudent/employeeEditStudent.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        employeeEditStudentController controller = loader.getController();
        controller.initialize(Id);
        boolean editStudent = Dialog.showAndPass("Edit Student", loader.getRoot());
        if (editStudent) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }
    private void ShowStaff(String Id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/employee/employeeShowStudents/employeeShowStudentInformation/employeeShowStudentInformation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        employeeShowStudentInformationController controller = loader.getController();
        controller.initialize(Id);
        boolean showStudent = Dialog.showAndPass("Show Student", loader.getRoot());
        if (showStudent) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/employee/employeeShowStudents/employeeShowStudents.fxml");
        }

    }

}

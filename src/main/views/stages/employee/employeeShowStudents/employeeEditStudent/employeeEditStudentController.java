package main.views.stages.employee.employeeShowStudents.employeeEditStudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DatabaseManager;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;
import main.views.stages.template.Students;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeEditStudentController  {
    @FXML
    private TextField fullName;

    @FXML
    private DatePicker birthDay;

    @FXML
    private ComboBox state;

    @FXML
    private ComboBox gender;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField address;

    @FXML
    private TextField relative;

    @FXML
    private TextField id;

    @FXML
    private TextField nationality;

    @FXML
    private TextField relation;

    @FXML
    private TextField phoneNumber;

    @FXML
    private ComboBox year;

    @FXML
    private ComboBox clas;

    @FXML
    private ComboBox healthStatus;

    @FXML
    private TextArea notes;

    private Date Bday;


    String std_id;
    private File selectedImage = null;


    public void initialize(String ID) {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto(), 100, 100, false, false));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        userPhoto.setFitWidth(100);
        std_id = ID;


        String gradeQuery = "SELECT `name` FROM `grades`";
        DatabaseManager.addComboBoxData(year, gradeQuery, null);
        clas.disableProperty().bind(year.valueProperty().isNull());
        String classquery = "select  classes.class_id from students inner join classes using(grade_id) where student_id ="+std_id ;
        DatabaseManager.addComboBoxData(clas, classquery, null);





        String query = "SELECT * FROM `students` WHERE `student_id` =" +std_id;
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        try {
            while (rs.next()) {


                id.setText(rs.getString("student_id"));
                fullName.setText(rs.getString("full_name"));
                Bday = rs.getDate("birthday");
                birthDay.setValue(Bday.toLocalDate());
                address.setText(rs.getString("address"));
                nationality.setText(rs.getString("nationality"));
                phoneNumber.setText(rs.getString("phone_number"));
                relative.setText(rs.getString("relative_name"));
                relation.setText(rs.getString("relation"));
                notes.setText(rs.getString("notes"));
                state.setValue(rs.getString("state"));
                gender.setValue(rs.getString("gender"));
                healthStatus.setValue(rs.getString("health_status"));
                year.setValue(rs.getString("grade_id")+"st Primary");
                clas.setValue(rs.getString("class_id"));


            }

        } catch (SQLException e) {
        }
    }



    @FXML
    void gradeAction(ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT class_id FROM classes WHERE grade_id = ?";
        int index = year.getSelectionModel().getSelectedIndex() +1;
        list.add(String.valueOf(index));
        DatabaseManager.addComboBoxData(clas, query, list);
    }





    @FXML
    void employeeCancelEditStudent(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void employeeSaveEditStudent(ActionEvent event) {
//        ControllerFunctions.uploadPhotoToUsersFile(selectedImage);

        ArrayList<String> data = new ArrayList<>();
        String StudentId = id.getText();
        String FullName = fullName.getText();
        String BirthDay = birthDay.getValue().toString();
        String Address = address.getText();
        String Nationality = nationality.getText();
        String PhoneNumber = phoneNumber.getText();
        String Relative = relative.getText();
        String Relation = relation.getText();
        String Notes = notes.getText();
        String Classs =clas.getSelectionModel().getSelectedItem().toString();
        String Gender =gender.getSelectionModel().getSelectedItem().toString();
        String HealthStatus =healthStatus.getSelectionModel().getSelectedItem().toString();
        String State =state.getSelectionModel().getSelectedItem().toString();
        String Grade =String.valueOf(year.getSelectionModel().getSelectedIndex() +1);


        data.add(StudentId);
        data.add(FullName);
        data.add(State);
        data.add(Gender);
        data.add(BirthDay);
        data.add(Address);
        data.add(Nationality);
        data.add(Relative);
        data.add(Relation);
        data.add(PhoneNumber);
        data.add(Grade);
        data.add(Classs);
        data.add(HealthStatus);
        data.add(Notes);

        System.out.println(data);
        String query = "UPDATE `students` SET `student_id`= ?,`full_name`=?,`state`=?,`gender`=?,`birthday`=?,`address`=?,"+
                "`nationality`=?,`relative_name`=?,`relation`=?,`phone_number`=?,`grade_id`=?,`class_id`=?,`health_status`=?,"+
                "`notes`=? WHERE `student_id`= "+std_id;
        int affectedrow = DatabaseManager.executeSQLRows(query,data);
        System.out.println(affectedrow);
        if (affectedrow > 0){
            Dialog.success = true;
            Dialog.closeDialogWindow();

        }

    }

    @FXML
    public void uploadPhoto(ActionEvent event) throws Exception {
        selectedImage = ControllerFunctions.getPhotoFromUser(event, userPhoto);

    }
}

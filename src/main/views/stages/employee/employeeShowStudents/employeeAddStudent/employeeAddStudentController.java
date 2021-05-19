package main.views.stages.employee.employeeShowStudents.employeeAddStudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DB.ComboFacade;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class employeeAddStudentController implements Initializable {

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField fullName;

    @FXML
    private ComboBox state;

    @FXML
    private DatePicker birthDay;

    @FXML
    private ComboBox gender;

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



    private File selectedImage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image("/main/assests/images/users/default.jpg", 100, 100, false, false));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        userPhoto.setFitWidth(100);

        String gradeQuery = "SELECT `name` FROM `grades`";
        ComboFacade.ComboAddData("ComboBox",year, gradeQuery, null);
        clas.disableProperty().bind(year.valueProperty().isNull());


    }

    @FXML
    void gradeAction(ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        String query = "SELECT class_id FROM classes WHERE grade_id = ?";
        int index = year.getSelectionModel().getSelectedIndex() + 1;
        list.add(String.valueOf(index));
        ComboFacade.ComboAddData("ComboBox",clas, query, list);
    }


    @FXML
    void adminCancelAddStaff(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void adminSaveAddStaff(ActionEvent event) {
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
        String query = "INSERT INTO `students`(`student_id`, `full_name`, `state`, `gender`, `birthday`, `address`, `nationality`,"+
                "`relative_name`, `relation`, `phone_number`,  `grade_id`, `class_id`, `health_status`, `notes`)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        userPhotoCircle.setClip(new Circle(50, 50, 50));


    }


}

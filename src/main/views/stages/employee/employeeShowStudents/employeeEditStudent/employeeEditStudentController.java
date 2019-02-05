package main.views.stages.employee.employeeShowStudents.employeeEditStudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class employeeEditStudentController implements Initializable {
    @FXML
    private TextField fullName;

    @FXML
    private DatePicker birthDay;

    @FXML
    private ComboBox<?> state;

    @FXML
    private ComboBox<?> gender;

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
    private ComboBox<?> year;

    @FXML
    private ComboBox<?> clas;

    @FXML
    private ComboBox<?> healthStatus;

    @FXML
    private TextArea notes;





    private File selectedImage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto(), 100, 100, false, false));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        userPhoto.setFitWidth(100);
        setUserData();
    }

    public void setUserData() {
        String query = "SELECT * FROM"; //جلب البيانات من view
        fullName.setText("Yousef Breaka");
//        type
        userPhoto.setImage(null);
        userPhoto.setImage(new Image("/main/assests/images/users/user_02.jpg"));
        birthDay.setValue(LocalDate.now());
//        gender
        id.setText("1234567890");
        address.setText("Anonymous, Tripoli, Libya");
        nationality.setText("Libyan");
        relative.setText("Abdalkarim");
        relation.setText("Father");
        phoneNumber.setText("0915555555");

    }





    @FXML
    void employeeCancelEditStudent(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void employeeSaveEditStudent(ActionEvent event) {
//        ControllerFunctions.uploadPhotoToUsersFile(selectedImage);

    }

    @FXML
    public void uploadPhoto(ActionEvent event) throws Exception {
        selectedImage = ControllerFunctions.getPhotoFromUser(event, userPhoto);

    }
}

package main.views.stages.admin.adminShowStaff.adminEditStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class adminEditStaffController implements Initializable {
    @FXML
    private TextField fullName;

    @FXML
    private DatePicker birthDay;

    @FXML
    private ComboBox<?> type;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField address;

    @FXML
    private TextField nationality;

    @FXML
    private TextField jobDescription;

    @FXML
    private TextField id;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField education;

    @FXML
    private TextField degree;

    @FXML
    private TextField major;

    @FXML
    private ComboBox<?> graduateYear;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

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
        fullName.setText("Ali Ben Mussa");
//        type
        userPhoto.setImage(null);
        userPhoto.setImage(new Image("/main/assests/images/users/user_01.jpg"));

        birthDay.setValue(LocalDate.now());
//        gender
        id.setText("1234567890");
        jobDescription.setText("Graphic Designer");
        address.setText("Anonymous, Tripoli, Libya");
        nationality.setText("Libyan");
        phoneNumber.setText("0915555555");
        email.setText("alibenmussa@gmail.com");

        major.setText("Software Engineering");
        degree.setText("Bechlorice");
        education.setText("University of Tripoli");

        username.setText("alibenmussa");
        password.setText("1234");

    }


    @FXML
    void clickCreateAccount(ActionEvent event) {
        ControllerFunctions.clickCreateAccount(event, username, password);
    }


    @FXML
    void adminCancelEditStaff(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void adminSaveEditStaff(ActionEvent event) {
//        ControllerFunctions.uploadPhotoToUsersFile(selectedImage);

    }

    @FXML
    public void uploadPhoto(ActionEvent event) throws Exception {
        selectedImage = ControllerFunctions.getPhotoFromUser(event, userPhoto);

    }
}

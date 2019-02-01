package main.views.stages.admin.adminShowStaff.adminEditStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import main.StagesManager;
import main.views.dialog.Dialog;

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
    private Group userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField address;

    @FXML
    private TextField nationality;

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
    private TextField username;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto()));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        setUserData();
    }

    private void setUserData() {
        String query = "SELECT * FROM"; //جلب البيانات من view
        fullName.setText("Ali Ben Mussa");
//        type
        userPhoto.setImage(new Image("/main/assets/images/users/user_01.jpg"));
        birthDay.setValue(LocalDate.now());
//        gender
        address.setText("Anonymous, Tripoli, Libya");
        nationality.setText("Libyan");
        phoneNumber.setText("0915555555");
        email.setText("alibenmussa@gmail.com");

        major.setText("Software Engineering");
        degree.setText("Bechlorice");
        education.setText("University of Tripoli");


    }

    @FXML
    void clickCreateAccount(ActionEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            username.setDisable(false);
            password.setDisable(false);
        } else {
            username.setDisable(true);
            password.setDisable(true);
        }
    }

    @FXML
    void adminCancelAddStaff(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void adminSaveAddStaff(ActionEvent event) {

    }

    @FXML
    public void uploadPhoto(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        File selectedImage = chooser.showOpenDialog(StagesManager.window);
        System.out.println(selectedImage);

    }
}

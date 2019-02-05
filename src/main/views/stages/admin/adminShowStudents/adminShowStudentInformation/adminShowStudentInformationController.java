package main.views.stages.admin.adminShowStudents.adminShowStudentInformation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class adminShowStudentInformationController implements Initializable {
    @FXML
    private Label fullName;

    @FXML
    private TextField birthDay;

    @FXML
    private Label type;

    @FXML
    private TextField gender;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField address;

    @FXML
    private TextField nationality;

    @FXML
    private Label jobDescription;

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
    private TextField graduateYear;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);
        setUserData();
    }

    public void setUserData() {
        String query = "SELECT * FROM"; //جلب البيانات من view
        fullName.setText("Ali Ben Mussa");
        type.setText("Employee");
        jobDescription.setText("Graphic Designer");
        userPhoto.setImage(new Image("/main/assests/images/users/user_01.jpg"));

        gender.setText("Male");
        birthDay.setText("16/9/1999");
        id.setText("99999999999");
        address.setText("Anonymous, Tripoli, Libya");
        nationality.setText("Libyan");
        phoneNumber.setText("0915555555");
        email.setText("alibenmussa@gmail.com");

        major.setText("Software Engineering");
        degree.setText("Bechlorice");
        education.setText("University of Tripoli");
        graduateYear.setText("2020");

    }


    @FXML
    public void adminOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

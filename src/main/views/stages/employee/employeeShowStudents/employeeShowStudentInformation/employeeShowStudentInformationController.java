package main.views.stages.employee.employeeShowStudents.employeeShowStudentInformation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeShowStudentInformationController{
    @FXML
    private Label fullName;

    @FXML
    private Label jobDescription;

    @FXML
    private Label state;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField address;

    @FXML
    private TextField id;

    @FXML
    private TextField birthDay;

    @FXML
    private TextField gender;

    @FXML
    private TextField nationality;

    @FXML
    private TextField relativeName;

    @FXML
    private TextField relativeRelation;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField degree;

    @FXML
    private TextArea notes;




    public void initialize(String ID) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);
        setUserData();
    }

    public void setUserData() {

    }


    @FXML
    public void employeeOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

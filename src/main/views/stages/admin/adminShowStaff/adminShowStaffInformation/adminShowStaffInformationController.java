package main.views.stages.admin.adminShowStaff.adminShowStaffInformation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DatabaseManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class adminShowStaffInformationController {
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



    public void initialize(String stf_id) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        //userPhoto.setImage(ControllerFunctions.readFromUsersFile(stf_id));
        userPhoto.setFitWidth(120);


        String querey = "SELECT * FROM `staff` WHERE `staff_id` =" +stf_id;
        ResultSet rs = DatabaseManager.executeSQLResultSet(querey,null);
        try {
            while (rs.next()) {
                fullName.setText(String.valueOf(rs.getString("full_name")));
                type.setText(rs.getString("type"));
                birthDay.setText(String.valueOf(rs.getDate("birthday")));
                gender.setText(rs.getString("gender"));
                id.setText(String.valueOf(rs.getInt("staff_id")));
                jobDescription.setText(rs.getString("job_description"));
                address.setText(rs.getString("address"));
                nationality.setText(rs.getString("nationality"));
                phoneNumber.setText(String.valueOf(rs.getInt("phone_number")));
                email.setText(rs.getString("email"));
                major.setText(rs.getString("major"));
                degree.setText(rs.getString("degree"));
                education.setText(rs.getString("education"));
                graduateYear.setText(rs.getString("graduate_year"));
            }

        } catch (SQLException e) {
        }
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

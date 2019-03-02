package main.views.stages.admin.adminShowStudents.adminShowStudentInformation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DatabaseManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminShowStudentInformationController  {
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



    public void initialize(String Id) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);

        String querey = "SELECT * FROM `students` WHERE `student_id` =" +Id;
        ResultSet rs = DatabaseManager.executeSQLResultSet(querey,null);
        try {
            while (rs.next()) {
                fullName.setText(String.valueOf(rs.getString("full_name")));
                userPhoto.setImage(new Image("/main/assests/images/users/user_01.jpg"));
                birthDay.setText(String.valueOf(rs.getDate("birthday")));
                gender.setText(rs.getString("gender"));
                id.setText(String.valueOf(rs.getInt("student_id")));
                address.setText(rs.getString("address"));
                nationality.setText(rs.getString("nationality"));
                phoneNumber.setText(String.valueOf(rs.getInt("phone_number")));
                notes.setText(rs.getString("notes"));
                relativeName.setText(rs.getString("relative_name"));
                degree.setText(rs.getString("degree"));
                relativeRelation.setText(rs.getString("relation"));
                state.setText(rs.getString("state"));
            }

        } catch (SQLException e) {
        }

    }




    @FXML
    public void adminOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

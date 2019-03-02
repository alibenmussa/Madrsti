package main.views.stages.teacher.teacherShowProfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Button;
import main.DatabaseManager;
import main.StagesManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class teacherShowProfileController implements Initializable {
    @FXML
    private Label fullName;

    @FXML
    private Label jobDescription;

    @FXML
    private Label type;

    @FXML
    private AnchorPane userPhotoCircle;

    @FXML
    private ImageView userPhoto;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

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
    private TextField graduateYear;

    @FXML
    private TextField major;

    @FXML
    private Button edit;

    @FXML
    private Button cancelEdit;


    private boolean isEditMode;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isEditMode = false;
        cancelEdit.setVisible(false);

        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);
        setUserData();
    }

    public void setUserData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(StagesManager.userId);
        String query = "SELECT * FROM `staff` INNER JOIN `users` ON `staff_id` = `user_id` WHERE `staff_id` = ?";
        ResultSet rs = DatabaseManager.executeSQLResultSet(query, data);
        if (rs != null) {
            try {
                while(rs.next()) {
                    fullName.setText(rs.getString("full_name"));
                    type.setText(rs.getString("type"));
                    jobDescription.setText(rs.getString("job_description"));
                    userPhoto.setImage(new Image("/main/assests/images/users/user_01.jpg"));

                    username.setText(rs.getString("username"));
                    password.setText(rs.getString("password"));

                    address.setText(rs.getString("address"));
                    nationality.setText(rs.getString("nationality"));
                    phoneNumber.setText(rs.getString("phone_number"));
                    email.setText(rs.getString("email"));

                    major.setText(rs.getString("major"));
                    degree.setText(rs.getString("degree"));
                    education.setText(rs.getString("education"));
                    graduateYear.setText(rs.getString("graduate_year"));
                }
            } catch (SQLException ex) {

            }
        }


    }


    @FXML
    void editInformation(ActionEvent event) {
        if (!isEditMode) {
            username.setDisable(false);
            password.setDisable(false);

            address.setDisable(false);
            nationality.setDisable(false);
            phoneNumber.setDisable(false);
            email.setDisable(false);

            major.setDisable(false);
            degree.setDisable(false);
            education.setDisable(false);
            graduateYear.setDisable(false);

            edit.setText("SAVE");
            cancelEdit.setVisible(true);
            isEditMode = true;
        } else {
            saveNewInformation();

            username.setDisable(true);
            password.setDisable(true);

            address.setDisable(true);
            nationality.setDisable(true);
            phoneNumber.setDisable(true);
            email.setDisable(true);

            major.setDisable(true);
            degree.setDisable(true);
            education.setDisable(true);
            graduateYear.setDisable(true);

            edit.setText("EDIT");
            cancelEdit.setVisible(false);
            isEditMode = false;
        }
    }

    @FXML
    void teacherCancelEditInformation(ActionEvent event) {
        setUserData();

        username.setDisable(true);
        password.setDisable(true);

        address.setDisable(true);
        nationality.setDisable(true);
        phoneNumber.setDisable(true);
        email.setDisable(true);

        major.setDisable(true);
        degree.setDisable(true);
        education.setDisable(true);
        graduateYear.setDisable(true);

        edit.setText("EDIT");
        cancelEdit.setVisible(false);
        isEditMode = false;
    }

    private void saveNewInformation() {
        ArrayList<String> data = new ArrayList<>();
        data.add(username.getText());
        data.add(password.getText());
        data.add(address.getText());
        data.add(nationality.getText());
        data.add(phoneNumber.getText());
        data.add(email.getText());
        data.add(major.getText());
        data.add(degree.getText());
        data.add(education.getText());
        data.add(graduateYear.getText());
        data.add(StagesManager.userId);

        String query = "UPDATE `staff` INNER JOIN `users` ON `staff_id` = `user_id` SET `username` = ?, `password` = ?, `address` = ?, `nationality` = ?, `phone_number` = ?, `email` = ?, `major` = ?, `degree` = ?, `education` = ?, `graduate_year` = ? WHERE `staff_id` = ?";
        int rowsAffected = DatabaseManager.executeSQLRows(query, data);
        System.out.println(rowsAffected);
        if (rowsAffected > 0) {
            Dialog.showAlert("Successful", "Your data has been updated!");
        } else {

        }
    }


}

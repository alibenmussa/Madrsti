package main.views.stages.admin.adminShowStudents.adminShowStudentInformation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DB.DatabaseManager;
import main.views.dialog.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private String std_id;

    public void initialize(String Id) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);
        std_id =Id;

        String query = "SELECT * FROM `students` WHERE `student_id` =" +std_id;
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        try {
            while (rs.next()) {


                id.setText(rs.getString("student_id"));
                fullName.setText(rs.getString("full_name"));
                birthDay.setText(String.valueOf(rs.getDate("birthday")));
                address.setText(rs.getString("address"));
                nationality.setText(rs.getString("nationality"));
                phoneNumber.setText(rs.getString("phone_number"));
                relativeName.setText(rs.getString("relative_name"));
                relativeRelation.setText(rs.getString("relation"));
                notes.setText(rs.getString("notes"));
                state.setText(rs.getString("state"));
                gender.setText(rs.getString("gender"));
                jobDescription.setText(rs.getString("grade_id")+"st Primary - Group " +rs.getString("class_id"));
                degree.setText(rs.getString("grade_id")+"st Primary");

            }

        } catch (SQLException e) {
        }

    }




    @FXML
    public void adminOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

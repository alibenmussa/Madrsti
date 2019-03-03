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
import main.DatabaseManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        private String std_id;

    public void initialize(String ID) {
        userPhotoCircle.setClip(new Circle(60, 60, 60));
        userPhoto.setFitWidth(120);

        std_id =ID;

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

                int t = rs.getInt("grade_id");
                if (t == 1)
                    degree.setText("1st Primary");
                else if (t == 2)
                    degree.setText("2st Primary");
                else if (t == 3)
                    degree.setText("3st Primary");
                else if (t == 4)
                    degree.setText("4st Primary");
                else if (t == 5)
                    degree.setText("5st Primary");
                else if (t == 6)
                    degree.setText("6st Primary");
            }

        } catch (SQLException e) {
        }
    }

    public void setUserData() {

    }


    @FXML
    public void adminOk(ActionEvent event) {
        Dialog.closeDialogWindow();
    }
}

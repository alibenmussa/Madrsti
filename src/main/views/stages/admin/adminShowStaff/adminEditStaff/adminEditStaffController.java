package main.views.stages.admin.adminShowStaff.adminEditStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import main.DatabaseManager;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;
import main.views.stages.template.ComboSubject;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminEditStaffController  {
    @FXML
    private TextField fullName;

    @FXML
    private DatePicker birthDay;

    @FXML
    private ComboBox type;

    @FXML
    private ComboBox gender;

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
    private ComboBox graduateYear;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    private Date bDay;

    private File selectedImage = null;
    private String sta_Id;

    public void initialize(String stf_id) {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto(), 100, 100, false, false));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        userPhoto.setFitWidth(100);
        sta_Id = stf_id;



        ArrayList arrayList = new ArrayList();
        int currentYear = LocalDateTime.now().getYear();
        for (int i = 1950; i <= currentYear; i++) {
            arrayList.add(i);
        }
        graduateYear.getItems().addAll(arrayList);

        /*String gradeQuery = "SELECT DISTINCT `type` FROM `staff`";
        DatabaseManager.addComboBoxData(type, gradeQuery, null);*/


        String query = "SELECT * FROM `staff` WHERE `staff_id` =" +sta_Id;
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        try {
            while (rs.next()) {
                fullName.setText(String.valueOf(rs.getString("full_name")));
                userPhoto.setImage(null);
                userPhoto.setImage(new Image("/main/assests/images/users/user_01.jpg"));
                bDay = rs.getDate("birthday");
                birthDay.setValue(bDay.toLocalDate());
                id.setText(rs.getString("staff_id"));
                jobDescription.setText(rs.getString("job_description"));
                address.setText(rs.getString("address"));
                nationality.setText(rs.getString("nationality"));
                phoneNumber.setText(rs.getString("phone_number"));
                email.setText(rs.getString("email"));
                major.setText(rs.getString("major"));
                degree.setText(rs.getString("degree"));
                education.setText(rs.getString("education"));
                type.setValue(rs.getString("type"));
                gender.setValue(rs.getString("gender"));
                graduateYear.setValue(rs.getString("graduate_year"));

                username.setText("alibenmussa");
                password.setText("1234");
            }

        } catch (SQLException e) {
        }
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
        ArrayList<String> data = new ArrayList<>();
        String StaffId = id.getText();
        String FullName = fullName.getText();
        String BirthDay = birthDay.getValue().toString();
        String Address = address.getText();
        String Job_D = jobDescription.getText();
        String Nationality = nationality.getText();
        String PhoneNumber = phoneNumber.getText();
        String Email = email.getText();
        String Major = major.getText();
        String Degree = degree.getText();
        String Education = education.getText();
        String Type =type.getSelectionModel().getSelectedItem().toString();
        String Gender =gender.getSelectionModel().getSelectedItem().toString();


        data.add(StaffId);
        data.add(FullName);
        data.add(BirthDay);
        data.add(Address);
        data.add(Job_D);
        data.add(Nationality);
        data.add(PhoneNumber);
        data.add(Email);
        data.add(Major);
        data.add(Degree);
        data.add(Education);
        data.add(Type);
        data.add(Gender);

        System.out.println(data);

        String query = "UPDATE `staff` SET `staff_id`= ?,`full_name` = ?, `birthday`= ?, `address`= ?, `job_description`= ?, `nationality`= ?,"+
                " `phone_number`= ?, `email`= ?, `major`= ?, `degree`= ?, `education`= ? ,`type`= ? ,`gender`= ?  WHERE `staff_id`="+sta_Id;
        int affectedrow = DatabaseManager.executeSQLRows(query,data);
        System.out.println(affectedrow);
        if (affectedrow > 0){
            Dialog.success = true;
            Dialog.closeDialogWindow();
        }

    }

    @FXML
    public void uploadPhoto(ActionEvent event) throws Exception {
        selectedImage = ControllerFunctions.getPhotoFromUser(event, userPhoto);

    }
}

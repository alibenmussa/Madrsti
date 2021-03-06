package main.views.stages.admin.adminShowStaff.adminAddStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import main.DB.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.ControllerFunctions;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class adminAddStaffController {

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
    @FXML
    private CheckBox createAccount;

    private File selectedImage = null;


    public void initialize() {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto(), 100, 100, false, false));
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        userPhoto.setFitWidth(100);

        type.getSelectionModel().selectFirst();
        gender.getSelectionModel().selectFirst();

        ArrayList arrayList = new ArrayList();
        int currentYear = LocalDateTime.now().getYear();
        for (int i = 1950; i <= currentYear; i++) {
            arrayList.add(i);
        }
        graduateYear.getItems().addAll(arrayList);
        graduateYear.getSelectionModel().selectFirst();

    }



    @FXML
    void clickCreateAccount(ActionEvent event) {
        ControllerFunctions.clickCreateAccount(event, username, password);





    }


    @FXML
    void adminCancelAddStaff(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void adminSaveAddStaff(ActionEvent event) {
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
        String GraduateYear =graduateYear.getSelectionModel().getSelectedItem().toString();


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
        data.add(GraduateYear);

        System.out.println(data);
        String query = "INSERT INTO `staff` (`staff_id`,`full_name`, `birthday`, `address`, `job_description`, `nationality`,"+
                " `phone_number`, `email`, `major`, `degree`, `education`,`type`,`gender`, `graduate_year` )"+
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int affectedrow = DatabaseManager.executeSQLRows(query,data);
        System.out.println(affectedrow);


        if (createAccount.isSelected()) {
            ArrayList<String> userData = new ArrayList<>();
            String permission;
            String userID = id.getText();
            String userName = username.getText();
            String Pass = password.getText();
            if (type.getSelectionModel().getSelectedItem().toString().equals("Employee")) {
                 permission = "3";
            } else {
                 permission = "2";
            }
            userData.add(userID);
            userData.add(userName);
            userData.add(Pass);
            userData.add(permission);

            String accountquery = "INSERT INTO `users`(`user_id`, `username`, `password`, `permission`) VALUES (?,?,?,?)";
            DatabaseManager.executeSQLRows(accountquery,userData);
        }

        if (affectedrow > 0){
            Dialog.success = true;
            Dialog.closeDialogWindow();

            }



    }

    @FXML
    public void uploadPhoto(ActionEvent event) throws Exception {


            FileChooser fch = new FileChooser();
            fch.showOpenDialog(Main.window);
            /*String path = fch.showOpenDialog(Main.window).toURI().toString();
            String n = path.toLowerCase();
            String a="";
            for (int i = 0; i < n.length(); i++) {
                if (i>5) {
                    a = a + n.charAt(i);
                }
            }*/
           // ControllerFunctions.uploadPhotoToUsersFile(a, id.getText());
        userPhotoCircle.setClip(new Circle(50, 50, 50));
        //userPhoto.setImage(new Image(a));



    }





}

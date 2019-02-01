package main.views.stages.admin.adminShowStaff.adminAddStaff;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import main.StagesManager;
import main.views.dialog.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class adminAddStaffController implements Initializable {
    @FXML
    Group userPhotoCircle;
    @FXML
    ImageView userPhoto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image(StagesManager.getUserPhoto()));
        userPhotoCircle.setClip(new Circle(50, 50, 50));

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

    }
}

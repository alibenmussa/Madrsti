package main.views.stages;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import main.Main;
import main.StagesManager;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ControllerFunctions {
    public static void clickCreateAccount(ActionEvent event, TextField username, PasswordField password) {
        if (((CheckBox) event.getSource()).isSelected()) {
            username.setDisable(false);
            password.setDisable(false);
        } else {
            username.setDisable(true);
            password.setDisable(true);
        }
    }

    public static File getPhotoFromUser(ActionEvent event, ImageView userPhoto) throws Exception {
        FileChooser chooser = new FileChooser();
        File selectedImage = chooser.showOpenDialog(((Button)event.getSource()).getScene().getWindow());

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        if(selectedImage != null) {
            URL path = selectedImage.toURI().toURL();
            userPhoto.setImage(new Image(path.toExternalForm()));
            return selectedImage;
        }
        return null;
    }

        public static void uploadPhotoToUsersFile(File selectedImage, String userId) {
        String selectedImageExtension = getFileExtension(selectedImage.getName());
        String image = userId + "." + selectedImageExtension;
        File path = new File(Main.class.getResource("/main/assests/images/users/").toExternalForm() + image);
//        selectedImage.renameTo(path);
        try {
            Files.copy(selectedImage.toPath(), path.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {

        }
        System.out.println(path.toString());
        }

    public static String getFileExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
}

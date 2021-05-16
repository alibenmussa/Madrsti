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

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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

        public static void uploadPhotoToUsersFile(String selectedImage, String userId) {
        try {
            File file = new File(selectedImage);
            String path2 = ("D:\\projects\\Madrsti\\src\\main\\assests\\images\\users");

            File file2 = new File(path2);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file2 + "/" + userId + ".jpg"));


            int d;
            while ((d=bis.read()) != -1 ) {
                bos.write(d);
            }

            bis.close();
            bos.close();

        } catch (Exception e) {
        e.printStackTrace();

    }
        }

    public static Image readFromUsersFile(String userId) {
        String path = "D:\\projects\\Madrsti\\src\\main\\assests\\images\\users\\" + userId + ".jpg";

        File file = new File(path);
        if (file.exists()) {
            return new Image(file.getPath());
        }
        return new Image("D:\\projects\\Madrsti\\src\\main\\assests\\images\\users\\default.jpg");

    }

    public static String getFileExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
}

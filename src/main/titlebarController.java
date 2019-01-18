package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.login.AuthenticationLogin;

import java.net.URL;
import java.util.ResourceBundle;

public  class titlebarController implements Initializable {
    @FXML
    AnchorPane header;
    @FXML
    Group userPhotoCircle;
    @FXML
    ImageView userPhoto;
    @FXML
    GridPane catigories;

    private double x;
    private double y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image(AuthenticationLogin.getUserPhoto()));
        userPhotoCircle.setClip(new Circle(20, 20, 20));
    }

    @FXML
    void dragHeader(MouseEvent event) {

        AuthenticationLogin.window.setX(event.getScreenX() - x);
        AuthenticationLogin.window.setY(event.getScreenY()  - y);

    }

    @FXML
    void pressHeader(MouseEvent event) {
        x = event.getX();
        y = event.getY();
    }

    @FXML
    public void closeApplication(ActionEvent event) {
        Main.closeApplication();
    }

    @FXML
    void userPhotoClick(MouseEvent event) {

    }

}

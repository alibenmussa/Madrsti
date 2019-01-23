package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
    Button signOut;

    private double x;
    private double y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPhoto.setImage(new Image(AuthenticationLogin.getUserPhoto()));
        userPhotoCircle.setClip(new Circle(15, 15, 15));
        signOut.setText(AuthenticationLogin.name + "? Sign Out");
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
    public void setSignOut(ActionEvent event) {
        AuthenticationLogin.window.close();
        Main.window.show();
    }

    @FXML
    void userPhotoClick(MouseEvent event) {

    }

}

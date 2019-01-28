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
import javafx.scene.shape.Circle;

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
        userPhoto.setImage(new Image(StagesManager.getUserPhoto()));
        userPhotoCircle.setClip(new Circle(15, 15, 15));
        signOut.setText(StagesManager.name + "? Sign Out");
    }

    @FXML
    void dragHeader(MouseEvent event) {
        StagesManager.window.setX(event.getScreenX() - x);
        StagesManager.window.setY(event.getScreenY()  - y);
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
    public void signOut(ActionEvent event) {
        StagesManager.window.close();
        StagesManager.window = Main.window;
        Main.window.show();
    }

    @FXML
    void userPhotoClick(MouseEvent event) {

    }

}

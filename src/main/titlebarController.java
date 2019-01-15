package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.login.AuthenticationLogin;

public  class titlebarController {
    @FXML
    AnchorPane header;
    @FXML
    GridPane catigories;

    private double x;
    private double y;

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

}

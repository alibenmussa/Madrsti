package main.views.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.login.AuthenticationLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class dialogController implements Initializable {

    @FXML
    private AnchorPane sceneWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    void adminShowEmployees(ActionEvent event) {

    }

    @FXML
    void adminShowHome(ActionEvent event) {

    }

    @FXML
    void adminShowResults(ActionEvent event) {

    }

    @FXML
    void adminShowStudents(ActionEvent event) {

    }


    @FXML
    void closeWindow(MouseEvent event) {

        if (event.getTarget() != sceneWindow) {
            System.out.println("ooo");
        }
    }
}

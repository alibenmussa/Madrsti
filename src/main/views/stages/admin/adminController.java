package main.views.stages.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class adminController implements Initializable {
    @FXML
    private AnchorPane header;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/titlebar.fxml");


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
}

package main.views.stages.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeController implements Initializable {
    @FXML
    private AnchorPane header;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/titlebar.fxml");


    }
    @FXML
    void employeeShowHome(ActionEvent event) {

    }

    @FXML
    void employeeShowProfile(ActionEvent event) {

    }

    @FXML
    void employeeShowStudents(ActionEvent event) {

    }
}

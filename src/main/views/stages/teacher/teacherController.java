package main.views.stages.teacher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class teacherController implements Initializable {
    @FXML
    private AnchorPane header;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/titlebar.fxml");


    }
    @FXML
    void teacherShowClasses(ActionEvent event) {

    }

    @FXML
    void teacherShowHome(ActionEvent event) {

    }

    @FXML
    void teacherShowStudents(ActionEvent event) {

    }

    @FXML
    void teacherShowSubjects(ActionEvent event) {

    }
}

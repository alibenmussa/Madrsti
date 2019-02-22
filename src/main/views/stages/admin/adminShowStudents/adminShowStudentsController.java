package main.views.stages.admin.adminShowStudents;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class adminShowStudentsController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<?> year;

    @FXML
    private String reihe;

    @FXML
    private String teacher;

    @FXML
    private String parallel;

    @FXML
    private ComboBox<?> clas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

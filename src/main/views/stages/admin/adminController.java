package main.views.stages.admin;

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
}

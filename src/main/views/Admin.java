package main.views;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    @FXML
    private AnchorPane header;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/navbar.fxml");

    }

    public void displayAdmin() throws Exception {
        Stage window = new Stage();
        Pane root = FXMLLoader.load(getClass().getResource("/main/views/admin.fxml"));
        window.setScene(new Scene(root, 1024, 680));
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
        Main.closeWindow();
    }
}

package main.views.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class dialogController implements Initializable {
    @FXML
    private ScrollPane dialogContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.FXMLLoaderPane(dialogContent, Dialog.dialogPane);
    }

}

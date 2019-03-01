package main.views.dialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class dialogController implements Initializable {
    @FXML
    private ScrollPane dialogContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Dialog.dialogPath != null) {
            Main.FXMLLoaderPane(dialogContent, Dialog.dialogPath);
        } else {
            dialogContent.setContent(Dialog.dialogPane);
        }
    }

}

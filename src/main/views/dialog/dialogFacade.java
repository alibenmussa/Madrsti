package main.views.dialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;

public class dialogFacade {

    public Pane createDialog(String type) throws IOException {
         Pane dialog = null;
         if (type.equals("Confirm")){
             dialog = FXMLLoader.load(Main.class.getResource("/main/views/dialog/confirmDialog.fxml"));
         } else if (type.equals("Alert")) {
             dialog = FXMLLoader.load(Main.class.getResource("/main/views/dialog/alertDialog.fxml"));
         }
         return dialog;
    }
}

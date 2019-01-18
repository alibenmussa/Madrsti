package main.views.dialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.login.AuthenticationLogin;

public class Dialog {

    public static void show(AuthenticationLogin parentWindow, String title) throws Exception{
        Stage dialogStage = new Stage();

        Pane root = FXMLLoader.load(Dialog.class.getResource("/main/views/dialog/dialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(AuthenticationLogin.window.getX() + ((1024 - 400) / 2));
        dialogStage.setY(AuthenticationLogin.window.getY() + ((680 - 400) / 2));

        dialogStage.setTitle(title);
        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);

        parentWindow.showBlackBG();
        dialogStage.show();

        AuthenticationLogin.blackBG.setOnMouseClicked(e -> {
            dialogStage.hide();
            parentWindow.hideBlackBG();
        });

        dialogStage.setOnCloseRequest(e -> {
            parentWindow.hideBlackBG();
        });
    }


}

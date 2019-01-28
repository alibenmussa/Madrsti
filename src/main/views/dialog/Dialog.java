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
    public static Stage dialogStage;

    public static void show(String path) throws Exception{
        AuthenticationLogin parentWindow = AuthenticationLogin.stageController;
        dialogStage = new Stage();

        Pane root = FXMLLoader.load(Main.class.getResource(path));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(AuthenticationLogin.window.getX() + ((1024 - 760) / 2));
        dialogStage.setY(AuthenticationLogin.window.getY() + ((680 - 540) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.show();
        parentWindow.showBlackBG();

        AuthenticationLogin.blackBG.setOnMouseClicked(e -> {
            dialogStage.hide();
//            dialogStage = null;
            parentWindow.hideBlackBG();
        });

        dialogStage.setOnShowing(e -> {
            parentWindow.showBlackBG();
        });

        dialogStage.setOnCloseRequest(e -> {
            parentWindow.hideBlackBG();
        });
    }

    public static void setTitle(String title) {
        dialogStage.setTitle(title);
    }

}

package main.views.dialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.StagesManager;

public class Dialog {
    public static Stage dialogStage;
    public static String confirmMessage;
    public static boolean confirm = false;

    public static void show(String path) throws Exception{
        dialogStage = new Stage();

        Pane root = FXMLLoader.load(Main.class.getResource(path));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(StagesManager.window.getX() + ((1024 - 760) / 2));
        dialogStage.setY(StagesManager.window.getY() + ((680 - 540) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.show();
        StagesManager.showBlackBG();

        StagesManager.blackBG.setOnMouseClicked(e -> {
            dialogStage.hide();
//            dialogStage = null;
            StagesManager.hideBlackBG();
        });

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        dialogStage.setOnCloseRequest(e -> {
            StagesManager.hideBlackBG();
        });
    }

    public static boolean showConfirm(String message) throws Exception {
        dialogStage = new Stage();

        Pane root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/confirmDialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(StagesManager.window.getX() + ((1024 - 450) / 2));
        dialogStage.setY(StagesManager.window.getY() + ((680 - 264) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);

        StagesManager.showBlackBG();

        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();

        });

        dialogStage.setOnCloseRequest(e -> {
            StagesManager.hideBlackBG();
        });

        return confirm;
    }

    public static void closeDialogWindow() {
        dialogStage.hide();
        StagesManager.hideBlackBG();
    }
}

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
    public static String dialogPane;
    public static String subject;
    public static String message;
    public static boolean confirm = false;

    public static void show(String title, String path) throws Exception{
        dialogStage = new Stage();

        dialogPane = path;
        Pane root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/dialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(StagesManager.window.getX() + ((1024 - 760) / 2));
        dialogStage.setY(StagesManager.window.getY() + ((680 - 540) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        StagesManager.window.setAlwaysOnTop(true);
        dialogStage.setAlwaysOnTop(true);
        dialogStage.setTitle(title);
        dialogStage.show();

        StagesManager.showBlackBG();

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });
    }

    public static boolean showConfirm(String message) throws Exception {
        dialogStage = new Stage();
        Dialog.subject = "Confirm Message";
        Dialog.message = message;

        Pane root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/confirmDialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(StagesManager.window.getX() + ((1024 - 450) / 2));
        dialogStage.setY(StagesManager.window.getY() + ((680 - 264) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        StagesManager.window.setAlwaysOnTop(true);
        dialogStage.setAlwaysOnTop(true);
        dialogStage.setTitle("Confirm Message");
        StagesManager.showBlackBG();

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        return confirm;
    }

    public static void showAlert(String subject, String message) throws Exception {
        dialogStage = new Stage();
        Dialog.subject = subject;
        Dialog.message = message;

        Pane root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/alertDialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setX(StagesManager.window.getX() + ((1024 - 450) / 2));
        dialogStage.setY(StagesManager.window.getY() + ((680 - 264) / 2));

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        StagesManager.window.setAlwaysOnTop(true);
        dialogStage.setAlwaysOnTop(true);
        dialogStage.setTitle("Confirm Message");
        StagesManager.showBlackBG();

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });
    }

    public static void closeDialogWindow() {
        dialogStage.hide();
        dialogStage.hide();
        StagesManager.hideBlackBG();
        StagesManager.window.setAlwaysOnTop(false);
        dialogStage.setAlwaysOnTop(false);

    }
}

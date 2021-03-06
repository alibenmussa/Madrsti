package main.views.dialog;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.Main;
import main.StagesManager;

import java.io.IOException;

public class Dialog {
    public static Stage dialogStage;
    public static String dialogPath;
    public static String subject;
    public static String message;
    public static boolean success = false;
    public static Pane dialogPane;

    public static boolean show(String title, String path) throws Exception{
        dialogStage = new Stage();
        success = false;
        dialogPath = path;
        Pane root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/dialog.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle(title);


        StagesManager.showBlackBG();

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        Platform.runLater(() -> {
            animateDialog();
        });
        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        return success;

    }

    public static boolean showAndPass(String title, Pane contnet){
        dialogStage = new Stage();
        success = false;
        dialogPath = null;
        dialogPane = contnet;

        Pane root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("/main/views/dialog/dialog.fxml"));
        } catch (IOException ex) {

        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle(title);


        StagesManager.showBlackBG();

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        dialogPane = null;
        Platform.runLater(() -> {
                animateDialog();
        });

        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        return success;

    }


    public static boolean showConfirm(String subject, String message) {
        dialogStage = new Stage();
        dialogFacade dialogFacade = new dialogFacade();
        Dialog.subject = subject;
        Dialog.message = message;
        success = false;
        Pane root = null;

        try {
            root = dialogFacade.createDialog("Confirm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        StagesManager.showBlackBG();

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        Platform.runLater(() -> {
            animateDialog();
        });

        dialogStage.showAndWait();

        dialogStage.setOnShowing(e -> {
            StagesManager.showBlackBG();
        });

        return success;
    }

    public static void showAlert(String subject, String message) {
        dialogStage = new Stage();
        dialogFacade dialogFacade = new dialogFacade();
        Dialog.subject = subject;
        Dialog.message = message;

        Pane root = null;
        try {
            root = dialogFacade.createDialog("Alert");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        StagesManager.showBlackBG();

        dialogStage.setOnCloseRequest(e -> {
            closeDialogWindow();
        });

        StagesManager.blackBG.setOnMouseClicked(e -> {
            closeDialogWindow();
        });

        Platform.runLater(() -> {
            animateDialog();
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

    private static void animateDialog() {
        double duration = 0.5;

        dialogStage.setOpacity(0);
        FadeTransition fade = new FadeTransition(Duration.seconds(duration), dialogStage.getScene().getRoot());
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        double centerY = screen.getMinY() + (screen.getHeight() - dialogStage.getScene().getHeight()) * 1.0f / 2;
        double start = screen.getMinY() + centerY + (int) (dialogStage.getHeight() / 2);
        double endPos = screen.getMinY() + centerY;
        DoubleProperty y = new SimpleDoubleProperty(start);
        y.addListener((obs, oldValue, newValue) -> dialogStage.setY(newValue.doubleValue()));

        KeyFrame kf1 = new KeyFrame(Duration.seconds(duration), new KeyValue(y, endPos));
        KeyFrame kf2 = new KeyFrame(Duration.seconds(duration), new KeyValue(dialogStage.opacityProperty(), 1));

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(kf1, kf2);
        timeline.play();
    }
}

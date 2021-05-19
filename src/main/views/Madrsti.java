package main.views;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.StagesManager;
import main.views.access.StageFactory;
import main.views.dialog.Dialog;

import java.io.IOException;

public class Madrsti {


    public static void displayStage(int access) {
        Stage window = new Stage();
        String path;

        Pane mainView = null;
        StageFactory stageFactory = new StageFactory();
        try {
            mainView = stageFactory.selectStage(access).createStage();
        } catch (IOException ex) {
            Dialog.showAlert("Connection Error", "The permission is not right");
        }

        Rectangle page = new Rectangle(1024, StagesManager.windowHeight);
        page.setArcWidth(20);
        page.setArcHeight(20);


        StackPane root = new StackPane(mainView);
        root.setClip(page);

        Scene scene = new Scene(root, 1024, StagesManager.windowHeight);
        scene.setFill(Color.TRANSPARENT);

        StagesManager.window = window;
        StagesManager.root = root;

        window.setScene(scene);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Madrsti Madrsti | Control Panel");
        window.show();
        Main.window.close();

    }

}

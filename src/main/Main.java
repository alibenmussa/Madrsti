package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage window;

    @Override
    public void start(Stage primaryStage){
        Main.showMain(primaryStage);

    }

    public static void showMain(Stage primaryStage) {
        window = primaryStage;
        StackPane root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource("/main/views/login/login.fxml"));
        } catch (Exception ex) {

        }

        StagesManager.root = root;
        Rectangle page = new Rectangle(StagesManager.windowWidth, StagesManager.windowHeight);
        page.setArcWidth(20);
        page.setArcHeight(20);
        root.setClip(page);
        Scene scene = new Scene(root, StagesManager.windowWidth, StagesManager.windowHeight);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.setTitle("Madrasti");
        window.initStyle(StageStyle.TRANSPARENT);
        StagesManager.blackBG.setStyle("-fx-background-color: rgba(53, 57, 89, 0.75)");

        window.show();
        System.out.println("Let Game Starts!");
    }

    public static void closeApplication() {
        Platform.exit();
    }

    public static void FXMLLoaderPane(AnchorPane mainPane, String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource(fxmlFile));
            mainPane.getChildren().setAll(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void FXMLLoaderPane(ScrollPane mainPane, String fxmlFile) {
        try {
            mainPane.setContent(new AnchorPane(new Button("Wait")));
            AnchorPane pane = FXMLLoader.load(Main.class.getResource(fxmlFile));
            mainPane.setContent(pane);
            mainPane.setVvalue(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void shadowMaker(Pane element) {
        DropShadow shadow = new DropShadow(80, 0 , 70, Color.rgb(134, 139, 142, 0.15)); //Color.web("F0F2F6")
        element.setEffect(shadow);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

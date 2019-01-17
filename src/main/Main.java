package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/main/login/login.fxml"));
        Rectangle page = new Rectangle(1024, 680);
        page.setArcWidth(20);
        page.setArcHeight(20);
        root.setClip(page);
        Scene scene = new Scene(root, 1024, 680);
//        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Roboto");
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.setTitle("Madrasti System");
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
        System.out.println("Let Game Starts!");
    }

    public static void closeApplication() {
        Platform.exit();
    }

    public static void headerLoader(AnchorPane headerPane, String navbar) {
        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource(navbar));
            headerPane.getChildren().setAll(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void shadowMaker(Pane element) {
        DropShadow shadow = new DropShadow(80, 0 , 70, Color.web("F0F2F6"));
        element.setEffect(shadow);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

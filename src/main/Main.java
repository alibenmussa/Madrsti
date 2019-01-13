package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/main/login/login.fxml"));
        root.getStylesheets().add(Main.class.getResource("/main/stylesheets/style.css").toExternalForm());
        Rectangle page = new Rectangle(1024, 680);
        page.setArcWidth(20);
        page.setArcHeight(20);
        root.setClip(page);
        Scene scene = new Scene(root, 1024, 680);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.setTitle("Madrasti System");
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
        System.out.println("Let Game Starts!");
    }

    public static void closeWindow() {
        window.close();
    }

    public static void headerLoader(AnchorPane headerPane, String navbar) {
        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource(navbar));
            headerPane.getChildren().setAll(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

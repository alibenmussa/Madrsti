package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Pane root = FXMLLoader.load(getClass().getResource("/main/login/login.fxml"));
        root.getStylesheets().add(Main.class.getResource("/main/stylesheets/style.css").toExternalForm());
        window.setScene(new Scene(root, 1024, 680));
        window.setTitle("Madrasti System");
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
        System.out.println("Let Game Starts!");
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

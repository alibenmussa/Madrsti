package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class StagesManager {

    public static String username;
    public static String name = "Yousef";
    public static StackPane root;
    public static Pane blackBG = new Pane();
    public static Stage window = Main.window;
    public static ScrollPane stageContent = null;
    public static double windowHeight = 720;

    public static String getUserPhoto() {
        return "/main/assests/images/users/user_02.jpg"; //ستستبدل بكويري من الداتابيز
    }

    public static void showBlackBG() {
        root.getChildren().add(blackBG);
    }

    public static void hideBlackBG() {
        root.getChildren().remove(blackBG);
    }

}

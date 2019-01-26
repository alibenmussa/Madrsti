package main.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Main;

public abstract class AuthenticationLogin {
    public static String username;
    public static String password;
    public static String name;

    protected StackPane root;

    public static Pane blackBG = new Pane();
    public static ScrollPane stageContent = null;
    public static Stage window = Main.window;
    public static AuthenticationLogin stageController;

    public AuthenticationLogin(String username, String password) {
        AuthenticationLogin.stageController = this;
        AuthenticationLogin.username = username;
        AuthenticationLogin.password = password;
        AuthenticationLogin.stageContent = null;
        name = "Yousef";
        blackBG.setStyle("-fx-background-color: rgba(53, 57, 89, 0.75)");

    }

    public static String getUserPhoto() {
        return "/main/assests/images/users/user_02.jpg"; //ستستبدل بكويري من الداتابيز
    }

    public void showBlackBG() {
        root.getChildren().add(blackBG);
    }

    public void hideBlackBG() {
        root.getChildren().remove(blackBG);
    }

    public static void navbarLoader(AnchorPane navbarPane, String navbar) {
        try {
            AnchorPane pane = FXMLLoader.load(Main.class.getResource(navbar));
            navbarPane.getChildren().setAll(pane);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

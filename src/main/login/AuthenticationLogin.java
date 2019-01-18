package main.login;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Main;

public abstract class AuthenticationLogin {
    protected String username;
    protected String password;
    protected StackPane root;
    public static Pane blackBG;
    public static Stage window = Main.window;

    public AuthenticationLogin(String username, String password) {
        this.username = username;
        this.password = password;
        blackBG = new Pane();
        blackBG.setStyle("-fx-background-color: rgba(53, 57, 89, 0.6)");
    }

    public static String getUserPhoto() {
        return "/main/images/users/user_01.jpg"; //ستستبدل بكويري من الداتابيز
    }

    public void showBlackBG() {
        root.getChildren().add(blackBG);
    }

    public void hideBlackBG() {
        root.getChildren().remove(blackBG);
    }
}

package main.login;

import javafx.stage.Stage;
import main.Main;

public class AuthenticationLogin {
    private String username;
    private String password;
    public static Stage window = Main.window;

    public AuthenticationLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

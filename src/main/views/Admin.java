package main.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.login.AuthenticationLogin;

public class Admin extends AuthenticationLogin {


    public Admin(String username, String password) {
        super(username, password);
    }

    public void displayAdmin() throws Exception {
        window = new Stage();
        Pane root = FXMLLoader.load(getClass().getResource("/main/views/admin/admin.fxml"));
        Rectangle page = new Rectangle(1024, 680);
        page.setArcWidth(20);
        page.setArcHeight(20);
        root.setClip(page);
        Scene scene = new Scene(root, 1024, 680);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
        Main.window.close();
    }
}

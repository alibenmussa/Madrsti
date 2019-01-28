package main.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.StagesManager;

public class Admin {

    public static void displayAdmin(String username) throws Exception {
        Stage window = new Stage();
        Pane mainView = FXMLLoader.load(Admin.class.getResource("/main/views/stages/admin/admin.fxml"));

        Rectangle page = new Rectangle(1024, 680);
        page.setArcWidth(20);
        page.setArcHeight(20);

        StackPane root = new StackPane(mainView);
        root.setClip(page);

        Scene scene = new Scene(root, 1024, 680);
        scene.setFill(Color.TRANSPARENT);

        StagesManager.username = username;
        StagesManager.window = window;
        StagesManager.root = root;

        window.setScene(scene);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Madrsti System | Control Panel");
        window.show();
        Main.window.close();


    }

}

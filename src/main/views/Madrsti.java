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

import java.io.IOException;

public class Madrsti {


    public static void displayStage(int access) {
        Stage window = new Stage();
        String path;
        switch (access) {
            case 1:
                path = "/main/views/stages/admin/admin.fxml";
                break;
            case 2:
                path = "/main/views/stages/teacher/teacher.fxml";
                break;
            case 3:
                path = "/main/views/stages/employee/employee.fxml";
                break;
            default:
                return;
        }

        Pane mainView = null;
        try {
            mainView = FXMLLoader.load(Madrsti.class.getResource(path));
        } catch (IOException ex) {

        }

        Rectangle page = new Rectangle(1024, StagesManager.windowHeight);
        page.setArcWidth(20);
        page.setArcHeight(20);


        StackPane root = new StackPane(mainView);
        root.setClip(page);

        Scene scene = new Scene(root, 1024, StagesManager.windowHeight);
        scene.setFill(Color.TRANSPARENT);

        StagesManager.window = window;
        StagesManager.root = root;

        window.setScene(scene);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Madrsti Madrsti | Control Panel");
        window.show();
        Main.window.close();

    }

}

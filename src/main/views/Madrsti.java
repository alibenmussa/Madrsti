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
import main.views.access.AdminStage;
import main.views.access.EmployeeStage;
import main.views.access.TeacherStage;

import java.io.IOException;

public class Madrsti {


    public static void displayStage(int access) {
        Stage window = new Stage();
        String path;


        Pane mainView = null;
        try {
            switch (access) {
                case 1:
                    mainView = new AdminStage().createStage();
                    break;
                case 2:
                    mainView = new TeacherStage().createStage();
                    break;
                case 3:
                    mainView = new EmployeeStage().createStage();
                    break;
                default:
                    return;
            }
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

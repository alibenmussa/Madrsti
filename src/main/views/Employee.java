package main.views;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Employee {

    public static void displayEmployee() {
        Stage window = new Stage();
        Pane root = new Pane(new Button("Employee Stage"));
        window.setScene(new Scene(root, 1024, 680));
        window.initStyle(StageStyle.TRANSPARENT);
        window.show();
    }
}

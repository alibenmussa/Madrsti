package main.views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import main.login.AuthenticationLogin;
import main.views.dialog.Dialog;

public class Teacher extends AuthenticationLogin {

    public Teacher(String username, String password) {
        super(username, password);
    }

    public void displayTeacher() throws Exception{
        window = new Stage();
        Pane mainview = FXMLLoader.load(getClass().getResource("/main/views/stages/teacher/teacher.fxml"));

        Rectangle page = new Rectangle(1024, 680);
        page.setArcWidth(20);
        page.setArcHeight(20);

        root = new StackPane(mainview);
        root.setClip(page);

        Scene scene = new Scene(root, 1024, 680);
        scene.setFill(Color.TRANSPARENT);
        window.setScene(scene);
        window.initStyle(StageStyle.TRANSPARENT);

        window.show();
        Main.window.close();
        Dialog.show(this, "Insert Students Data");
    }
}

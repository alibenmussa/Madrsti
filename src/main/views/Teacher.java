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

    public void displayTeacher() {
        //ليس الآن - ليس الآن
        //بعد ما نكملوا الأدمن، كبي بيست، لأن مرات تتغير واجهة الأدمن
    }
}

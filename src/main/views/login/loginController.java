package main.views.login;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.views.Madrsti;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private AnchorPane header;
    @FXML
    private Label loginSubject;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private VBox loginContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //مؤقتا فقط لعملية التصميم

        Main.shadowMaker(loginContainer);
        Main.FXMLLoaderPane(header, "/main/titlebar.fxml");
        setLoginSubjectText();
    }

    @FXML
    public void clickLogIn(ActionEvent event) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        int authenticationState = checkAuthentication(username, password);
        if(authenticationState != 0) {
            switch (Integer.parseInt(username + "")) {
                case 1:
                    Madrsti.displayStage(username, 1);
                    break;
                case 2:
                    Madrsti.displayStage(username, 2);
                    break;
                case 3:
                    Madrsti.displayStage(username, 3);
                    break;
                default:
                    Madrsti.displayStage(username, 1);

            }
        } else {
            java.lang.System.out.println("Your Enter is Error!"); //مؤقتا لين ما نديروا رسالة خطأ
            this.username.setStyle("-fx-border-color: red");
            this.password.setStyle("-fx-border-color: red");
        }
    }

    private int checkAuthentication(String username, String password) {
        return 1;    //مؤقتا بس، بعدين حنربطوا بالداتابيز
    }



    private void setLoginSubjectText() {
        int currentHour = LocalDateTime.now().getHour();
        String message = "WELCOME,";
        if (currentHour <= 3) {
            message = "GOODNIGHT,";
        } else if (currentHour <= 10) {
            message = "GOODMORNING,";
        } else if (currentHour <= 14) {
            message = "HELLO!";
        } else if (currentHour <= 23) {
            message = "WELCOME,";
        }
        loginSubject.setText(message);
    }


}

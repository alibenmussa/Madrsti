package main.login;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.views.Admin;
import main.views.Teacher;
import main.views.Employee;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Main.shadowMaker(loginContainer);
        Main.FXMLLoaderPane(header, "/main/titlebar.fxml");
        setLoginSubjectText();
    }

    private void setLoginSubjectText() {
        int currentHour = LocalDateTime.now().getHour();
        String message = "Welcome";
        if (currentHour <= 3) {
            message = "GOODNIGHT";
        } else if (currentHour <= 10) {
            message = "Goodmorning";
        } else if (currentHour <= 14) {
            message = "Goodafternoon";
        } else if (currentHour <= 23) {
            message = "WELCOME";
        }
        loginSubject.setText(message + ",");
    }

    @FXML
    public void clickLogIn(ActionEvent event) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        int authenticationState = checkAuthentication(username, password);
        if(authenticationState != 0) {
            switch (authenticationState) {
                case 1:
                    new Admin(username, password).displayAdmin();
                    break;
                case 2:
                    new Teacher(username, password).displayTeacher();
                    break;
                case 3:
                    new Employee(username, password).displayEmployee();
                    break;
            }
        } else {
            System.out.println("Your Enter is Error!"); //مؤقتا لين ما نديروا رسالة خطأ
        }
    }

    private int checkAuthentication(String username, String password) {
        return 1;    //مؤقتا بس، بعدين حنربطوا بالداتابيز
    }


}

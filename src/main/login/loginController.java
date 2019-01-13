package main.login;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.views.Admin;
import main.views.Teacher;
import main.views.Employee;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private AnchorPane header;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;




    @FXML
    public void clickSignIn(ActionEvent event) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        int authenticationState = checkAuthentication(username, password);
        if(authenticationState != 0) {
            switch (authenticationState) {
                case 1:
                    new Admin(username, password).displayAdmin();
                    break;
                case 2:
                    Teacher.displayTeacher();
                    break;
                case 3:
                    Employee.displayEmployee();
                    break;
            }
        } else {
            System.out.println("Your Enter is Error!"); //مؤقتا لين ما نديروا رسالة خطأ
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.headerLoader(header, "/main/titlebar.fxml");

    }

    private int checkAuthentication(String username, String password) {
        return 1;    //مؤقتا بس، بعدين حنربطوا بالداتابيز
    }


}

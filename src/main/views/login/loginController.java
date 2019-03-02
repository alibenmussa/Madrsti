package main.views.login;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import main.DatabaseManager;
import main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.StagesManager;
import main.views.Madrsti;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private AnchorPane header;
    @FXML
    private Label loginSubject;
    @FXML
    private Button login;
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
        username.setText("");
        password.setText("");
    }

    @FXML
    public void clickLogIn(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        int authenticationState = checkAuthentication(username, password);
        if(authenticationState != 0) {
            Madrsti.displayStage(authenticationState);
        } else {
            this.username.getStyleClass().add("error-field");
            this.password.getStyleClass().add("error-field");
        }
    }

    public void pressLogIn(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            clickLogIn(null);
        }
    }

    private int checkAuthentication(String username, String password) {
        ArrayList<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        int permission = 0;
        ResultSet rs = DatabaseManager.executeSQLResultSet("SELECT * FROM `users` INNER JOIN `staff` ON `user_id` = `staff_id` WHERE `username` = ? AND `password` = ?", data);
        if (rs != null) {
            try {
                while (rs.next()) {
                    StagesManager.userId = rs.getString("user_id");
                    StagesManager.username = rs.getString("username");
                    StagesManager.name = rs.getString("full_name");
                    permission = rs.getInt("permission");
                    System.out.println(StagesManager.name);
                }
            } catch (SQLException ex) {
            }
        }
        return permission;
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

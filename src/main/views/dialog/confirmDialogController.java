package main.views.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class confirmDialogController implements Initializable {
    @FXML
    private Label subject;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subject.setText(Dialog.subject);
        message.setText(Dialog.message);
    }

    @FXML
    void clickNo(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void clickYes(ActionEvent event) {
        Dialog.success = true;
        Dialog.closeDialogWindow();
    }

}

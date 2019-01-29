package main.views.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.StagesManager;

public class confirmDialogController {

    @FXML
    void clickNo(ActionEvent event) {
        Dialog.closeDialogWindow();
    }

    @FXML
    void clickYes(ActionEvent event) {
        Dialog.confirm = true;
        Dialog.closeDialogWindow();
    }

}

package main;

import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public  class titlebarController {
    @FXML
    AnchorPane header;
    @FXML
    GridPane catigories;

    private double x;
    private double y;

    @FXML
    void dragHeader(MouseEvent event) {
        Main.window.setX(event.getScreenX() - x);
        Main.window.setY(event.getScreenY()  - y);
    }

    @FXML
    void pressHeader(MouseEvent event) {
        x = event.getX();
        y = event.getY();
    }

}

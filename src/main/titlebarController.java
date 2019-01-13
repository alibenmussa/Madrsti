package main;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public  class titlebarController {

    private double x;
    private double y;

    @FXML
    AnchorPane header;
    @FXML
    GridPane catigories;

    @FXML
    void dragHeader(MouseEvent event) {
        Main.window.setX(event.getSceneX() - x);
        Main.window.setY(event.getSceneY()  - y);
    }

    @FXML
    void pressHeader(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
        System.out.println(x + " " + y);
    }




}

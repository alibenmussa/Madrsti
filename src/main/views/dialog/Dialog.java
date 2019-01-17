package main.views.dialog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;

public class Dialog {
    private Pane box;

    public Dialog(Pane scene) {
        this.box = scene;
    }

    public void show() {
        Stage dialogStage = new Stage();

        Pane root = new AnchorPane();

        root.setStyle("-fx-background-color: rgba(0,0,0, 0.6)");
        Rectangle page = new Rectangle(1024, 680);

        page.setArcWidth(20);
        page.setArcHeight(20);
        root.setClip(page);

        root.setOnMouseClicked(e -> {
            dialogStage.close();
        });

        Scene scene = new Scene(root, 1024, 680);
        scene.setFill(Color.TRANSPARENT);
        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.show();
    }

}

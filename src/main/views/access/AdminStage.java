package main.views.access;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.views.Madrsti;

import java.io.IOException;

public class AdminStage implements Stage {
    @Override
    public Pane createStage() throws IOException {
        return FXMLLoader.load(Madrsti.class.getResource("/main/views/stages/admin/admin.fxml"));
    }
}

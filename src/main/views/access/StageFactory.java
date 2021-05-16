package main.views.access;

import javafx.scene.layout.Pane;

import java.io.IOException;

public interface StageFactory {
    public Pane createStage() throws IOException;
}

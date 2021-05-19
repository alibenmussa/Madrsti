package main.views.access;

import javafx.scene.layout.Pane;

import java.io.IOException;

public class StageFactory {
    public Stage selectStage(int access) {
        switch (access) {
            case 1:
                return new AdminStage();
            case 2:
                return new TeacherStage();
            case 3:
                return new EmployeeStage();
            default:
                return null;
        }
    }
}

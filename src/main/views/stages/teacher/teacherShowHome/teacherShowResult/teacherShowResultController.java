package main.views.stages.teacher.teacherShowHome.teacherShowResult;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Main;
import main.StagesManager;

public class teacherShowResultController {

    public String gradeId;
    public String gradeName;
    public String className;
    public String subjectId;
    public String subjectName;
    public void initialize(String gradeId, String gradeName, String className, String subjectId, String subjectName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.className = className;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    @FXML
    void editResults(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
        Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/teacher/teacherShowHome/teacherShowHome.fxml");
    }
}

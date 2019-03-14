package main.views.stages.template;

import javafx.scene.control.TextField;

public class TextFieldForm {
    public String studentId;
    public TextField firstMidtermExam;
    public TextField firstFinalExam;
    public TextField secondMidtermExam;
    public TextField secondFinalExam;

    public TextFieldForm(String studentId, TextField firstMidtermExam, TextField firstFinalExam, TextField secondMidtermExam, TextField secondFinalExam) {
        this.studentId = studentId;
        this.firstMidtermExam = firstMidtermExam;
        this.firstFinalExam = firstFinalExam;
        this.secondMidtermExam = secondMidtermExam;
        this.secondFinalExam = secondFinalExam;
    }
}

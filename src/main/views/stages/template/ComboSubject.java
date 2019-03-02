package main.views.stages.template;

public class ComboSubject {
    private int subjectId;
    private String name;

    public ComboSubject(int subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }
}

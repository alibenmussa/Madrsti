package main.views.stages.template;

public class Grade {
    private Integer gradeId;
    private String name;

    public Grade(Integer gradeId, String name) {
        this.gradeId = gradeId;
        this.name = name;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

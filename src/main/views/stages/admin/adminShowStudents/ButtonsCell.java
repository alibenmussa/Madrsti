package main.views.stages.admin.adminShowStudents;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.template.Student;


public class ButtonsCell extends TableCell<Student, Boolean> {

    final Button showInformationButton = new Button("S");

    public ButtonsCell(adminShowStudentsController controller) {
        showInformationButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.showStudent(selected.getStu_id());
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(new HBox(10, showInformationButton));
        }
    }
}


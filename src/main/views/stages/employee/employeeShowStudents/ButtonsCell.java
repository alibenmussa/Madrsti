package main.views.stages.employee.employeeShowStudents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.template.Student;

public class ButtonsCell extends TableCell<Student, Boolean> {
    final Button deleteButton = new Button();
    final Button editButton = new Button();
    final Button showInformationButton = new Button();
    final Button showResultButton = new Button();

    public ButtonsCell(employeeShowStudentsController controller) {
        deleteButton.getStyleClass().addAll("mini-button", "delete-button");
        deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CLOSE));

        editButton.getStyleClass().addAll("mini-button", "edit-button");
        editButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EDIT));

        showInformationButton.getStyleClass().addAll("mini-button", "profile-button");
        showInformationButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.USER));

        showResultButton.getStyleClass().addAll("mini-button", "show-button");
        showResultButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.LIST_ALT));



        deleteButton.setOnAction(e -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.employeeDeleteStudent(selected.getStu_id());
        });
        editButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.employeeEditStudent(selected.getStu_id());
        });
        showInformationButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.employeeShowStudent(selected.getStu_id());
        });
        showResultButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.employeeShowResult(selected.getStu_id());
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(new HBox(10, editButton, deleteButton, showInformationButton, showResultButton));
        }
    }
}


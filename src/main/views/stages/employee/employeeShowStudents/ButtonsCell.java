package main.views.stages.employee.employeeShowStudents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.template.Students;

public class ButtonsCell extends TableCell<Students, Boolean> {
    final Button deleteButton = new Button();
    final Button editButton = new Button("E");
    final Button showInformationButton = new Button("S");

    public ButtonsCell(employeeShowStudentsController controller) {
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CLOSE));
        deleteButton.setOnAction(e -> {
//          Students currentPerson = ButtonsCell.this.getTableView().getItems().get(ButtonsCell.this.getIndex());
            Students selected = getTableView().getItems().get(getIndex());
            controller.employeeDeleteStudent(selected.getStu_id());
        });
        editButton.setOnAction(event -> {
            Students selected = getTableView().getItems().get(getIndex());
            controller.employeeEditStudent(selected.getStu_id());
        });
        showInformationButton.setOnAction(event -> {
            Students selected = getTableView().getItems().get(getIndex());
            controller.employeeShowStudent(selected.getStu_id());
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(new HBox(10, deleteButton, editButton, showInformationButton));
        }
    }
}


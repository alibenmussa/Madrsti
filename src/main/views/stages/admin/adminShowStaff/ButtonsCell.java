package main.views.stages.admin.adminShowStaff;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.template.Staff;


public class ButtonsCell extends TableCell<Staff, Boolean> {
    final Button deleteButton = new Button();
    final Button editButton = new Button("E");
    final Button showInformationButton = new Button("S");

    public ButtonsCell(adminShowStaffController controller) {
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CLOSE));
        deleteButton.setOnAction(e -> {

            Staff selected = getTableView().getItems().get(getIndex());
            controller.adminDeleteStaff(selected.getNational_id());
        });
        editButton.setOnAction(event -> {
            Staff selected = getTableView().getItems().get(getIndex());
            controller.EditStaff(selected.getNational_id());
        });
        showInformationButton.setOnAction(event -> {
            Staff selected = getTableView().getItems().get(getIndex());
            controller.ShowStaff(selected.getNational_id());
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


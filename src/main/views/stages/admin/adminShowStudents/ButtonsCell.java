package main.views.stages.admin.adminShowStudents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.admin.adminShowStaff.adminShowStaffController;
import main.views.stages.template.Staff;
import main.views.stages.template.Students;


public class ButtonsCell extends TableCell<Students, Boolean> {

    final Button showInformationButton = new Button("S");

    public ButtonsCell(adminShowStudentsController controller) {
        showInformationButton.setOnAction(event -> {
            Students selected = getTableView().getItems().get(getIndex());
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


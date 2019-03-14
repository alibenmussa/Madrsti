package main.views.stages.admin.adminShowStudents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import main.views.stages.template.Student;


public class ButtonsCell extends TableCell<Student, Boolean> {

    final Button showInformationButton = new Button();
    final Button showResultButton = new Button();

    public ButtonsCell(adminShowStudentsController controller) {

        showInformationButton.getStyleClass().addAll("mini-button", "profile-button");
        showInformationButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.USER));

        showResultButton.getStyleClass().addAll("mini-button", "show-button");
        showResultButton.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.LIST_ALT));


        showInformationButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.showStudent(selected.getStu_id());
        });
        showResultButton.setOnAction(event -> {
            Student selected = getTableView().getItems().get(getIndex());
            controller.adminShowResult(selected.getStu_id());
        });

    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(new HBox(10, showInformationButton,showResultButton));
        }
    }
}


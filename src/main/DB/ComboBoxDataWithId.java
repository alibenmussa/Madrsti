package main.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import main.views.stages.template.ComboForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ComboBoxDataWithId {
    public static void addComboBoxDataWithId(ComboBox comboBox, String query, ArrayList<String> data) {
        ArrayList<String> items = DatabaseManager.getResultOneRow(query, data, 1);
        comboBox.getItems().clear();
        comboBox.setVisibleRowCount(6);
        ObservableList<ComboForm> list = FXCollections.observableArrayList();
        ResultSet rs = DatabaseManager.executeSQLResultSet(query, data);
        if (rs != null) {
            try {
                while (rs.next()) {
                    ComboForm form = new ComboForm(rs.getString(1), rs.getString(2));
                    list.add(form);
                }
            } catch (SQLException ex) {

            }
        }
        comboBox.setItems(list);
        comboBox.setConverter(new StringConverter<ComboForm>() {
            @Override
            public String toString(ComboForm form) {
                return form.nameProperty().get();
            }

            @Override
            public ComboForm fromString(String id) {
                return list.stream().filter(item -> item.idProperty().get().equals(id)).collect(Collectors.toList()).get(0);
            }
        });
    }
}

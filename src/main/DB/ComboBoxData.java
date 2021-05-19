package main.DB;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ComboBoxData {
    public static void addComboBoxData(ComboBox comboBox, String query, ArrayList<String> data) {
        ArrayList<String> items = DatabaseManager.getResultOneRow(query, data, 1);
        comboBox.getItems().clear();
        comboBox.setVisibleRowCount(6);
        for (int i = 0; i < items.size(); i++) {
            comboBox.getItems().add(items.get(i));
        }
    }
}

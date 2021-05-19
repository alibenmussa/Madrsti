package main.DB;

import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ComboFacade {
    public static void ComboAddData(String type, ComboBox comboBox, String query, ArrayList<String> data) {

        if (type.equals("ComboBox")) {
            ComboBoxData.addComboBoxData(comboBox, query, data);
        } else if (type.equals("ComboBoxWithId")) {
            ComboBoxDataWithId.addComboBoxDataWithId(comboBox, query, data);
        }

    }

}

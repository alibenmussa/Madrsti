package main.views.stages.admin.adminShowStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import main.DatabaseManager;
import main.Main;
import main.StagesManager;
import main.views.dialog.Dialog;
import main.views.stages.template.Staff;
import main.views.stages.admin.adminShowStaff.adminAddStaff.adminAddStaffController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminShowStaffController implements Initializable {
    @FXML
    private TextField search;

    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, String> name;

    @FXML
    private TableColumn<Staff, String> section;

    @FXML
    private TableColumn<Staff, String> phone;

    @FXML
    private TableColumn<Staff, Button> operations;

    ObservableList<Staff> data;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpTable();

    }


    public void setUpTable() {



        this.name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        this.section.setCellValueFactory(new PropertyValueFactory<>("state"));
        this.phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        this.operations.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<Staff, Button>, TableCell<Staff, Button>> cellFactory
                = //
                new Callback<TableColumn<Staff, Button>, TableCell<Staff, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<Staff, Button> param) {
                        final TableCell<Staff, Button> cell = new TableCell<Staff, Button>() {

                            final Button editBtn = new Button("E");
                            final Button deleteBtn = new Button("D");
                            final Button showBtn = new Button("S");
                            HBox h = new HBox(editBtn,deleteBtn,showBtn);

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    deleteBtn.setOnAction(event -> {
                                        Staff selectedItem = getTableView().getItems().get(getIndex());
                                        System.out.println(selectedItem  == null);
                                        ArrayList<String> list = new ArrayList<>();
                                        list.add(selectedItem.getNational_id().toString());
                                        System.out.println(list);
                                        String query = "DELETE FROM `staff` WHERE staff_id = ?";
                                        int affectedRows = DatabaseManager.executeSQLRows(query, list);

                                        setUpTable();
                                    });
                                    setGraphic(h);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

                operations.setCellFactory(cellFactory);

                staffTable.setItems(getStaffList());





    }

    public ObservableList<Staff> getStaffList() {

        data  = FXCollections.observableArrayList();

        String query = "SELECT * FROM `staff`";
        ResultSet rw = DatabaseManager.executeSQLResultSet(query,null);
        try {
            while (rw.next()) {
                Staff staff = new Staff();
                staff.setNational_id(rw.getInt("staff_id"));
                staff.setPhone_number(rw.getInt("phone_number"));
                staff.setBirthday(rw.getDate("birthday"));
                staff.setDegree(rw.getString("degree"));
                staff.setEmail(rw.getString("email"));
                staff.setFull_name(rw.getString("full_name"));
                staff.setState(rw.getString("type"));
                staff.setGender(rw.getString("gender"));
                staff.setLiving_address(rw.getString("address"));
                staff.setJob_description(rw.getString("job_description"));
                staff.setNationality(rw.getString("nationality"));
                staff.setMajor(rw.getString("major"));
                staff.setUniversity(rw.getString("education"));
                staff.setGraduateYear(rw.getString("graduate_year"));

                data.add(staff);
          }


      }catch (Exception e){

      }
      return data;
    }

    @FXML
    void adminAddStaff(ActionEvent event) {
        String path = "/main/views/stages/admin/adminShowStaff/adminAddStaff/adminAddStaff.fxml";
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        adminAddStaffController controller = loader.getController();
        controller.initialize();
        boolean addStaff = Dialog.showAndPass("Add Staff", loader.getRoot());
        if (addStaff) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
        }
    }
}

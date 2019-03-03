package main.views.stages.admin.adminShowStaff;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import main.views.stages.admin.adminShowStaff.adminEditStaff.adminEditStaffController;
import main.views.stages.admin.adminShowStaff.adminShowStaffInformation.adminShowStaffInformationController;
import main.views.stages.template.Staff;
import main.views.stages.admin.adminShowStaff.adminAddStaff.adminAddStaffController;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
                                        list.add(selectedItem.getNational_id());
                                        System.out.println(list);
                                        String query = "DELETE FROM `staff` WHERE staff_id = ?";
                                        if(Dialog.showConfirm("blah","delete")) {
                                            int affectedRows = DatabaseManager.executeSQLRows(query, list);
                                        }
                                        setUpTable();
                                    });
                                    editBtn.setOnAction(event -> {
                                        Staff Item = getTableView().getItems().get(getIndex());
                                        EditStaff(Item.getNational_id());
                                    });
                                    showBtn.setOnAction(event -> {
                                        Staff selected = getTableView().getItems().get(getIndex());
                                        ShowStaff(selected.getNational_id());
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

        data = FXCollections.observableArrayList();

    Connection connection = DatabaseManager.createConnection();

        String query = "SELECT * FROM `staff`";
        ResultSet rs = DatabaseManager.executeSQLResultSet(query,null);
        try {

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setNational_id(rs.getString("staff_id"));
                staff.setPhone_number(rs.getInt("phone_number"));
                staff.setBirthday(rs.getDate("birthday"));
                staff.setDegree(rs.getString("degree"));
                staff.setEmail(rs.getString("email"));
                staff.setFull_name(rs.getString("full_name"));
                staff.setState(rs.getString("type"));
                staff.setGender(rs.getString("gender"));
                staff.setLiving_address(rs.getString("address"));
                staff.setJob_description(rs.getString("job_description"));
                staff.setNationality(rs.getString("nationality"));
                staff.setMajor(rs.getString("major"));
                staff.setUniversity(rs.getString("education"));
                staff.setGraduateYear(rs.getString("graduate_year"));

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
        /*adminAddStaffController controller = loader.getController();
        controller.initialize();*/
        boolean addStaff = Dialog.showAndPass("Add Staff", loader.getRoot());
        if (addStaff) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
        }
    }

    private void EditStaff(String Id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/admin/adminShowStaff/adminEditStaff/adminEditStaff.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        adminEditStaffController controller = loader.getController();
        controller.initialize(Id);
        boolean editStaff = Dialog.showAndPass("Edit Staff", loader.getRoot());
        if (editStaff) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
        }

    }
    private void ShowStaff(String Id) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/views/stages/admin/adminShowStaff/adminShowStaffInformation/adminShowStaffInformation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {

        }
        adminShowStaffInformationController controller = loader.getController();
        controller.initialize(Id);
        boolean showStaff = Dialog.showAndPass("Show Staff", loader.getRoot());
        if (showStaff) {
            //إعادة تحميل الصفحة عند نجاح الإضافة
            Main.FXMLLoaderPane(StagesManager.stageContent, "/main/views/stages/admin/adminShowStaff/adminShowStaff.fxml");
        }

    }
}

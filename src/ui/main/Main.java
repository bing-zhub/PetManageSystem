package ui.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main {

    @FXML
    private StackPane rootPane;

    @FXML
    private Button btnAddAdmin;

    @FXML
    private Button addUser;

    @FXML
    private Button btnAddPet;

    @FXML
    private Button btnAddOrder;

    @FXML
    private Button btnAddAppointment;

    @FXML
    private Button btnAddService;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnAddCategory;

    @FXML
    void AddAppointmentStarter(ActionEvent event) {

    }

    @FXML
    void addAdminStarter(ActionEvent event) {
        showWindow("/ui/add/addOperator/addOperator.fxml","添加管理员");
    }

    @FXML
    void addCategoryStarter(ActionEvent event) {

    }

    @FXML
    void addOrderStater(ActionEvent event) {

    }

    @FXML
    void addPetStarter(ActionEvent event) {

    }

    @FXML
    void addProductStarter(ActionEvent event) {

    }

    @FXML
    void addServiceStarter(ActionEvent event) {

    }

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","添加用户");
    }

    void showWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().showWindow("a","a");
    }

}

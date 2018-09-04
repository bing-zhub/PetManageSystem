package ui.listUser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BeanMyUser;
import model.BeanOperator;
import util.PetManageSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListUser implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<BeanMyUser> tableView;

    @FXML
    private TableColumn<BeanMyUser, Integer> idCol;

    @FXML
    private TableColumn<BeanMyUser, String> usernameCol;

    @FXML
    private TableColumn<BeanMyUser, Integer> telCol;

    @FXML
    private TableColumn<BeanMyUser, String> emailCol;

    @FXML
    private TableColumn<BeanMyUser, String> contactCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("userTel"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("userContact"));
        tableView.setItems(getUser());
    }

    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = PetManageSystemUtil.userController.loadAll();
        for (BeanMyUser e: list){
            users.add(e);
        }
        return users;
    }
}

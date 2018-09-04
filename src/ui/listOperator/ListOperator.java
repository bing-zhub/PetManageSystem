package ui.listOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.BeanOperator;
import util.PetManageSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListOperator implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<BeanOperator> tableView;

    @FXML
    private TableColumn<BeanOperator, Integer> idCol;

    @FXML
    private TableColumn<BeanOperator,String> usernameCol;

    @FXML
    private TableColumn<BeanOperator, Integer> levCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("opId"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("opName"));
        levCol.setCellValueFactory(new PropertyValueFactory<>("opLevel"));
        tableView.setItems(getOperator());
    }

    private ObservableList<BeanOperator> getOperator(){
        ObservableList<BeanOperator> operators = FXCollections.observableArrayList();
        List<BeanOperator> list = PetManageSystemUtil.operatorController.loadAll();
        for (BeanOperator e: list){
            operators.add(e);
        }
        return operators;
    }
}

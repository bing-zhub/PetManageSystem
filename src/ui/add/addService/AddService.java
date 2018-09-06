package ui.add.addService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanCategory;
import model.BeanMyUser;
import model.BeanPet;
import model.BeanService;
import util.PetManageSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddService implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXComboBox<BeanCategory> serviceCategory;

    @FXML
    private JFXTextField serviceName;

    @FXML
    private JFXTextField servicePrice;

    @FXML
    private JFXButton btnOk;

    private boolean isEditMode = false;
    private int servId = 0;

    private ObservableList<BeanCategory> getCategories(){
        ObservableList<BeanCategory> categories = FXCollections.observableArrayList();
        List<BeanCategory> list = PetManageSystemUtil.categoryController.loadAll();
        categories.addAll(list);
        return categories;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void serviceAdd(ActionEvent event) {
        String name = serviceName.getText();
        int price = Integer.parseInt(servicePrice.getText());
        BeanService service = new BeanService();
        String contentText = "";
        if(name.isEmpty()){
            return;
        }

        service.setServName(name);
        service.setServPrice(price);

        if(isEditMode){
            btnOk.setText("修改");
            service.setServId(servId);
            PetManageSystemUtil.update(service);
            contentText = "修改成功";
        }else{
            btnOk.setText("添加");
            PetManageSystemUtil.save(service);
            contentText = "添加成功";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.show();
        cancel(new ActionEvent());
        return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("此为必填项");

        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("请输入纯数字");

        serviceCategory.getValidators().add(requiredFieldValidator);
        serviceName.getValidators().add(requiredFieldValidator);
        servicePrice.getValidators().add(requiredFieldValidator);
        servicePrice.getValidators().add(numberValidator);

        serviceName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    serviceName.validate();
            }
        });

        serviceCategory.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    serviceCategory.validate();
            }
        });

        servicePrice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    servicePrice.validate();
            }
        });

        serviceCategory.setItems(getCategories());

    }

    public void inflateUI(BeanService service){
        serviceName.setText(service.getServName());
        servicePrice.setText(service.getServPrice().toString());
        this.isEditMode = true;
        this.servId = service.getServId();
    }
}

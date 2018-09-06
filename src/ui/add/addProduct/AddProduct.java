package ui.add.addProduct;

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
import model.BeanProduct;
import model.BeanService;
import util.PetManageSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddProduct implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXComboBox<BeanCategory> serviceCategory;

    @FXML
    private JFXTextField productName;

    @FXML
    private JFXTextField productPrice;

    @FXML
    private JFXTextField productBrand;

    @FXML
    private JFXTextField productBarcode;

    @FXML
    private JFXButton btnOk;

    private boolean isEditMode = false;
    private int productId = 0;


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
    void productAdd(ActionEvent event) {
        String name = productName.getText();
        int price = Integer.parseInt(productPrice.getText());
        String brand = productBrand.getText();
        String barcode = productBarcode.getText();
        BeanProduct product = new BeanProduct();
        BeanCategory category = serviceCategory.getSelectionModel().getSelectedItem();
        String contentText = "";
        if(name.isEmpty() || brand.isEmpty() || barcode.isEmpty() || category == null){
            return;
        }

        if(isEditMode){
            btnOk.setText("修改");
            product.setProdId(productId);
            product.setProdName(name);
            product.setProdBarcode(barcode);
            product.setProdBrand(brand);
            product.setProdPrice(price);
            product.setCateId(category.getCateId());
            PetManageSystemUtil.update(product);
            contentText = "修改成功";
        }else{
            btnOk.setText("添加");
            PetManageSystemUtil.save(product);
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
        productName.getValidators().add(requiredFieldValidator);
        productBrand.getValidators().add(requiredFieldValidator);
        productBarcode.getValidators().add(requiredFieldValidator);
        productPrice.getValidators().add(requiredFieldValidator);
        productPrice.getValidators().add(numberValidator);

        productBarcode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    productBarcode.validate();
            }
        });

        serviceCategory.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    serviceCategory.validate();
            }
        });

        productBrand.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    productBrand.validate();
            }
        });

        productName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    productName.validate();
            }
        });

        productPrice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    productPrice.validate();
            }
        });

        serviceCategory.setItems(getCategories());
    }

    public void inflateUI(BeanProduct product){
        productPrice.setText(product.getProdPrice().toString());
        productBarcode.setText(product.getProdBarcode());
        productBrand.setText(product.getProdBrand());
        productName.setText(product.getProdName());
        this.isEditMode = true;
        this.productId = product.getProdId();
    }

}

package ui.add.addOrder;

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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanMyOrder;
import model.BeanMyUser;
import model.BeanOrderDetail;
import model.BeanProduct;
import util.PetManageSystemUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddOrder implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXComboBox<BeanProduct> productBox1;

    @FXML
    private JFXTextField productNum1;

    @FXML
    private Label productPrice1;

    @FXML
    private JFXComboBox<BeanProduct> productBox2;

    @FXML
    private JFXTextField productNum2;

    @FXML
    private Label productPrice2;

    @FXML
    private JFXComboBox<BeanProduct> productBox3;

    @FXML
    private JFXTextField productNum3;

    @FXML
    private Label productPrice3;

    @FXML
    private JFXComboBox<BeanProduct> productBox4;

    @FXML
    private JFXTextField productNum4;

    @FXML
    private Label productPrice4;

    @FXML
    private Label orderTotalPrice;

    @FXML
    private JFXButton btnOk;

    @FXML
    private JFXComboBox<BeanMyUser> userBox;

    private BeanProduct product1 = null;
    private BeanProduct product2 = null;
    private BeanProduct product3 = null;
    private BeanProduct product4 = null;
    private BeanMyUser user = null;
    private int num1 = 0;
    private int num2 = 0;
    private int num3 = 0;
    private int num4 = 0;
    private int price1 = 0;
    private int price2 = 0;
    private int price3 = 0;
    private int price4 = 0;

    ObservableList<BeanProduct> products = FXCollections.observableArrayList();
    ObservableList<BeanMyUser> users = FXCollections.observableArrayList();

    private ObservableList<BeanProduct> getProduct(){
        ObservableList<BeanProduct> products = FXCollections.observableArrayList();
        List<BeanProduct> list = PetManageSystemUtil.productController.loadAll();
        for (BeanProduct e: list){
            products.add(e);
        }
        return products;
    }

    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = PetManageSystemUtil.userController.loadAll();
        for (BeanMyUser e: list){
            users.add(e);
        }
        return users;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    @FXML
    void userSelected(ActionEvent event){
        this.user = userBox.getSelectionModel().getSelectedItem();
    }

    @FXML
    void orderAdd(ActionEvent event) {
        BeanMyOrder order = new BeanMyOrder();
        order.setOrderState("订单创建完成");
        order.setOrderPrice(price1+price2+price3+price4);
        order.setOrderNum(num1+num2+num3+num4);
        order.setOrderUser(user.getUserId());
        PetManageSystemUtil.save(order);

        if(product1!=null && num1 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrder(order);
            detail.setProduct(product1);
            detail.setProdNum(num1);
            PetManageSystemUtil.save(detail);
        }

        if(product2!=null && num2 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrder(order);
            detail.setProduct(product2);
            detail.setProdNum(num2);
            PetManageSystemUtil.save(detail);
        }

        if(product3!=null && num3 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrder(order);
            detail.setProduct(product3);
            detail.setProdNum(num3);
            PetManageSystemUtil.save(detail);
        }

        if(product4!=null && num4 !=0){
            BeanOrderDetail detail = new BeanOrderDetail();
            detail.setOrder(order);
            detail.setProduct(product4);
            detail.setProdNum(num4);
            PetManageSystemUtil.save(detail);
        }

        cancel(new ActionEvent());
        return;
    }

    @FXML
    void product2NumInput(ActionEvent event) {
        try{
            num2 = Integer.parseInt(productNum2.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num2 == 0 || product2 == null){
            productPrice2.setText("价格");
        }else {
            price2 = product2.getProdPrice()*num2;
            productPrice2.setText(String.valueOf(price2));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));
        }
    }

    @FXML
    void product2Selected(ActionEvent event) {
        product2 = productBox2.getSelectionModel().getSelectedItem();

        if(num2 == 0 || product2 == null){
            productPrice2.setText("");
        }else {
            price2 = product2.getProdPrice()*num2;
            productPrice2.setText(String.valueOf(price2));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));
        }

    }

    @FXML
    void product3NumInput(ActionEvent event) {
        try{
            num3 = Integer.parseInt(productNum3.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num3 == 0 || product3 == null){
            productPrice3.setText("");
        }else {
            price3 = product3.getProdPrice()*num3;
            productPrice3.setText(String.valueOf(price3));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));
        }
    }

    @FXML
    void product3Selected(ActionEvent event) {
        product3 = productBox3.getSelectionModel().getSelectedItem();

        if(num3 == 0 || product3 == null){
            productPrice3.setText("");
        }else {
            price3 = product3.getProdPrice()*num3;
            productPrice3.setText(String.valueOf(price3));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));
        }
    }

    @FXML
    void product4NumInput(ActionEvent event) {
        try{
            num4 = Integer.parseInt(productNum4.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num4 == 0 || product4 == null){
            productPrice4.setText("");
        }else {
            price4 = product4.getProdPrice()*num4;
            productPrice4.setText(String.valueOf(price4));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));

        }
    }

    @FXML
    void product4Selected(ActionEvent event) {
        product4 = productBox4.getSelectionModel().getSelectedItem();

        if(num4 == 0 || product4 == null){
            productPrice4.setText("");
        }else {
            price4 = product4.getProdPrice()*num4;
            productPrice4.setText(String.valueOf(price4));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));

        }

    }

    @FXML
    void product1NumInput(ActionEvent event) {
        try{
            num1 = Integer.parseInt(productNum1.getText());
        }catch (Exception e){
            //do nothing
        }

        if(num1 == 0 || product1 == null){
            productPrice1.setText("");
        }else {
            price1 = product1.getProdPrice()*num1;
            productPrice1.setText(String.valueOf(price1));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));

        }
    }

    @FXML
    void product1Selected(ActionEvent event) {
        product1 = productBox1.getSelectionModel().getSelectedItem();

        if(num1 == 0 || product1 == null){
            productPrice1.setText("");
        }else {
            price1 = product1.getProdPrice()*num1;
            productPrice1.setText(String.valueOf(price1));
            orderTotalPrice.setText(String.valueOf(price1+price2+price3+price4));
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.products = getProduct();
        this.users = getUser();
        productBox1.setItems(products);
        productBox2.setItems(products);
        productBox3.setItems(products);
        productBox4.setItems(products);
        userBox.setItems(users);

        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        NumberValidator numberValidator = new NumberValidator();
        requiredFieldValidator.setMessage("此为必填项");
        numberValidator.setMessage("必须为纯数字");

        productBox1.getValidators().add(requiredFieldValidator);
        productNum1.getValidators().add(requiredFieldValidator);
        productNum1.getValidators().add(numberValidator);
        productNum2.getValidators().add(numberValidator);
        productNum3.getValidators().add(numberValidator);
        productNum4.getValidators().add(numberValidator);
        userBox.getValidators().add(requiredFieldValidator);


        userBox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    userBox.validate();
                }
            }
        });

        productBox1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productBox1.validate();
                }
            }
        });

        productNum1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum1.validate();
                }
            }
        });

        productNum2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum2.validate();
                }
            }
        });

        productNum3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum3.validate();
                }
            }
        });

        productNum4.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    productNum4.validate();
                }
            }
        });
    }
}

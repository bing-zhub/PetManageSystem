package ui.main;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import util.BaseException;
import util.PetManageSystemUtil;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Main implements Initializable{

    @FXML
    private Text orderUser;

    @FXML
    private Text orderPrice;

    @FXML
    private Text orderTel;

    @FXML
    private Text orderStatus;

    @FXML
    private Text appUser;

    @FXML
    private Text appDate;

    @FXML
    private Text appPet;

    @FXML
    private Text appStatus;

    @FXML
    private JFXTextField appId;

    @FXML
    private JFXTextField orderId;

    @FXML
    private TableView<BeanOperator> operatorTbl;

    @FXML
    private TableColumn<BeanOperator, Integer> OperatorIdCol;

    @FXML
    private TableColumn<BeanOperator, String> OperatornameCol;

    @FXML
    private TableColumn<BeanOperator, Integer> OPeratorlevCol;

    @FXML
    private TableView<BeanMyUser> userTbl;

    @FXML
    private TableColumn<BeanMyUser, Integer> UserIdCol;

    @FXML
    private TableColumn<BeanMyUser, String> UsernameCol;

    @FXML
    private TableColumn<BeanMyUser, Integer> UserTelCol;

    @FXML
    private TableColumn<BeanMyUser, String> UserEmailCol;

    @FXML
    private TableColumn<BeanMyUser, String> UserContactCol;

    @FXML
    private TableView<BeanPet> petTbl;

    @FXML
    private TableColumn<BeanPet, Integer> PetIdCol;

    @FXML
    private TableColumn<BeanPet, Integer> PetOwnerCol;

    @FXML
    private TableColumn<BeanPet, String> PetNikenameCol;

    @FXML
    private TableColumn<BeanPet, String> PetAliasCol;

    @FXML
    private TableView<BeanService> serviceTbl;

    @FXML
    private TableColumn<BeanService, Integer> ServiceIdCol;

    @FXML
    private TableColumn<BeanService, String> ServiceNameCol;

    @FXML
    private TableColumn<BeanService, Integer> ServicePriceCol;

    @FXML
    private TableView<BeanProduct> productTbl;

    @FXML
    private TableColumn<BeanProduct, Integer> ProductIdCol;

    @FXML
    private TableColumn<BeanProduct, String> ProductNameCol;

    @FXML
    private TableColumn<BeanProduct, String> ProductBrandCol;

    @FXML
    private TableColumn<BeanProduct, Integer> ProductPriceCol;

    @FXML
    private TableColumn<BeanProduct, String> ProductBarcodeCol;

    @FXML
    private TableView<BeanCategory> categoryTbl;

    @FXML
    private TableColumn<BeanCategory, Integer> CategoryIdCol;

    @FXML
    private TableColumn<BeanCategory, String> CategoryNameCol;

    @FXML
    private TableColumn<BeanCategory, String> CategoryDetailCol;

    private ObservableList<BeanOperator> operators = null;
    private ObservableList<BeanMyUser> users = null;
    private ObservableList<BeanPet> pets = null;
    private ObservableList<BeanService> services = null;
    private ObservableList<BeanProduct> products = null;
    private ObservableList<BeanCategory> categories = null;

    @FXML
    void AddAppointmentStarter(ActionEvent event) {

    }

    @FXML
    void addAdminStarter(ActionEvent event) {
        showWindow("/ui/add/addOperator/addOperator.fxml","��ӹ���Ա");
    }

    @FXML
    void addCategoryStarter(ActionEvent event) {

    }

    @FXML
    void addOrderStater(ActionEvent event) {

    }

    @FXML
    void addPetStarter(ActionEvent event) {
        showWindow("/ui/test/test.fxml","����û�");
    }

    @FXML
    void addProductStarter(ActionEvent event) {
        showWindow("/ui/login/login.fxml","��¼");
    }

    @FXML
    void addServiceStarter(ActionEvent event) {

    }

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","����û�");
    }

    void showWindow(String loc, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadAppInfo(ActionEvent event) {
        try{
            int id = Integer.parseInt(appId.getText());
            System.out.println(1);
            BeanAppointment appointment = null;
            appointment = PetManageSystemUtil.appointmentController.findAppointmentById(id);
            BeanPet pet = PetManageSystemUtil.petController.findPetById(appointment.getPetId());
            BeanMyUser user = PetManageSystemUtil.userController.findUserById(appointment.getUserId());
            appDate.setText(appointment.getAppDate().toString());
            appPet.setText(pet.getPetNikename());
            appUser.setText(user.getUserName());
            appStatus.setText((appointment.getAppDoneDate().toString().equals(new Date(System.currentTimeMillis()).toString()))?"δ���":"�����");
        }catch (Exception e){
            System.out.println(e.getMessage());
            appUser.setText("");
            appDate.setText("");
            appStatus.setText("�����Ŵ���");
            appPet.setText("");
        }
    }

    @FXML
    void deleteOperator(ActionEvent event) {
        BeanOperator operator = operatorTbl.getSelectionModel().getSelectedItem();
        if(operator == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ���Ĺ���Ա");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ������Ա"+operator.getOpName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            PetManageSystemUtil.operatorController.delOperator(operator.getOpId());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("����Ա"+operator.getOpName()+"��ɾ��");
            a.showAndWait();
            operators.remove(operator);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void deleteUser (ActionEvent event){
        BeanMyUser user = userTbl.getSelectionModel().getSelectedItem();
        if(user == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ�����û�");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ���û�"+user.getUserName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.userController.delUser(user.getUserId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("���û�Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("�û�"+user.getUserName()+"��ɾ��");
            a.showAndWait();
            users.remove(user);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void deletePet(ActionEvent event){
        BeanPet pet = petTbl.getSelectionModel().getSelectedItem();
        if(pet == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ���ĳ���");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ������"+pet.getPetNikename()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.petController.delPet(pet.getPetId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("�ó���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("����"+pet.getPetNikename()+"��ɾ��");
            a.showAndWait();
            pets.remove(pet);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void deleteService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ���ķ���");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ������"+service.getServName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.serviceController.delService(service.getServId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("�÷���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("����"+service.getServName()+"��ɾ��");
            a.showAndWait();
            services.remove(service);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void deleteProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ���Ĳ�Ʒ");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ����Ʒ"+product.getProdName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.productController.delProduct(product.getProdId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("�ò�ƷĿǰ���ڻ�Ծ״̬,����ɾ��");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("��Ʒ"+product.getProdName()+"��ɾ��");
            a.showAndWait();
            products.remove(product);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void deleteCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("��ѡ��Ҫɾ���ķ���");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("ȷ��");
        alert.setContentText("�Ƿ�Ҫɾ������"+category.getCateName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.categoryController.delCategory(category.getCateId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("�÷���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("����"+category.getCateName()+"��ɾ��");
            a.showAndWait();
            categories.remove(category);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("ȡ��ɾ������");
            a.showAndWait();
            return;
        }
    }

    @FXML
    void loadOrderInfo(ActionEvent event) {
        try{
            int id = Integer.parseInt(orderId.getText());
            BeanMyOrder order = null;
            order = PetManageSystemUtil.orderController.findOrderById(id);
            int userId = order.getOrderUser();
            String status = order.getOrderState();
            int price = order.getOrderPrice();
            BeanMyUser user = PetManageSystemUtil.userController.findUserById(userId);
            String tel = user.getUserTel().toString();
            orderUser.setText(user.getUserName());
            orderPrice.setText(String.valueOf(price));
            orderStatus.setText(status);
            orderTel.setText(tel);
        }catch (Exception e){
            orderUser.setText("");
            orderPrice.setText("");
            orderStatus.setText("�����Ŵ���");
            orderTel.setText("");
        }
    }

    private void initCol() {
        OperatorIdCol.setCellValueFactory(new PropertyValueFactory<>("opId"));
        OperatornameCol.setCellValueFactory(new PropertyValueFactory<>("opName"));
        OPeratorlevCol.setCellValueFactory(new PropertyValueFactory<>("opLevel"));
        operatorTbl.setItems(operators);

        UserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        UserEmailCol.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        UserTelCol.setCellValueFactory(new PropertyValueFactory<>("userTel"));
        UserContactCol.setCellValueFactory(new PropertyValueFactory<>("userContact"));
        userTbl.setItems(users);

        PetIdCol.setCellValueFactory(new PropertyValueFactory<>("petId"));
        PetNikenameCol.setCellValueFactory(new PropertyValueFactory<>("petNikename"));
        PetAliasCol.setCellValueFactory(new PropertyValueFactory<>("petAlias"));
        PetOwnerCol.setCellValueFactory(new PropertyValueFactory<>("petOwner"));
        petTbl.setItems(pets);

        ServiceIdCol.setCellValueFactory(new PropertyValueFactory<>("servId"));
        ServiceNameCol.setCellValueFactory(new PropertyValueFactory<>("servName"));
        ServicePriceCol.setCellValueFactory(new PropertyValueFactory<>("servPrice"));
        serviceTbl.setItems(services);

        ProductIdCol.setCellValueFactory(new PropertyValueFactory<>("prodId"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        ProductBrandCol.setCellValueFactory(new PropertyValueFactory<>("prodBrand"));
        ProductBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("prodBarcode"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("prodPrice"));
        productTbl.setItems(products);

        CategoryIdCol.setCellValueFactory(new PropertyValueFactory<>("cateId"));
        CategoryDetailCol.setCellValueFactory(new PropertyValueFactory<>("cateDetail"));
        CategoryNameCol.setCellValueFactory(new PropertyValueFactory<>("cateName"));
        categoryTbl.setItems(categories);



    }

    private ObservableList<BeanOperator> getOperator(){
        ObservableList<BeanOperator> operators = FXCollections.observableArrayList();
        List<BeanOperator> list = PetManageSystemUtil.operatorController.loadAll();
        for (BeanOperator e: list){
            operators.add(e);
        }
        return operators;
    }

    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = PetManageSystemUtil.userController.loadAll();
        for (BeanMyUser e: list){
            users.add(e);
        }
        return users;
    }

    private ObservableList<BeanPet> getPet(){
        ObservableList<BeanPet> pets = FXCollections.observableArrayList();
        List<BeanPet> list = PetManageSystemUtil.petController.loadAll();
        for (BeanPet e: list){
            pets.add(e);
        }
        return pets;
    }

    private ObservableList<BeanService> getService(){
        ObservableList<BeanService> services = FXCollections.observableArrayList();
        List<BeanService> list = PetManageSystemUtil.serviceController.loadAll();
        for (BeanService e: list){
            services.add(e);
        }
        return services;
    }

    private ObservableList<BeanProduct> getProduct(){
        ObservableList<BeanProduct> products = FXCollections.observableArrayList();
        List<BeanProduct> list = PetManageSystemUtil.productController.loadAll();
        for (BeanProduct e: list){
            products.add(e);
        }
        return products;
    }

    private ObservableList<BeanCategory> getCategory(){
        ObservableList<BeanCategory> operators = FXCollections.observableArrayList();
        List<BeanCategory> list = PetManageSystemUtil.categoryController.loadAll();
        for (BeanCategory e: list){
            operators.add(e);
        }
        return operators;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NumberValidator numberValidator = new NumberValidator();
        try{
            Image image = new Image(new FileInputStream("src/ui/icons/error-sign.png"));
            numberValidator.setIcon(new ImageView(image));

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        numberValidator.setMessage("�����봿����");

        appId.getValidators().add(numberValidator);
        orderId.getValidators().add(numberValidator);

        appId.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    appId.validate();
                }
            }
        });

        orderId.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    orderId.validate();
                }
            }
        });

        operators = getOperator();
        users = getUser();
        categories =  getCategory();
        pets =  getPet();
        products = getProduct();
        services=  getService();

        initCol();

    }
}

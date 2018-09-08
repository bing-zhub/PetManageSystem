package ui.main;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import ui.add.addCategory.AddCategory;
import ui.add.addOperator.AddOperator;
import ui.add.addPet.AddPet;
import ui.add.addProduct.AddProduct;
import ui.add.addService.AddService;
import ui.add.addUser.AddUser;
import util.PetManageSystemUtil;

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
    private TableView<BeanOrderDetail> orderTbl;

    @FXML
    private JFXComboBox<BeanMyOrder> orderBox;

    @FXML
    private TableColumn<BeanOrderDetail, BeanProduct> orderProductCol;

    @FXML
    private TableColumn<BeanOrderDetail, Integer> orderNumCol;

    @FXML
    private JFXComboBox<BeanAppointment> appointmentBox;

    @FXML
    private TableView<BeanAppointmentDetail> appointmentTbl;

    @FXML
    private TableColumn<BeanAppointmentDetail, BeanService> appointmentServiceCol;

    @FXML
    private TableColumn<BeanAppointmentDetail, Integer> appointmentTimesCol;


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
    private TableColumn<BeanProduct, BeanCategory> ProductCategory;

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
    private ObservableList<BeanOrderDetail> orderDetails = null;
    private ObservableList<BeanAppointmentDetail> appointmentDetails = null;

    @FXML
    void AddAppointmentStarter(ActionEvent event) {

    }

    @FXML
    void addAdminStarter(ActionEvent event) {
        showWindow("/ui/add/addOperator/addOperator.fxml","添加管理员");
    }

    @FXML
    void addCategoryStarter(ActionEvent event) {
        showWindow("/ui/add/addCategory/addCategory.fxml","添加分类");
    }

    @FXML
    void addOrderStater(ActionEvent event) {
        showWindow("/ui/add/addOrder/addOrder.fxml", "添加订单");
    }

    @FXML
    void addPetStarter(ActionEvent event) {
        showWindow("/ui/add/addPet/addPet.fxml","添加宠物");
    }

    @FXML
    void addProductStarter(ActionEvent event) {
        showWindow("/ui/add/addProduct/addProduct.fxml","添加产品");
    }

    @FXML
    void addServiceStarter(ActionEvent event) {
        showWindow("/ui/add/addService/addService.fxml","添加服务");
    }

    @FXML
    void addUserStarter(ActionEvent event) {
        showWindow("/ui/add/addUser/addUser.fxml","添加用户");
    }

    private void showWindow(String loc, String title){
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
            appStatus.setText((appointment.getAppDoneDate().toString().equals(new Date(System.currentTimeMillis()).toString()))?"未完成":"已完成");
        }catch (Exception e){
            System.out.println(e.getMessage());
            appUser.setText("");
            appDate.setText("");
            appStatus.setText("订单号错误");
            appPet.setText("");
        }
    }


    /*
    *  Delete Operation
    *
    * */
    @FXML
    void deleteOperator(ActionEvent event) {
        BeanOperator operator = operatorTbl.getSelectionModel().getSelectedItem();
        if(operator == null){
            alertForSelectNothing("管理员");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除管理员"+operator.getOpName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            PetManageSystemUtil.operatorController.delOperator(operator.getOpId());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("管理员"+operator.getOpName()+"已删除");
            a.showAndWait();
            operators.remove(operator);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            alertForCancel("删除");
            return;
        }
    }

    @FXML
    void deleteUser (ActionEvent event){
        BeanMyUser user = userTbl.getSelectionModel().getSelectedItem();
        if(user == null){
            alertForSelectNothing("用户");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除用户"+user.getUserName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.userController.delUser(user.getUserId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("该用户目前处于活跃状态,不可删除");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("用户"+user.getUserName()+"已删除");
            a.showAndWait();
            users.remove(user);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
           alertForCancel("删除");
            return;
        }
    }

    @FXML
    void deletePet(ActionEvent event){
        BeanPet pet = petTbl.getSelectionModel().getSelectedItem();
        if(pet == null){
            alertForSelectNothing("宠物");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除宠物"+pet.getPetNikename()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.petController.delPet(pet.getPetId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("该宠物目前处于活跃状态,不可删除");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("宠物"+pet.getPetNikename()+"已删除");
            a.showAndWait();
            pets.remove(pet);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            alertForCancel("删除");
            return;
        }
    }

    @FXML
    void deleteProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            alertForSelectNothing("产品");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除产品"+product.getProdName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.productController.delProduct(product.getProdId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("该产品目前处于活跃状态,不可删除");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("产品"+product.getProdName()+"已删除");
            a.showAndWait();
            products.remove(product);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            alertForCancel("删除");
            return;
        }
    }

    @FXML
    void deleteCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("请选择要删除的分类");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除分类"+category.getCateName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.categoryController.delCategory(category.getCateId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("该分类目前处于活跃状态,不可删除");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("分类"+category.getCateName()+"已删除");
            a.showAndWait();
            categories.remove(category);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            alertForCancel("分类");
            return;
        }
    }


    @FXML
    void deleteService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            alertForSelectNothing("服务");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("确认");
        alert.setContentText("是否要删除服务"+service.getServName()+" ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            try {
                PetManageSystemUtil.serviceController.delService(service.getServId());
            } catch (Exception e) {
                Alert t = new Alert(Alert.AlertType.ERROR);
                t.setHeaderText(null);
                t.setContentText("该服务目前处于活跃状态,不可删除");
                t.showAndWait();
                return;
            }
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(null);
            a.setContentText("服务"+service.getServName()+"已删除");
            a.showAndWait();
            services.remove(service);
            return;
        }else if(answer.get() == ButtonType.CANCEL){
            alertForCancel("删除");
            return;
        }
    }

    @FXML
    void deleteOrderProduct(ActionEvent event){}

    @FXML
    void deleteOrder(ActionEvent event){}

    /*
    *  Edit operation
    * */

    @FXML
    void editOperator(ActionEvent event){
        BeanOperator beanOperator = operatorTbl.getSelectionModel().getSelectedItem();
        if(beanOperator == null){
            alertForSelectNothing("管理员");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOperator/addOperator.fxml"));
            Parent parent = loader.load();
            AddOperator addOperator = (AddOperator) loader.getController();
            addOperator.inflateUI(beanOperator);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑管理员");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editPet(ActionEvent event){
        BeanPet beanPet = petTbl.getSelectionModel().getSelectedItem();
        if(beanPet == null){
            alertForSelectNothing("宠物");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addPet/addPet.fxml"));
            Parent parent = loader.load();
            AddPet addPet = (AddPet) loader.getController();
            addPet.inflateUI(beanPet);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑宠物");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        refreshPet(new ActionEvent());
    }

    @FXML
    void editUser(ActionEvent event){
        BeanMyUser beanUser = userTbl.getSelectionModel().getSelectedItem();
        if(beanUser == null){
            alertForSelectNothing("用户");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addUser/addUser.fxml"));
            Parent parent = loader.load();
            AddUser addUser = (AddUser) loader.getController();
            addUser.inflateUI(beanUser);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑用户");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            alertForSelectNothing("服务");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addService/addService.fxml"));
            Parent parent = loader.load();
            AddService addService = (AddService) loader.getController();
            addService.inflateUI(service);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑服务");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            alertForSelectNothing("产品");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addProduct/addProduct.fxml"));
            Parent parent = loader.load();
            AddProduct addProduct = (AddProduct) loader.getController();
            addProduct.inflateUI(product);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑产品");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            alertForSelectNothing("分类");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addCategory/addCategory.fxml"));
            Parent parent = loader.load();
            AddCategory addCategory = (AddCategory) loader.getController();
            addCategory.inflateUI(category);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("编辑分类");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editOrder(ActionEvent event){}

    /*
    * refresh operation
    * */
    @FXML
    void refreshOperator(ActionEvent event){
        operators.clear();
        operators = getOperator();
        operatorTbl.setItems(operators);
    }

    @FXML
    void refreshPet(ActionEvent event){
        pets.clear();
        pets = getPet();
        petTbl.setItems(pets);
    }

    @FXML
    void refreshUser(ActionEvent event){
        users.clear();
        users = getUser();
        userTbl.setItems(users);
    }

    @FXML
    void refreshProduct(ActionEvent event){
        products.clear();
        products = getProduct();
        productTbl.setItems(products);
    }

    @FXML
    void refreshCategory(ActionEvent event){
        categories.clear();
        categories = getCategory();
        categoryTbl.setItems(categories);
    }

    @FXML
    void refreshOrder(ActionEvent event){
        orderBox.setItems(getOrder());
        orderDetails.clear();
        orderDetails = getOrderDetail();
        orderTbl.setItems(orderDetails);
    }

    @FXML
    void refreshService(ActionEvent event){
        services.clear();
        services = getService();
        serviceTbl.setItems(services);
    }

    private void alertForSelectNothing(String cate){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("请选择要删除的"+cate);
        alert.showAndWait();
    }

    private void alertForCancel(String cate){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText("取消"+cate+"操作");
        a.showAndWait();
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
            orderStatus.setText("订单号错误");
            orderTel.setText("");
        }
    }

    @FXML
    void selectOrderId(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        orderDetails.clear();
        orderDetails = getOrderDetail( order.getOrderId());
        orderTbl.setItems(orderDetails);
    }

    @FXML
    void selectAppointmentId(ActionEvent event){
        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
        appointmentDetails.clear();
        appointmentDetails = getAppointmentDetail(appointment.getAppId());
        appointmentTbl.setItems(appointmentDetails);
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
        PetOwnerCol.setCellValueFactory(new PropertyValueFactory<>("user"));
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
        ProductCategory.setCellValueFactory(new PropertyValueFactory<>("prodCategory"));
        productTbl.setItems(products);

        CategoryIdCol.setCellValueFactory(new PropertyValueFactory<>("cateId"));
        CategoryDetailCol.setCellValueFactory(new PropertyValueFactory<>("cateDetail"));
        CategoryNameCol.setCellValueFactory(new PropertyValueFactory<>("cateName"));
        categoryTbl.setItems(categories);

        orderProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        orderNumCol.setCellValueFactory(new PropertyValueFactory<>("prodNum"));
        orderTbl.setItems(orderDetails);

        appointmentServiceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        appointmentTimesCol.setCellValueFactory(new PropertyValueFactory<>("times"));
        appointmentTbl.setItems(appointmentDetails);
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

    private ObservableList<BeanOrderDetail> getOrderDetail(){
        ObservableList<BeanOrderDetail> details = FXCollections.observableArrayList();
        List<BeanOrderDetail> list = PetManageSystemUtil.orderController.loadAllDetails();
        for (BeanOrderDetail e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanMyOrder> getOrder(){
        ObservableList<BeanMyOrder> details = FXCollections.observableArrayList();
        List<BeanMyOrder> list = PetManageSystemUtil.orderController.loadAll();
        for (BeanMyOrder e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanOrderDetail> getOrderDetail(int orderId){
        ObservableList<BeanOrderDetail> details = FXCollections.observableArrayList();
        List<BeanOrderDetail> list = PetManageSystemUtil.orderController.loadDetailByOrderId(orderId);
        for (BeanOrderDetail e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanAppointmentDetail> getAppointmentDetail(){
        ObservableList<BeanAppointmentDetail> details = FXCollections.observableArrayList();
        List<BeanAppointmentDetail> list = PetManageSystemUtil.appointmentController.loadAllDetails();
        for (BeanAppointmentDetail e: list){
            details.add(e);
        }
        return details;
    }

    private ObservableList<BeanAppointmentDetail> getAppointmentDetail(int appId){
        ObservableList<BeanAppointmentDetail> details = FXCollections.observableArrayList();
        List<BeanAppointmentDetail> list = PetManageSystemUtil.appointmentController.loadDetailByAppointmentId(appId);
        details.addAll(list);
        return details;
    }

    private ObservableList<BeanAppointment> getAppointment(){
        ObservableList<BeanAppointment> appointments = FXCollections.observableArrayList();
        List<BeanAppointment> list = PetManageSystemUtil.appointmentController.loadAll();
        appointments.addAll(list);
        return appointments;
    }


    private void loadData(){
        this.operators = getOperator();
        this.users = getUser();
        this.categories =  getCategory();
        this.pets =  getPet();
        this.products = getProduct();
        this.services=  getService();
        this.orderDetails = getOrderDetail();
        this.appointmentDetails = getAppointmentDetail();
        orderBox.setItems(getOrder());
        appointmentBox.setItems(getAppointment());
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
        numberValidator.setMessage("请输入纯数字");

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

        loadData();

        initCol();

    }
}

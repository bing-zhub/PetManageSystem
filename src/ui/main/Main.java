package ui.main;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.jfoenix.validation.NumberValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import ui.add.addAppointment.AddAppointment;
import ui.add.addCategory.AddCategory;
import ui.add.addOperator.AddOperator;
import ui.add.addOrder.AddOrder;
import ui.add.addPet.AddPet;
import ui.add.addProduct.AddProduct;
import ui.add.addService.AddService;
import ui.add.addUser.AddUser;
import ui.add.barcode.Barcode;
import util.BaseException;
import util.PetManageSystemUtil;
import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Main implements Initializable{

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Text orderUser;

    @FXML
    private Text orderPrice;

    @FXML
    private Text orderTel;

    @FXML
    private Text orderStatus;

    @FXML
    private JFXComboBox<String> choiceType1;

    @FXML
    private JFXComboBox<String> choiceType2;

    @FXML
    private Text appUser;

    @FXML
    private Text appDate;

    @FXML
    private Text appPet;

    @FXML
    private Text appStatus;

    @FXML
    private JFXTextField keyword;

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
    private TableColumn<BeanAppointmentDetail, BeanPet> appointmentPetCol;

    @FXML
    private TableColumn<BeanAppointmentDetail, LocalDate> appointmentDateCol;

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

    @FXML
    private Text adminTotal;

    @FXML
    private Text userTotal;

    @FXML
    private Text PetTotal;

    @FXML
    private Text orderTotal;

    @FXML
    private Text appointmentTotal;

    @FXML
    private Text serviceTotal;

    @FXML
    private Text categoryTotal;

    @FXML
    private Text productTotal;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private VBox charContainer;

    private ObservableList<BeanOperator> operators = null;
    private ObservableList<BeanMyUser> users = null;
    private ObservableList<BeanPet> pets = null;
    private ObservableList<BeanService> services = null;
    private ObservableList<BeanProduct> products = null;
    private ObservableList<BeanCategory> categories = null;
    private ObservableList<BeanOrderDetail> orderDetails = null;
    private ObservableList<BeanAppointmentDetail> appointmentDetails = null;
    private PieChart orderPie = null;
    private PieChart appointmentPie = null;
    private String choice1 = "";
    private String choice2 = "";

//    @FXML
//    void loadAppInfo(ActionEvent event) {
//        try{
//            int id = Integer.parseInt(appId.getText());
//            System.out.println(1);
//            BeanAppointment appointment = null;
//            appointment = PetManageSystemUtil.appointmentController.findAppointmentById(id);
//        }catch (Exception exception){
//            System.out.println(exception.getMessage());
//            appUser.setText("");
//            appDate.setText("");
//            appStatus.setText("订单号错误");
//            appPet.setText("");
//        }
//    }

    @FXML
    void showProductBarcode(ActionEvent event) {
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            showDialog("请选择要操作的产品");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/barcode/barcode.fxml"));
            Parent parent = loader.load();
            Barcode barcode = loader.getController();
            barcode.inflateUI(product.getProdBarcode());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("条形码");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
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
            showDialog("请选择要操作的管理员");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try{
                PetManageSystemUtil.operatorController.delOperator(operator.getOpId());
                operators.remove(operator);
                showDialog("管理员"+operator.getOpName()+"已删除");
            }catch (BaseException e1){
                showDialog(e1.getMessage());
            }
        });
        showConfirmDialog("是否要删除管理员"+operator.getOpName()+" ?", Arrays.asList(btnCancel,btnOK));
    }

    @FXML
    void deleteUser (ActionEvent event){
        BeanMyUser user = userTbl.getSelectionModel().getSelectedItem();
        if(user == null){
            showDialog("请选择要操作的用户");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.userController.delUser(user.getUserId());
            } catch (Exception exception1) {
                showDialog("该用户目前处于活跃状态,不可删除");
            }
            users.remove(user);
            showDialog("用户"+user.getUserName()+"已删除");
        });
       showConfirmDialog("是否要删除用户"+user.getUserName()+" ?",Arrays.asList(btnCancel, btnOK));

    }

    @FXML
    void deletePet(ActionEvent event){
        BeanPet pet = petTbl.getSelectionModel().getSelectedItem();
        if(pet == null){
            showDialog("请选择要操作的宠物");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.petController.delPet(pet.getPetId());
            } catch (Exception exception1) {
                showDialog("该宠物目前处于活跃状态,不可删除");
                return;
            }
            pets.remove(pet);
            showDialog("宠物"+pet.getPetNikename()+"已删除");
        });
        showConfirmDialog("是否要删除宠物"+pet.getPetNikename()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            showDialog("请选择要操作的产品");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.productController.delProduct(product.getProdId());
            } catch (Exception exception1) {
                showDialog("该产品目前处于活跃状态,不可删除");
                return;
            }
            products.remove(product);
            showDialog("产品"+product.getProdName()+"已删除");
        });
        showConfirmDialog("是否要删除产品"+product.getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            showDialog("请选择要删除的分类");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.categoryController.delCategory(category.getCateId());
            } catch (Exception exception) {
                showDialog("该分类目前处于活跃状态,不可删除");
                return;
            }
            categories.remove(category);
            showDialog("分类"+category.getCateName()+"已删除");
        });
        showConfirmDialog("是否要删除分类"+category.getCateName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            showDialog("请选择要操作的服务");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.serviceController.delService(service.getServId());
            } catch (Exception exception) {
                showDialog("该服务目前处于活跃状态,不可删除");
                return;
            }
            services.remove(service);
            showDialog("服务"+service.getServName()+"已删除");
        });
        showConfirmDialog("是否要删除服务"+service.getServName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteOrderProduct(ActionEvent event){
        BeanOrderDetail detail = orderTbl.getSelectionModel().getSelectedItem();
        if(detail == null){
            showDialog("请选择要操作的产品");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.orderController.delOrderDetail(detail);
            } catch (Exception exception) {
                showDialog("该产品目前处于活跃状态,不可删除");
                return;
            }
            orderDetails.remove(detail);

            List<BeanAppointment> list = PetManageSystemUtil.appointmentController.loadAll();
            for(BeanAppointment a1 : list){
                List<BeanAppointmentDetail> details = PetManageSystemUtil.appointmentController.loadDetailByAppointmentId(a1.getAppId());
                if(details.size() == 0)
                    PetManageSystemUtil.appointmentController.delAppointment(a1.getAppId());
            }
            showDialog("产品"+detail.getProduct().getProdName()+"已删除");
        });
        showConfirmDialog("是否要删除产品"+detail.getProduct().getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的产品");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            PetManageSystemUtil.orderController.delOrder(order.getOrderId());
            showDialog("订单"+order.getOrderId()+"已删除");
        });
        showConfirmDialog("是否要删除订单"+order.getOrderId()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteAppointmentService(ActionEvent event){
        BeanAppointmentDetail detail = appointmentTbl.getSelectionModel().getSelectedItem();
        if(detail == null){
            showDialog("请选择要操作的产品");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.appointmentController.delAppointmentDetail(detail);
            } catch (Exception exception) {
                showDialog("该预约目前处于活跃状态,不可删除");
                return;
            }
            appointmentDetails.remove(detail);

            List<BeanAppointment> list = PetManageSystemUtil.appointmentController.loadAll();
            for(BeanAppointment a1 : list){
                List<BeanAppointmentDetail> details = PetManageSystemUtil.appointmentController.loadDetailByAppointmentId(a1.getAppId());
                if(details.size() == 0) {
                    PetManageSystemUtil.appointmentController.delAppointment(a1.getAppId());
                }
            }

            showDialog("预约"+detail.getService().getServName()+"已删除");
        });
        showConfirmDialog("是否要删除预约"+detail.getService().getServName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteAppointment(ActionEvent event){
        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
        if(appointment == null){
            showDialog("请选择要操作的产品");
            return;
        }
        JFXButton btnOK = new JFXButton("去意已决");
        JFXButton btnCancel = new JFXButton("再想想");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("删除");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            PetManageSystemUtil.appointmentController.delAppointment(appointment.getAppId());
            showDialog("预约"+appointment.getAppId()+"已删除");
        });
        showConfirmDialog("是否要删除预约"+appointment.getAppId()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    /*
    *  Edit operation
    * */

    @FXML
    void editOperator(ActionEvent event){
        BeanOperator beanOperator = operatorTbl.getSelectionModel().getSelectedItem();
        if(beanOperator == null){
            showDialog("请选择要操作的管理员");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editPet(ActionEvent event){
        BeanPet beanPet = petTbl.getSelectionModel().getSelectedItem();
        if(beanPet == null){
            showDialog("请选择要操作的宠物");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        refreshPet(new ActionEvent());
    }

    @FXML
    void editUser(ActionEvent event){
        BeanMyUser beanUser = userTbl.getSelectionModel().getSelectedItem();
        if(beanUser == null){
            showDialog("请选择要操作的用户");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            showDialog("请选择要操作的服务");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            showDialog("请选择要操作的产品");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            showDialog("请选择要操作的分类");
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOrder/addOrder.fxml"));
            Parent parent = loader.load();
            AddOrder addOrder = (AddOrder) loader.getController();
            addOrder.inflateUI(order);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("编辑订单");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void editAppointmentService(ActionEvent event){
        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
        if(appointment == null){
            showDialog("请选择要操作的预约");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addAppointment/addAppointment.fxml"));
            Parent parent = loader.load();
            AddAppointment addAppointment = (AddAppointment) loader.getController();
            addAppointment.inflateUI(appointment);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("编辑预约");
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

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
        orderBox.getItems().clear();
        orderBox.setItems(getOrder());
        orderDetails.clear();
        orderDetails = getOrderDetail();
        orderTbl.setItems(orderDetails);
    }

    @FXML
    void refreshService(ActionEvent event){
        appointmentBox.getItems().clear();
        appointmentBox.setItems(getAppointment());
        services.clear();
        services = getService();
        serviceTbl.setItems(services);
    }

    @FXML
    void refreshAppointment(ActionEvent event){
        appointmentBox.setItems(getAppointment());
        appointmentDetails.clear();
        appointmentDetails = getAppointmentDetail();
        appointmentTbl.setItems(appointmentDetails);
    }

    /*
    * Other
    * */
    @FXML
    void levelUpOperator(ActionEvent event){

    }

    @FXML
    void levelDownOperator(ActionEvent event){}

    @FXML
    void sendOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("发货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderState("已发货");
            PetManageSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已发货");
        });
        showConfirmDialog("是否已将订单"+order.getOrderId()+"货物全部发出 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的订单");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("确认收货");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderState("已完成");
            PetManageSystemUtil.update(order);
            showDialog("订单"+order.getOrderId()+"已收货");
        });
        showConfirmDialog("订单"+order.getOrderId()+"货物全部确认收货 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishAppointment(ActionEvent event){
        BeanAppointment order = appointmentBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("请选择要操作的预约");
            return;
        }
        JFXButton btnOK = new JFXButton("是");
        JFXButton btnCancel = new JFXButton("否");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("预约完成");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setAppState("已完成");
            PetManageSystemUtil.update(order);
            showDialog("预约"+order.getAppId()+"已完成");
        });
        showConfirmDialog("预约"+order.getAppId()+"服务全部完成 ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void OrderOrAppointmentChoice(ActionEvent event){
        choice1 = choiceType1.getSelectionModel().getSelectedItem();
    }

    @FXML
    void otherChoice(ActionEvent event){
        choice2 = choiceType2.getSelectionModel().getSelectedItem();
    }

    private void showDialog(String message){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("已知晓");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            dialog.close();
        });
        dialogLayout.setHeading(new Label(message));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

    private void showCancelDialog(String cate){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("已知晓");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            rootAnchorPane.setEffect(null);
            dialog.close();
        });
        dialogLayout.setHeading(new Label("取消"+cate+"操作"));
        dialogLayout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

    private void showConfirmDialog(String message, List<JFXButton> buttons){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
        dialogLayout.setHeading(new Label(message));
        buttons.forEach(jfxButton -> {jfxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> dialog.close());});
        dialogLayout.setActions(buttons);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent e) -> rootAnchorPane.setEffect(null));
        rootAnchorPane.setEffect(blur);
    }

    @FXML
    void loadOrderOrAppointmentInfo(ActionEvent event) {
        if(choice1.isEmpty()){
            showDialog("没有选择要查询数据类型");
            return;
        }

        try{
            int id = Integer.parseInt(orderId.getText());
            if(choice1.equals("预约")){
                BeanAppointment appointment = null;
                appointment = PetManageSystemUtil.appointmentController.findAppointmentById(id);
                orderUser.setText(appointment.getUser().getUserName());
                orderPrice.setText(appointment.getAppState());
            }else{
                BeanMyOrder order = null;
                order = PetManageSystemUtil.orderController.findOrderById(id);
                orderUser.setText(order.getOrderUser().getUserName());
                orderPrice.setText(order.getOrderPrice().toString());
                orderStatus.setText(order.getOrderState());
                orderTel.setText(order.getOrderUser().getUserTel().toString());
            }
        }catch (Exception exception){
            showDialog("sorry! nothing found.");
        }
    }

    @FXML
    void loadOtherInfo(ActionEvent event){
        if(choice2.isEmpty()){
            showDialog("没有选择要查询的数据类型");
            return;
        }
        try{
            if(choice2.equals("管理员")){
                List<BeanOperator> list = PetManageSystemUtil.operatorController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanOperator b: list){
                        ones += (b.getOpName()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanOperator b = list.get(0);
                appUser.setText(b.getOpName());
                appDate.setText("");
                appPet.setText(b.getOpLevel().toString());
                appStatus.setText("");
            }else if(choice2 .equals("类别")){
                List<BeanCategory> list = PetManageSystemUtil.categoryController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanCategory b: list){
                        ones += (b.getCateName()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanCategory b = list.get(0);
                appUser.setText(b.getCateName());
                appDate.setText("");
                appPet.setText(b.getCateDetail());
                appStatus.setText("");
            }else if(choice2.equals("宠物")){
                List<BeanPet> list = PetManageSystemUtil.petController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanPet b: list){
                        ones += (b.getPetNikename()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanPet b = list.get(0);
                appUser.setText(b.getPetNikename());
                appDate.setText(b.getUser().getUserName());
                appPet.setText(b.getPetAlias());
                appStatus.setText("");
            }else if(choice2.equals("商品")){
                List<BeanProduct> list = PetManageSystemUtil.productController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanProduct b: list){
                        ones += (b.getProdName()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanProduct b = list.get(0);
                appUser.setText(b.getProdCategory().getCateName());
                appDate.setText(b.getProdPrice().toString());
                appPet.setText(b.getProdName());
                appStatus.setText(b.getProdBrand());
            }else if(choice2.equals("服务")){
                List<BeanService> list = PetManageSystemUtil.serviceController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanService b: list){
                        ones += (b.getServName()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanService b = list.get(0);
                appUser.setText(b.getServName());
                appDate.setText(b.getCategory().getCateName());
                appPet.setText(b.getServPrice().toString());
                appStatus.setText("");
            }else if(choice2.equals("用户")){
                List<BeanMyUser> list = PetManageSystemUtil.userController.search(keyword.getText());
                if(list.size()==0){
                    showDialog("啥都么找到!");
                    return;
                }
                if(list.size()>1){
                    String ones = "";
                    for (BeanMyUser b: list){
                        ones += (b.getUserName()+", ");
                    }
                    showDialog("查找到多个结果"+ones+"但仅显示最匹配部分");
                }
                BeanMyUser b = list.get(0);
                appUser.setText(b.getUserName());
                appDate.setText(b.getUserTel().toString());
                appPet.setText(b.getUserEmail());
                appStatus.setText(b.getUserContact());
            }
        }catch (Exception e){
            showDialog("Oops sth bad occur!");
            e.printStackTrace();
        }
    }


    @FXML
    void selectOrderId(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        orderDetails.clear();
        if(order == null){
            orderDetails.clear();
            orderTbl.setItems(orderDetails);
        }else {
            orderDetails = getOrderDetail(order.getOrderId());
            orderTbl.setItems(orderDetails);
        }
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
        appointmentDateCol.setCellValueFactory(new PropertyValueFactory<>("app_date"));
        appointmentPetCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
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

    private ObservableList<PieChart.Data> getOrderPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        int count1 = PetManageSystemUtil.orderController.getOrderCount("已完成");
        int count2 = PetManageSystemUtil.orderController.getOrderCount("已发货");
        int count3 = PetManageSystemUtil.orderController.getOrderCount("订单创建完成");
        data.add(new PieChart.Data("已完成订单 ( " + String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已发货订单 ( " + String.valueOf(count2) +" )",count2));
        data.add(new PieChart.Data("未发货订单 ( " + String.valueOf(count3) +" )",count3));
        return data;
    }

    private ObservableList<PieChart.Data> getAppointmentPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        int count1 = PetManageSystemUtil.appointmentController.getAppointmentCount("预约创建完成");
        int count2 = PetManageSystemUtil.appointmentController.getAppointmentCount("已完成");
        data.add(new PieChart.Data("未完成预约 ( "+String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("已完成预约 ( "+String.valueOf(count2) +" )",count2));
        return data;
    }

    private ObservableList<String> getChoice1(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("订单","预约");
        return choice;
    }

    private ObservableList<String> getChoice2(){
        ObservableList<String> choice = FXCollections.observableArrayList();
        choice.addAll("宠物","产品","类别","用户","服务","管理员");
        return choice;
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
        choiceType1.setItems(getChoice1());
        choiceType2.setItems(getChoice2());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NumberValidator numberValidator = new NumberValidator();
        try{
            Image image = new Image(new FileInputStream("src/ui/icons/error-sign.png"));
            numberValidator.setIcon(new ImageView(image));

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        numberValidator.setMessage("请输入纯数字");

        orderId.getValidators().add(numberValidator);

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

        initDrawer();

        initChart();

        initStatics();

        charContainer.getChildren().add(orderPie);
        charContainer.getChildren().add(appointmentPie);

    }

    private void initStatics() {
        adminTotal.setText("管理员总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanOperator")));
        userTotal.setText("用户总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanMyUser")));
        PetTotal.setText("宠物总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanPet")));
        orderTotal.setText("订单总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanMyOrder")));
        appointmentTotal.setText("预约总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanAppointment")));
        serviceTotal.setText("服务总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanService")));
        productTotal.setText("产品总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanProduct")));
        categoryTotal.setText("分类总数: "+ String.valueOf(PetManageSystemUtil.getCount("BeanCategory")));
    }

    private void initChart() {
       orderPie = new PieChart(getOrderPieData());
       orderPie.autosize();
       appointmentPie = new PieChart(getAppointmentPieData());
       appointmentPie.autosize();
    }

    private void initDrawer() {
        try {
            VBox toolbar = FXMLLoader.load(getClass().getResource("/ui/main/toolbar/toolbar.fxml"));
            drawer.setSidePane(toolbar);
            drawer.setDefaultDrawerSize(175);
            HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
            task.setRate(-1);
            hamburger.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (Event event) -> {
                task.setRate(task.getRate() * -1);
                task.play();
                if(drawer.isClosed()){
                    drawer.open();
                    drawer.setMinWidth(175);
                }else{
                    drawer.close();
                    drawer.setMinWidth(0);
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}

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
    private JFXTabPane tabPane;

    @FXML
    private Tab homeTab;

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

    @FXML
    void loadAppInfo(ActionEvent event) {
        try{
            int id = Integer.parseInt(appId.getText());
            System.out.println(1);
            BeanAppointment appointment = null;
            appointment = PetManageSystemUtil.appointmentController.findAppointmentById(id);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            appUser.setText("");
            appDate.setText("");
            appStatus.setText("�����Ŵ���");
            appPet.setText("");
        }
    }

    @FXML
    void showProductBarcode(ActionEvent event) {
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/barcode/barcode.fxml"));
            Parent parent = loader.load();
            Barcode barcode = loader.getController();
            barcode.inflateUI(product.getProdBarcode());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("������");
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
            showDialog("��ѡ��Ҫ�����Ĺ���Ա");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try{
                PetManageSystemUtil.operatorController.delOperator(operator.getOpId());
                operators.remove(operator);
                showDialog("����Ա"+operator.getOpName()+"��ɾ��");
            }catch (BaseException e1){
                showDialog(e1.getMessage());
            }
        });
        showConfirmDialog("�Ƿ�Ҫɾ������Ա"+operator.getOpName()+" ?", Arrays.asList(btnCancel,btnOK));
    }

    @FXML
    void deleteUser (ActionEvent event){
        BeanMyUser user = userTbl.getSelectionModel().getSelectedItem();
        if(user == null){
            showDialog("��ѡ��Ҫ�������û�");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.userController.delUser(user.getUserId());
            } catch (Exception exception1) {
                showDialog("���û�Ŀǰ���ڻ�Ծ״̬,����ɾ��");
            }
            users.remove(user);
            showDialog("�û�"+user.getUserName()+"��ɾ��");
        });
       showConfirmDialog("�Ƿ�Ҫɾ���û�"+user.getUserName()+" ?",Arrays.asList(btnCancel, btnOK));

    }

    @FXML
    void deletePet(ActionEvent event){
        BeanPet pet = petTbl.getSelectionModel().getSelectedItem();
        if(pet == null){
            showDialog("��ѡ��Ҫ�����ĳ���");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.petController.delPet(pet.getPetId());
            } catch (Exception exception1) {
                showDialog("�ó���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                return;
            }
            pets.remove(pet);
            showDialog("����"+pet.getPetNikename()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ������"+pet.getPetNikename()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteProduct(ActionEvent event){
        BeanProduct product = productTbl.getSelectionModel().getSelectedItem();
        if(product == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.productController.delProduct(product.getProdId());
            } catch (Exception exception1) {
                showDialog("�ò�ƷĿǰ���ڻ�Ծ״̬,����ɾ��");
                return;
            }
            products.remove(product);
            showDialog("��Ʒ"+product.getProdName()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ����Ʒ"+product.getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteCategory(ActionEvent event){
        BeanCategory category = categoryTbl.getSelectionModel().getSelectedItem();
        if(category == null){
            showDialog("��ѡ��Ҫɾ���ķ���");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.categoryController.delCategory(category.getCateId());
            } catch (Exception exception) {
                showDialog("�÷���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                return;
            }
            categories.remove(category);
            showDialog("����"+category.getCateName()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ������"+category.getCateName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteService(ActionEvent event){
        BeanService service = serviceTbl.getSelectionModel().getSelectedItem();
        if(service == null){
            showDialog("��ѡ��Ҫ�����ķ���");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.serviceController.delService(service.getServId());
            } catch (Exception exception) {
                showDialog("�÷���Ŀǰ���ڻ�Ծ״̬,����ɾ��");
                return;
            }
            services.remove(service);
            showDialog("����"+service.getServName()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ������"+service.getServName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteOrderProduct(ActionEvent event){
        BeanOrderDetail detail = orderTbl.getSelectionModel().getSelectedItem();
        if(detail == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.orderController.delOrderDetail(detail);
            } catch (Exception exception) {
                showDialog("�ò�ƷĿǰ���ڻ�Ծ״̬,����ɾ��");
                return;
            }
            orderDetails.remove(detail);

            List<BeanAppointment> list = PetManageSystemUtil.appointmentController.loadAll();
            for(BeanAppointment a1 : list){
                List<BeanAppointmentDetail> details = PetManageSystemUtil.appointmentController.loadDetailByAppointmentId(a1.getAppId());
                if(details.size() == 0)
                    PetManageSystemUtil.appointmentController.delAppointment(a1.getAppId());
            }
            showDialog("��Ʒ"+detail.getProduct().getProdName()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ����Ʒ"+detail.getProduct().getProdName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            PetManageSystemUtil.orderController.delOrder(order.getOrderId());
            showDialog("����"+order.getOrderId()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ������"+order.getOrderId()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteAppointmentService(ActionEvent event){
        BeanAppointmentDetail detail = appointmentTbl.getSelectionModel().getSelectedItem();
        if(detail == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            try {
                PetManageSystemUtil.appointmentController.delAppointmentDetail(detail);
            } catch (Exception exception) {
                showDialog("��ԤԼĿǰ���ڻ�Ծ״̬,����ɾ��");
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

            showDialog("ԤԼ"+detail.getService().getServName()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ��ԤԼ"+detail.getService().getServName()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void deleteAppointment(ActionEvent event){
        BeanAppointment appointment = appointmentBox.getSelectionModel().getSelectedItem();
        if(appointment == null){
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        JFXButton btnOK = new JFXButton("ȥ���Ѿ�");
        JFXButton btnCancel = new JFXButton("������");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ɾ��");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            PetManageSystemUtil.appointmentController.delAppointment(appointment.getAppId());
            showDialog("ԤԼ"+appointment.getAppId()+"��ɾ��");
        });
        showConfirmDialog("�Ƿ�Ҫɾ��ԤԼ"+appointment.getAppId()+" ?", Arrays.asList(btnCancel, btnOK));
    }

    /*
    *  Edit operation
    * */

    @FXML
    void editOperator(ActionEvent event){
        BeanOperator beanOperator = operatorTbl.getSelectionModel().getSelectedItem();
        if(beanOperator == null){
            showDialog("��ѡ��Ҫ�����Ĺ���Ա");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOperator/addOperator.fxml"));
            Parent parent = loader.load();
            AddOperator addOperator = (AddOperator) loader.getController();
            addOperator.inflateUI(beanOperator);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭����Ա");
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
            showDialog("��ѡ��Ҫ�����ĳ���");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addPet/addPet.fxml"));
            Parent parent = loader.load();
            AddPet addPet = (AddPet) loader.getController();
            addPet.inflateUI(beanPet);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭����");
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
            showDialog("��ѡ��Ҫ�������û�");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addUser/addUser.fxml"));
            Parent parent = loader.load();
            AddUser addUser = (AddUser) loader.getController();
            addUser.inflateUI(beanUser);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭�û�");
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
            showDialog("��ѡ��Ҫ�����ķ���");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addService/addService.fxml"));
            Parent parent = loader.load();
            AddService addService = (AddService) loader.getController();
            addService.inflateUI(service);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭����");
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
            showDialog("��ѡ��Ҫ�����Ĳ�Ʒ");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addProduct/addProduct.fxml"));
            Parent parent = loader.load();
            AddProduct addProduct = (AddProduct) loader.getController();
            addProduct.inflateUI(product);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭��Ʒ");
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
            showDialog("��ѡ��Ҫ�����ķ���");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addCategory/addCategory.fxml"));
            Parent parent = loader.load();
            AddCategory addCategory = (AddCategory) loader.getController();
            addCategory.inflateUI(category);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image("/ui/icons/icon.png"));
            stage.setTitle("�༭����");
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
            showDialog("��ѡ��Ҫ�����Ķ���");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addOrder/addOrder.fxml"));
            Parent parent = loader.load();
            AddOrder addOrder = (AddOrder) loader.getController();
            addOrder.inflateUI(order);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("�༭����");
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
            showDialog("��ѡ��Ҫ������ԤԼ");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/add/addAppointment/addAppointment.fxml"));
            Parent parent = loader.load();
            AddAppointment addAppointment = (AddAppointment) loader.getController();
            addAppointment.inflateUI(appointment);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("�༭ԤԼ");
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
            showDialog("��ѡ��Ҫ�����Ķ���");
            return;
        }
        JFXButton btnOK = new JFXButton("��");
        JFXButton btnCancel = new JFXButton("��");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("����");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderState("�ѷ���");
            PetManageSystemUtil.update(order);
            showDialog("����"+order.getOrderId()+"�ѷ���");
        });
        showConfirmDialog("�Ƿ��ѽ�����"+order.getOrderId()+"����ȫ������ ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishOrder(ActionEvent event){
        BeanMyOrder order = orderBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("��ѡ��Ҫ�����Ķ���");
            return;
        }
        JFXButton btnOK = new JFXButton("��");
        JFXButton btnCancel = new JFXButton("��");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ȷ���ջ�");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setOrderState("�����");
            PetManageSystemUtil.update(order);
            showDialog("����"+order.getOrderId()+"���ջ�");
        });
        showConfirmDialog("����"+order.getOrderId()+"����ȫ��ȷ���ջ� ?", Arrays.asList(btnCancel, btnOK));
    }

    @FXML
    void finishAppointment(ActionEvent event){
        BeanAppointment order = appointmentBox.getSelectionModel().getSelectedItem();
        if(order == null){
            showDialog("��ѡ��Ҫ������ԤԼ");
            return;
        }
        JFXButton btnOK = new JFXButton("��");
        JFXButton btnCancel = new JFXButton("��");
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            showCancelDialog("ԤԼ���");
        });
        btnOK.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            order.setAppState("�����");
            PetManageSystemUtil.update(order);
            showDialog("ԤԼ"+order.getAppId()+"�����");
        });
        showConfirmDialog("ԤԼ"+order.getAppId()+"����ȫ����� ?", Arrays.asList(btnCancel, btnOK));
    }

    private void showDialog(String message){
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("��֪��");
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
        JFXButton button = new JFXButton("��֪��");
        JFXDialog dialog = new JFXDialog(rootPane, dialogLayout,JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event e)->{
            rootAnchorPane.setEffect(null);
            dialog.close();
        });
        dialogLayout.setHeading(new Label("ȡ��"+cate+"����"));
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
    void loadOrderInfo(ActionEvent event) {
        try{
            int id = Integer.parseInt(orderId.getText());
            BeanMyOrder order = null;
            order = PetManageSystemUtil.orderController.findOrderById(id);
            int userId = order.getOrderUser().getUserId();
            String status = order.getOrderState();
            int price = order.getOrderPrice();
            BeanMyUser user = PetManageSystemUtil.userController.findUserById(userId);
            String tel = user.getUserTel().toString();
            orderUser.setText(user.getUserName());
            orderPrice.setText(String.valueOf(price));
            orderStatus.setText(status);
            orderTel.setText(tel);
        }catch (Exception exception){
            orderUser.setText("");
            orderPrice.setText("");
            orderStatus.setText("�����Ŵ���");
            orderTel.setText("");
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
        int count1 = PetManageSystemUtil.orderController.getOrderCount("�����");
        int count2 = PetManageSystemUtil.orderController.getOrderCount("�ѷ���");
        int count3 = PetManageSystemUtil.orderController.getOrderCount("�����������");
        data.add(new PieChart.Data("����ɶ��� ( " + String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("�ѷ������� ( " + String.valueOf(count2) +" )",count2));
        data.add(new PieChart.Data("δ�������� ( " + String.valueOf(count3) +" )",count3));
        return data;
    }

    private ObservableList<PieChart.Data> getAppointmentPieData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        int count1 = PetManageSystemUtil.appointmentController.getAppointmentCount("ԤԼ�������");
        int count2 = PetManageSystemUtil.appointmentController.getAppointmentCount("�����");
        data.add(new PieChart.Data("δ��ɶ��� ( "+String.valueOf(count1) +" )",count1));
        data.add(new PieChart.Data("����ɶ��� ( "+String.valueOf(count2) +" )",count2));
        return data;
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

        }catch (Exception exception){
            System.out.println(exception.getMessage());
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

        loadData();

        initCol();

        initDrawer();

        initChart();

        initStatics();

        charContainer.getChildren().add(orderPie);
        charContainer.getChildren().add(appointmentPie);

    }

    private void initStatics() {
        adminTotal.setText("����Ա����: "+ String.valueOf(PetManageSystemUtil.getCount("BeanOperator")));
        userTotal.setText("�û�����: "+ String.valueOf(PetManageSystemUtil.getCount("BeanMyUser")));
        PetTotal.setText("��������: "+ String.valueOf(PetManageSystemUtil.getCount("BeanPet")));
        orderTotal.setText("��������: "+ String.valueOf(PetManageSystemUtil.getCount("BeanMyOrder")));
        appointmentTotal.setText("ԤԼ����: "+ String.valueOf(PetManageSystemUtil.getCount("BeanAppointment")));
        serviceTotal.setText("��������: "+ String.valueOf(PetManageSystemUtil.getCount("BeanService")));
        productTotal.setText("��Ʒ����: "+ String.valueOf(PetManageSystemUtil.getCount("BeanProduct")));
        categoryTotal.setText("��������: "+ String.valueOf(PetManageSystemUtil.getCount("BeanCategory")));
    }

    private void initChart() {
       orderPie = new PieChart(getOrderPieData());
       appointmentPie = new PieChart(getAppointmentPieData());
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

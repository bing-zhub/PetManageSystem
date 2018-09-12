package ui.add.addAppointment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import util.PetManageSystemUtil;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXComboBox<BeanPet> petBox1;

    @FXML
    private JFXComboBox<BeanPet> petBox2;

    @FXML
    private JFXComboBox<BeanPet> petBox3;

    @FXML
    private JFXComboBox<BeanPet> petBox4;

    @FXML
    private JFXComboBox<BeanService> serviceBox1;

    @FXML
    private JFXDatePicker appointmentDate1;

    @FXML
    private Label servicePrice1;

    @FXML
    private JFXComboBox<BeanService> serviceBox2;

    @FXML
    private JFXDatePicker appointmentDate2;

    @FXML
    private Label servicePrice2;

    @FXML
    private JFXComboBox<BeanService> serviceBox3;

    @FXML
    private JFXDatePicker appointmentDate3;

    @FXML
    private Label servicePrice3;

    @FXML
    private JFXComboBox<BeanService> serviceBox4;

    @FXML
    private JFXDatePicker appointmentDate4;

    @FXML
    private Label servicePrice4;

    @FXML
    private Label appointmentTotalPrice;

    @FXML
    private JFXButton btnOk;

    @FXML
    private JFXComboBox<BeanMyUser> userBox;

    ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
    ObservableList<BeanService> services = FXCollections.observableArrayList();
    ObservableList<BeanPet> pets = FXCollections.observableArrayList();
    private BeanService service1 = null;
    private BeanService service2 = null;
    private BeanService service3 = null;
    private BeanService service4 = null;
    private LocalDate date1 = null;
    private LocalDate date2 = null;
    private LocalDate date3 = null;
    private LocalDate date4 = null;
    private BeanPet pet1 = null;
    private BeanPet pet2 = null;
    private BeanPet pet3 = null;
    private BeanPet pet4 = null;
    private BeanMyUser user = null;
    private BeanAppointment appointment = null;
    private boolean isEditMode = false;
    private int detailId1 = 0;
    private int detailId2 = 0;
    private int detailId3 = 0;
    private int detailId4 = 0;
    private int price1 = 0;
    private int price2 = 0;
    private int price3 = 0;
    private int price4 = 0;


    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = PetManageSystemUtil.userController.loadAll();
        for (BeanMyUser e: list){
            users.add(e);
        }
        return users;
    }

    private ObservableList<BeanService> getService(){
        ObservableList<BeanService> services = FXCollections.observableArrayList();
        List<BeanService> list = PetManageSystemUtil.serviceController.loadAll();
        services.addAll(list);
        return services;
    }

    private ObservableList<BeanPet> getPet(BeanMyUser user){
        ObservableList<BeanPet> pets = FXCollections.observableArrayList();
        List<BeanPet> list = PetManageSystemUtil.petController.loadAll(user.getUserId());
        pets.addAll(list);
        return pets;
    }

    private ObservableList<BeanPet> getPet(){
        ObservableList<BeanPet> pets = FXCollections.observableArrayList();
        List<BeanPet> list = PetManageSystemUtil.petController.loadAll();
        pets.addAll(list);
        return pets;
    }

    @FXML
    void appointmentDate1Selected(ActionEvent event) {
        date1 = appointmentDate1.getValue();
    }

    @FXML
    void appointmentDate2Selected(ActionEvent event) {
        date2 = appointmentDate2.getValue();
    }

    @FXML
    void appointmentDate3Selected(ActionEvent event) {
        date3 = appointmentDate3.getValue();
    }

    @FXML
    void appointmentDate4Selected(ActionEvent event) {
        date4 = appointmentDate4.getValue();
    }

    @FXML
    void service1Selected(ActionEvent event) {
        service1 = serviceBox1.getSelectionModel().getSelectedItem();
        servicePrice1.setText(service1.getServPrice().toString()+"元");
        this.price1 = service1.getServPrice();
        appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
    }

    @FXML
    void service2Selected(ActionEvent event) {
        service2 = serviceBox2.getSelectionModel().getSelectedItem();
        if(service2!=null){
            this.price2 = service2.getServPrice();
            servicePrice2.setText(service2.getServPrice().toString()+"元");
        }

        appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
    }

    @FXML
    void service3Selected(ActionEvent event) {
        service3 = serviceBox3.getSelectionModel().getSelectedItem();
        if(service3!=null){
            this.price3 = service3.getServPrice();
            servicePrice3.setText(service3.getServPrice().toString()+"元");
        }
        appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");

    }

    @FXML
    void service4Selected(ActionEvent event) {
        service4 = serviceBox4.getSelectionModel().getSelectedItem();
        if(service4!=null){
            this.price4 = service4.getServPrice();
            servicePrice4.setText(service4.getServPrice().toString()+"元");
        }
        appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");

    }

    @FXML
    void petBox1Selected(ActionEvent event){
        pet1 = petBox1.getSelectionModel().getSelectedItem();
    }

    @FXML
    void petBox2Selected(ActionEvent event){
        if(petBox2.getSelectionModel().getSelectedItem()!=null)
            pet2 = petBox2.getSelectionModel().getSelectedItem();
    }

    @FXML
    void petBox3Selected(ActionEvent event){
        if(petBox3.getSelectionModel().getSelectedItem()!=null)
            pet3 = petBox3.getSelectionModel().getSelectedItem();
    }

    @FXML
    void petBox4Selected(ActionEvent event){
        if(petBox4.getSelectionModel().getSelectedItem()!=null)
            pet4 = petBox4.getSelectionModel().getSelectedItem();
    }

    @FXML
    void userSelected(ActionEvent event){
        this.user = userBox.getSelectionModel().getSelectedItem();
        pets.clear();
        pets = getPet(user);
        petBox1.setItems(pets);
        petBox2.setItems(pets);
        petBox3.setItems(pets);
        petBox4.setItems(pets);
    }

    @FXML
    void appointmentAdd(ActionEvent event) {
        if(user == null || service1 == null || appointmentDate1 == null || pet1 == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("缺少信息");
            alert.showAndWait();
            return;
        }
        service1Selected(new ActionEvent());
        appointmentDate1Selected(new ActionEvent());
        service2Selected(new ActionEvent());
        appointmentDate2Selected(new ActionEvent());
        service3Selected(new ActionEvent());
        appointmentDate3Selected(new ActionEvent());
        service4Selected(new ActionEvent());
        appointmentDate4Selected(new ActionEvent());



        if(!isEditMode)
            appointment = new BeanAppointment();

        appointment.setAppState("预约创建完成");
        appointment.setUser(user);
        PetManageSystemUtil.save(appointment);

        if(service1 != null && date1 != null){
            BeanAppointmentDetail detail = new BeanAppointmentDetail();
            if(!isEditMode){
                if(date1.isBefore(LocalDate.now())){
                    beforeAlert();
                }else{
                    detail.setApp_date(Date.valueOf(date1));
                }
            }else{
                detail.setApp_date(Date.valueOf(date1));
            }
            detail.setAppointment(appointment);
            detail.setPet(pet1);
            System.out.println(pet1);
            detail.setService(service1);
            if(isEditMode){
                detail.setDetailId(detailId1);
                PetManageSystemUtil.update(detail);
            }else{
                PetManageSystemUtil.save(detail);
            }
        }

        if(service2 != null && date2 != null){
            BeanAppointmentDetail detail = new BeanAppointmentDetail();
            if(!isEditMode){
                if(date2.isBefore(LocalDate.now())){
                    beforeAlert();
                }else{
                    detail.setApp_date(Date.valueOf(date2));
                }
            }else{
                detail.setApp_date(Date.valueOf(date2));
            }
            detail.setAppointment(appointment);
            detail.setPet(pet2);
            detail.setService(service2);
            if(isEditMode){
                detail.setDetailId(detailId2);
                PetManageSystemUtil.update(detail);
            }else{
                PetManageSystemUtil.save(detail);
            }
        }

        if(service3 != null && date3 != null){
            BeanAppointmentDetail detail = new BeanAppointmentDetail();
            if(!isEditMode){
                if(date3.isBefore(LocalDate.now())){
                    beforeAlert();
                }else{
                    detail.setApp_date(Date.valueOf(date3));
                }
            }else{
                detail.setApp_date(Date.valueOf(date3));
            }
            detail.setAppointment(appointment);
            detail.setPet(pet3);
            detail.setService(service3);
            if(isEditMode){
                detail.setDetailId(detailId3);
                PetManageSystemUtil.update(detail);
            }else{
                PetManageSystemUtil.save(detail);
            }
        }

        if(service4 != null && date4 != null){
            BeanAppointmentDetail detail = new BeanAppointmentDetail();
            if(!isEditMode){
                if(date4.isBefore(LocalDate.now())){
                    beforeAlert();
                }else{
                    detail.setApp_date(Date.valueOf(date4));
                }
            }else{
                detail.setApp_date(Date.valueOf(date4));
            }
            detail.setAppointment(appointment);
            detail.setPet(pet4);
            detail.setService(service4);
            if(isEditMode){
                detail.setDetailId(detailId4);
                PetManageSystemUtil.update(detail);
            }else{
                PetManageSystemUtil.save(detail);
            }
        }

        cancel(new ActionEvent());
        return;

    }

    private void beforeAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("预约时间不能早于当前时间");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.users = getUser();
        userBox.setItems(users);
        this.services = getService();
        this.pets = getPet();
        serviceBox1.setItems(services);
        serviceBox2.setItems(services);
        serviceBox3.setItems(services);
        serviceBox4.setItems(services);
        petBox1.setItems(pets);
        petBox2.setItems(pets);
        petBox3.setItems(pets);
        petBox4.setItems(pets);
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage("此为必填项");

        serviceBox1.getValidators().add(requiredFieldValidator);
        appointmentDate1.getValidators().add(requiredFieldValidator);
        petBox1.getValidators().add(requiredFieldValidator);
        userBox.getValidators().add(requiredFieldValidator);

        serviceBox1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    serviceBox1.validate();
            }
        });

        appointmentDate1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    appointmentDate1.validate();
            }
        });

        petBox1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    appointmentDate1.validate();
            }
        });

        userBox.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                    appointmentDate1.validate();
            }
        });

    }

    private LocalDate convertToLocalDateViaInstant(java.util.Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public void inflateUI(BeanAppointment appointment) {
        List<BeanAppointmentDetail> details = PetManageSystemUtil.appointmentController.loadDetailByAppointmentId(appointment.getAppId());
        userBox.setValue(appointment.getUser());
        int size = details.size();
        this.isEditMode = true;
        if(size == 1){
            service1 = details.get(0).getService();
            serviceBox1.setValue(service1);
            date1 =  convertToLocalDateViaInstant(details.get(0).getApp_date());
            appointmentDate1.setValue(date1);
            petBox1.setValue(details.get(0).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+"元"));
            detailId1 = details.get(0).getDetailId();
        }else if(size == 2) {
            service1 = details.get(0).getService();
            serviceBox1.setValue(service1);
            date1 =  convertToLocalDateViaInstant(details.get(0).getApp_date());
            appointmentDate1.setValue(date1);
            petBox1.setValue(details.get(0).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+service2.getServPrice())+"元");
            detailId1 = details.get(0).getDetailId();

            service2 = details.get(1).getService();
            serviceBox2.setValue(service2);
            date2 =  convertToLocalDateViaInstant(details.get(1).getApp_date());
            appointmentDate2.setValue(date2);
            petBox2.setValue(details.get(1).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+service2.getServPrice()+service3.getServPrice())+"元");
            detailId2 = details.get(1).getDetailId();
        }else if(size == 3){
            service1 = details.get(0).getService();
            serviceBox1.setValue(service1);
            date1 =  convertToLocalDateViaInstant(details.get(0).getApp_date());
            appointmentDate1.setValue(date1);
            petBox1.setValue(details.get(0).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId1 = details.get(0).getDetailId();

            service2 = details.get(1).getService();
            serviceBox2.setValue(service2);
            date2 =  convertToLocalDateViaInstant(details.get(1).getApp_date());
            appointmentDate2.setValue(date2);
            petBox2.setValue(details.get(1).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId2 = details.get(1).getDetailId();

            service3 = details.get(2).getService();
            serviceBox3.setValue(service3);
            date3 =  convertToLocalDateViaInstant(details.get(2).getApp_date());
            appointmentDate3.setValue(date3);
            appointmentDate1.setValue(date3);
            petBox3.setValue(details.get(2).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId3 = details.get(2).getDetailId();

        }else if(size == 4){
            service1 = details.get(0).getService();
            serviceBox1.setValue(service1);
            date1 =  convertToLocalDateViaInstant(details.get(0).getApp_date());
            appointmentDate1.setValue(date1);
            petBox1.setValue(details.get(0).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId1 = details.get(0).getDetailId();

            service2 = details.get(1).getService();
            serviceBox2.setValue(service2);
            date2 =  convertToLocalDateViaInstant(details.get(1).getApp_date());
            appointmentDate2.setValue(date2);
            petBox2.setValue(details.get(1).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId2 = details.get(1).getDetailId();

            service3 = details.get(2).getService();
            serviceBox3.setValue(service3);
            date3 =  convertToLocalDateViaInstant(details.get(2).getApp_date());
            appointmentDate3.setValue(date3);
            petBox3.setValue(details.get(2).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId3 = details.get(2).getDetailId();

            service4 = details.get(3).getService();
            serviceBox4.setValue(service4);
            date4 =  convertToLocalDateViaInstant(details.get(3).getApp_date());
            appointmentDate4.setValue(date4);
            petBox4.setValue(details.get(3).getPet());
            appointmentTotalPrice.setText("总价:"+String.valueOf(price1+price2+price3+price4)+"元");
            detailId4 = details.get(3).getDetailId();
        }

        this.appointment = appointment;
        this.price1 = service1==null?0:service1.getServPrice();
        this.price2 = service2==null?0:service2.getServPrice();
        this.price3 = service3==null?0:service3.getServPrice();
        this.price4 = service4==null?0:service4.getServPrice();
    }
}

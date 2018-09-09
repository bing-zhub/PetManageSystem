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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import util.PetManageSystemUtil;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
    private Label orderTotalPrice;

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
        servicePrice1.setText(service1.getServPrice().toString());
    }

    @FXML
    void service2Selected(ActionEvent event) {
        service2 = serviceBox2.getSelectionModel().getSelectedItem();
        if(service2!=null)
            servicePrice2.setText(service2.getServPrice().toString());
    }

    @FXML
    void service3Selected(ActionEvent event) {
        service3 = serviceBox3.getSelectionModel().getSelectedItem();
        if(service3!=null)
            servicePrice3.setText(service3.getServPrice().toString());
    }

    @FXML
    void service4Selected(ActionEvent event) {
        service4 = serviceBox4.getSelectionModel().getSelectedItem();
        if(service4!=null)
            servicePrice4.setText(service4.getServPrice().toString());
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
    void orderAdd(ActionEvent event) {
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
            detail.setApp_date(Date.valueOf(date1));
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
            detail.setApp_date(Date.valueOf(date2));
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
            detail.setApp_date(Date.valueOf(date3));
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
            detail.setApp_date(Date.valueOf(date4));
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

    }
}

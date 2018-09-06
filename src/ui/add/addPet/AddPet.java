package ui.add.addPet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import model.BeanMyUser;
import model.BeanOperator;
import model.BeanPet;
import org.hibernate.Hibernate;
import util.PetManageSystemUtil;

import java.net.URL;
import java.sql.Blob;
import java.util.List;
import java.util.ResourceBundle;

public class AddPet implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXComboBox<BeanMyUser> petOwner;

    @FXML
    private JFXTextField petNikename;

    @FXML
    private JFXTextField petAlias;

    @FXML
    private JFXButton btnOk;

    private boolean isEditMode = false;
    private int petId = 0;

    private ObservableList<BeanMyUser> getUser(){
        ObservableList<BeanMyUser> users = FXCollections.observableArrayList();
        List<BeanMyUser> list = PetManageSystemUtil.userController.loadAll();
        users.addAll(list);
        return users;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void petAdd(ActionEvent event) {
        String nikename = petNikename.getText();
        String alias = petAlias.getText();
        BeanMyUser owner = petOwner.getSelectionModel().getSelectedItem();

        if(nikename.isEmpty() || owner==null || alias.isEmpty()){
            return;
        }

        BeanPet pet = new BeanPet();
        String contentText = "";
        pet.setPetAlias(alias);

        pet.setPetNikename(nikename);
        pet.setPetOwner(owner.getUserId());
        pet.setPetImg(null);

        if(isEditMode){
            btnOk.setText("修改");
            pet.setPetId(petId);
            PetManageSystemUtil.update(pet);
            contentText = "修改成功";
        }else{
            btnOk.setText("添加");
            PetManageSystemUtil.save(pet);
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
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("此为必填项");
        petOwner.getValidators().add(validator);
        petNikename.getValidators().add(validator);
        petAlias.getValidators().add(validator);

        petOwner.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    petOwner.validate();
                }
            }
        });


        petNikename.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    petNikename.validate();
                }
            }
        });

        petAlias.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    petAlias.validate();
                }
            }
        });

        petOwner.setItems(getUser());
    }

    public void inflateUI(BeanPet pet){
        petAlias.setText(pet.getPetAlias());
        petNikename.setText(pet.getPetNikename());
        this.isEditMode = true;
        this.petId = pet.getPetId();
    }
}

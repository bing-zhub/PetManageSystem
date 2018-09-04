package view.md;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import util.BaseException;
import util.PetManageSystemUtil;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton register;

    @FXML
    private JFXPasswordField password;

    @FXML
    void userLogin(ActionEvent event) {
        String userName = username.getText();
        String passWord = password.getText();
        try{
            PetManageSystemUtil.operatorController.login(userName, passWord);
            JFXSnackbar snackbar = new JFXSnackbar(rootPane);

            snackbar.show("登录成功",1000);
        }catch (BaseException e){
            JFXSnackbar snackbar = new JFXSnackbar(rootPane);
            snackbar.show(e.getMessage(),1000);
        }
    }

    @FXML
    void userRegister(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        RequiredFieldValidator validator = new RequiredFieldValidator();
//        NumberValidator numberValidator = new NumberValidator();

        username.getValidators().add(validator);
//        username.getValidators().add(numberValidator);
        validator.setMessage("未输入用户名");
//        numberValidator.setMessage("请输入纯数字");

        username.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    username.validate();
                }
            }
        });

        password.getValidators().add(validator);
        validator.setMessage("请输入密码");

        password.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    password.validate();
                }
            }
        });

        try{
            Image image = new Image(new FileInputStream("img/error-sign.png"));
            validator.setIcon(new ImageView(image));

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

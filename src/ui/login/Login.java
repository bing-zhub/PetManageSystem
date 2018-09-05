package ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import util.BaseException;
import util.PetManageSystemUtil;

import java.io.IOException;

public class Login {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label titleBar;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton login;

    @FXML
    private JFXPasswordField password;

    @FXML
    void quit(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void userLogin(ActionEvent event) {
        titleBar.setText("宠物服务管理系统");
        titleBar.setStyle("-fx-background-color: #000000");

        String userName = username.getText();
        String passWord = DigestUtils.sha1Hex(password.getText());
        try {
            PetManageSystemUtil.operatorController.login(userName, passWord);
            titleBar.setStyle("-fx-text-fill: white");
            loadMain();
            ((Stage)username.getScene().getWindow()).close();
        } catch (BaseException e) {
            titleBar.setText("账号或密码输入错误");
            titleBar.setStyle("-fx-background-color: #d32f2f");
        }
    }

    void loadMain(){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("宠物服务管理系统");
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnCloseRequest(e->System.exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

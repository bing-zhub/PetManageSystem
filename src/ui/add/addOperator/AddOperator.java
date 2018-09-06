package ui.add.addOperator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanOperator;
import util.BaseException;
import util.PetManageSystemUtil;

public class AddOperator {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmPassword;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton cancel;

    private Boolean isEditMode = false;
    private int operatorId = 0;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void userRegister(ActionEvent event) {
        String tUserName = username.getText();
        String tPassword = password.getText();
        String tConfirmPassword = confirmPassword.getText();

        if(tConfirmPassword.isEmpty() || tPassword.isEmpty() || tUserName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("请输入所有内容");
            alert.showAndWait();
            return;
        }

        if(!tConfirmPassword.equals(tPassword)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("密码输入不一致");
            alert.showAndWait();
            return;
        }
        BeanOperator operator = new BeanOperator();
        operator.setOpLevel(1);
        operator.setOpName(tUserName);
        operator.setOpPwd(tPassword);
        String contentText = "";
        try {
            if(isEditMode){
                operator.setOpId(operatorId);
                PetManageSystemUtil.update(operator);
                contentText = "修改成功";
            } else {
                PetManageSystemUtil.operatorController.addOperator(operator);
                contentText = "添加成功";
            }
        } catch (BaseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.show();
        cancel(new ActionEvent());
        return;
    }

    public void inflateUI(BeanOperator operator){
        username.setText(operator.getOpName());
        password.setText(operator.getOpPwd());
        confirmPassword.setText(operator.getOpPwd());
        this.isEditMode = true;
        this.operatorId = operator.getOpId();
    }

}

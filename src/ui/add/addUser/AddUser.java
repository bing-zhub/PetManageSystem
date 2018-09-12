package ui.add.addUser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanMyUser;
import util.BaseException;
import util.PetManageSystemUtil;

public class AddUser {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXTextField tel;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnCancel;

    private Boolean isEditMode = false;
    private int userId = 0;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void userAdd(ActionEvent event) {
        String tUsername = username.getText();
        String tTel = tel.getText();
        String tEmail = email.getText();
        String rAddress = address.getText();
        BeanMyUser user = new BeanMyUser();
        user.setUserEmail(tEmail);
        user.setUserContact(rAddress);
        user.setUserTel(tTel);
        user.setUserName(tUsername);
        String contentText = "";
        try{
            if(isEditMode){
                user.setUserId(userId);
                PetManageSystemUtil.update(user);
                contentText = "修改成功";
            }else{
                PetManageSystemUtil.userController.addUser(user);
                contentText = "添加成功";
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(contentText);
            alert.showAndWait();
        }catch (BaseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        cancel(new ActionEvent());
    }

    public void inflateUI(BeanMyUser user){
        username.setText(user.getUserName());
        tel.setText(user.getUserTel().toString());
        email.setText(user.getUserEmail());
        address.setText(user.getUserContact());
        isEditMode = true;
        this.userId = user.getUserId();
    }

}

package ui.add.addCategory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.BeanCategory;
import util.BaseException;
import util.PetManageSystemUtil;

public class AddCategory {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXTextField categoryName;

    @FXML
    private JFXTextField categoryDetail;

    private Boolean isEditMode = false;
    private int categoryId = 0;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void categoryAdd(ActionEvent event) {
        String name = categoryName.getText();
        String detail = categoryDetail.getText();
        BeanCategory category = new BeanCategory();
        category.setCateName(name);
        category.setCateDetail(detail);
        String contentText = "";

        if(isEditMode){
            category.setCateId(categoryId);
            PetManageSystemUtil.update(category);
            contentText = "修改成功";
        }else{
            PetManageSystemUtil.save(category);
            contentText = "添加成功";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();

        cancel(new ActionEvent());
    }
    public void inflateUI( BeanCategory category){
        categoryName.setText(category.getCateName());
        categoryDetail.setText(category.getCateDetail());
        isEditMode = true;
        this.categoryId = category.getCateId();
    }


}

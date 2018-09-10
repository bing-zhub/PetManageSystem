package ui.add.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Barcode implements Initializable{

    @FXML
    private ImageView barcodeView;

    public URL encode(String contents, int width, int height, String location) {
        URL url = getClass().getResource(location);
        File file = new File(url.getPath());
        int codeWidth = 3 + (7 * 6) + 5 + (7 * 6) + 3;
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height, null);
            MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        barcodeView.setImage(new Image("/ui/add/barcode/tmp.png"));
    }

    public void inflateUI(String content){
        if(content.length()<13){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("条形码长度应为13");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        Barcode barcode = new Barcode();
        barcode.encode(content,300,60,"/ui/add/barcode/tmp.png");
        barcodeView.setImage(new Image("/ui/add/barcode/tmp.png"));
    }
}

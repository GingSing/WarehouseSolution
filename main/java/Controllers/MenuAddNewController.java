package main.java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.ProductSQL;

public class MenuAddNewController{

    @FXML
    private AnchorPane addNewAnchorPane;

    @FXML
    private TextField barcode;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField amount;

    @FXML
    void submitInfo(MouseEvent event) {

        int inputBarcode = -1;
        int inputAmount = -1;
        int inputPrice = -1;
        String errorString = "Invalid input for:";

        try{
            if(barcode.getText().trim().equals(""))
                throw new Exception("barcode Exception");
            inputBarcode = Integer.parseInt(barcode.getText().trim());
        }catch(Exception e){
            errorString +="\n- barcode";
        }

        try{
            if(amount.getText().trim().equals(""))
                throw new Exception("amount Exception");
            inputAmount = Integer.parseInt(amount.getText().trim());
        }catch(Exception e){
            errorString += "\n- amount";
        }

        try{
            if(price.getText().trim().equals(""))
                throw new Exception("price Excpetion");
            inputPrice = Integer.parseInt(price.getText().trim());
        }catch(Exception e){
            errorString += "\n- price";
        }

        if(name.getText().trim().equals("")){
            errorString += "\n- name";
        }

        if(errorString.equals("Invalid input for:")) {
            ProductSQL.insert(inputBarcode, inputAmount, inputPrice, name.getText());
            MainViewController.refresh();
            Stage stage = (Stage) barcode.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(errorString);
            alert.showAndWait();
        }

    }

}

package main.java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable{

    @FXML
    private AnchorPane productsPage;

    @FXML
    private AnchorPane productsSoldPage;

    private static FXMLLoader root;

    private boolean productsPageFlag;
    private boolean productsSoldPageFlag;

    private static AnchorPane currentProductsPage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void products(){
        try {
            if(!productsPageFlag) {
                root = new FXMLLoader(getClass().getResource("../FXML/ProductsAnchor.fxml"));
                setPage(productsPage);
                productsPage.getChildren().setAll((AnchorPane) root.load());
                productsPageFlag = true;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void productsSold(){
        try {
            if(!productsSoldPageFlag) {
                root = new FXMLLoader(getClass().getResource("../FXML/ProductsSoldAnchor.fxml"));
                productsSoldPage.getChildren().setAll((AnchorPane) root.load());
                productsSoldPageFlag = true;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void loadAddNew(){
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/MenuAddNew.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void setPage(AnchorPane productsPage){
        currentProductsPage = productsPage;
    }

    public static void refresh(){

        try {
            root = new FXMLLoader(MainViewController.class.getResource("../FXML/ProductsAnchor.fxml"));
            //makes an extra object
            //TODO: FIX
            currentProductsPage.getChildren().setAll((AnchorPane) root.load());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}

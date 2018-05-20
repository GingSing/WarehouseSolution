package main.java.Controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.java.Hibernate.Entities.Products;
import main.java.ProductSQL;
import org.jsoup.helper.StringUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsAnchorController implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField barcodeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField amountField;

    @FXML
    private TreeTableView<ProductList> productsView;

    @FXML
    private TreeTableView<String> customerCart;

    private ArrayList<Integer> cartBarcodes = new ArrayList<>();

    private JFXTreeTableColumn<ProductList, String> productBarcode;
    private JFXTreeTableColumn<ProductList, String> productName;
    private JFXTreeTableColumn<ProductList, String> productPrice;
    private JFXTreeTableColumn<ProductList, String> productAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productBarcode = new JFXTreeTableColumn<>("Product Barcode");
        productBarcode.setPrefWidth(150);
        productBarcode.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductList, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductList, String> param) {
                return param.getValue().getValue().barcode;
            }
        });

        productName = new JFXTreeTableColumn<>("Product Name");
        productName.setPrefWidth(150);
        productName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductList, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductList, String> param) {
                return param.getValue().getValue().name;
            }
        });

        productAmount = new JFXTreeTableColumn<>("Product Amount");
        productAmount.setPrefWidth(150);
        productAmount.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductList, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductList, String> param) {
                return param.getValue().getValue().amount;
            }
        });

        productPrice = new JFXTreeTableColumn<>("Product Price");
        productPrice.setPrefWidth(150);
        productPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductList, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductList, String> param) {
                return param.getValue().getValue().price;
            }
        });

        /**
         * Allows the changing of values ( need to add values to database)
         *
         */
        // productName section
        productName.setCellFactory(new Callback<TreeTableColumn<ProductList, String>, TreeTableCell<ProductList, String>>() {
            @Override
            public TreeTableCell<ProductList, String> call(TreeTableColumn<ProductList, String> param) {
                return new TextFieldTreeTableCell<>();
            }
        });

        productName.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        productName.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<ProductList, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<ProductList, String> event) {
                TreeItem<ProductList> currentEditingItem = productsView.getTreeItem(event.getTreeTablePosition().getRow());
                currentEditingItem.getValue().setNameProperty(event.getNewValue());
                ProductSQL.setName();
            }
        });

        // productAmount section
        productAmount.setCellFactory(new Callback<TreeTableColumn<ProductList, String>, TreeTableCell<ProductList, String>>() {
            @Override
            public TreeTableCell<ProductList, String> call(TreeTableColumn<ProductList, String> param) {
                return new TextFieldTreeTableCell<>();
            }
        });

        productAmount.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        productAmount.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<ProductList, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<ProductList, String> event) {
                TreeItem<ProductList> currentEditingItem = productsView.getTreeItem(event.getTreeTablePosition().getRow());
                currentEditingItem.getValue().setAmountProperty(event.getNewValue());
                ProductSQL.setAmount();
            }
        });

        // productPrice section
        productPrice.setCellFactory(new Callback<TreeTableColumn<ProductList, String>, TreeTableCell<ProductList, String>>() {
            @Override
            public TreeTableCell<ProductList, String> call(TreeTableColumn<ProductList, String> param) {
                return new TextFieldTreeTableCell<>();
            }
        });

        productPrice.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        productPrice.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<ProductList, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<ProductList, String> event) {
                TreeItem<ProductList> currentEditingItem = productsView.getTreeItem(event.getTreeTablePosition().getRow());
                currentEditingItem.getValue().setPriceProperty(event.getNewValue());
                ProductSQL.setPrice();
            }
        });

        ObservableList<ProductList> observableProducts = FXCollections.observableArrayList();
        List<Products> productsList = ProductSQL.getAllProducts();
        for(Products product: productsList) {
            observableProducts.add(new ProductList(product.getName(), product.getBarcode(), product.getAmount(), product.getPrice()));
        }

        final TreeItem<ProductList> root = new RecursiveTreeItem<ProductList>(observableProducts, RecursiveTreeObject::getChildren);

        productsView.setEditable(true);
        productsView.getColumns().setAll(productBarcode, productName, productAmount, productPrice);
        productsView.setRoot(root);
        productsView.setShowRoot(false);

    }

    public class ProductList extends RecursiveTreeObject<ProductList>{

        StringProperty name;
        StringProperty barcode;
        StringProperty amount;
        StringProperty price;

        public ProductList(String name, int barcode, int amount, int price){
            this.name = new SimpleStringProperty(name);
            this.barcode = new SimpleStringProperty(barcode + "");
            this.amount = new SimpleStringProperty(amount +  "");
            this.price = new SimpleStringProperty(price +  "");
        }

        public void setNameProperty(String name){
            this.name = new SimpleStringProperty(name);
        }

        public void setAmountProperty(String amount){
            this.amount = new SimpleStringProperty(amount);
        }

        public void setPriceProperty(String price){
            this.price = new SimpleStringProperty(price);
        }

        public String toString(){
            return "" + this.barcode.get();
        }

    }

    @FXML
    void addToCart(MouseEvent event) {

        for(TreeItem items: productsView.getSelectionModel().getSelectedItems()){
            cartBarcodes.add(Integer.parseInt(items.getValue().toString()));
        }

    }

    //TODO: make price double
    @FXML
    void autoComplete(KeyEvent event){

        List<Products> productsList = null;
        List<Products> tempList = ProductSQL.getAllProducts();

        if(event.getCode() != KeyCode.BACK_SPACE && ((TextField)event.getSource()).getText().length() != 0) {

            if (nameField.getText().length() != 0) {

                productsList = ProductSQL.getByName(nameField.getText());

            }

            if (priceField.getText().length() != 0 && StringUtil.isNumeric(priceField.getText())) {

                List<Products> priceList = ProductSQL.getByPrice(priceField.getText());

                if (productsList != null) {
                    productsList = mergeProductLists(productsList, priceList);
                } else {
                    productsList = priceList;
                }
            }

            if (amountField.getText().length() != 0 && StringUtil.isNumeric(amountField.getText())) {
                List<Products> amountList = ProductSQL.getByAmount(amountField.getText());

                if (productsList != null) {
                    productsList = mergeProductLists(productsList, amountList);
                } else {
                    productsList = amountList;
                }
            }

            if (barcodeField.getText().length() != 0 && StringUtil.isNumeric(barcodeField.getText())) {
                List<Products> barcodeList = ProductSQL.getByBarcode(barcodeField.getText());

                if (productsList != null) {
                    productsList = mergeProductLists(productsList, barcodeList);
                } else {
                    productsList = barcodeList;
                }
            }

            if (productsList == null) {
                productsList = ProductSQL.getAllProducts();
            }

            updateView(productsList);
        }else{
            updateView(tempList);
        }

    }

    /** Returns mergedList of values that are same in both input lists.
     *
     * @param productList
     * @param productListToMerge
     */
    public List<Products> mergeProductLists(List<Products> productList, List<Products> productListToMerge){

        List<Products> mergedList = new ArrayList<>();

        for(Products product: productListToMerge){
            if(productList.contains(product)){
                mergedList.add(product);
            }
        }

        return mergedList;

    }

    public void updateView(List<Products> productsList){

        ObservableList<ProductList> observableProducts = FXCollections.observableArrayList();
        for(Products product: productsList) {
            observableProducts.add(new ProductList(product.getName(), product.getBarcode(), product.getAmount(), product.getPrice()));
        }

        final TreeItem<ProductList> root = new RecursiveTreeItem<ProductList>(observableProducts, RecursiveTreeObject::getChildren);
        productsView.setRoot(root);
        productsView.setShowRoot(false);
    }
}

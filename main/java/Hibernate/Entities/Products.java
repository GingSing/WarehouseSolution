package main.java.Hibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products{

    @Id
    @Column(name = "product_barcode")
    private int barcode;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_amount")
    private int amount;

    @Column(name = "product_price")
    private int price;

    public Products(){

    }

    public Products(int barcode){

        this.barcode = barcode;

    }

    public Products(int barcode, String name, int amount, int price){
        this.barcode = barcode;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeAmount(int amount){
        this.amount = amount;
    }

    public void changePrice(int price){
        this.price = price;
    }

    public int getAmount(){
        return amount;
    }

    public int getPrice(){
        return price;
    }

    public int getBarcode(){
        return barcode;
    }

    public String getName(){
        return name;
    }

    public boolean equals(Object product){
        return this.barcode == ((Products) product).getBarcode();
    }


}

//public abstract class Product {
//
//    private int price;
//    private int amount;
//    private int barcode;
//    private String name;
//
//    public Product(String name, int price, int amount, int barcode){
//        this.price = price;
//        this.amount = amount;
//        this.name = name;
//        this.barcode = barcode;
//    }
//
//    public int getPrice(){
//
//        return this.price;
//
//    }
//
//    public int getAmount(){
//
//        return this.amount;
//
//    }
//
//    public int getBarcode(){
//
//        return this.barcode;
//
//    }
//
//    public String getName(){
//
//        return name;
//
//    }
//
//    public void addAmount(int difference){
//        this.amount += difference;
//    }
//
//    public void subtractAmount(int difference){
//        if(this.amount - difference >= 0) {
//            this.amount -= difference;
//        }
//    }
//
//    public void changePrice(int newPrice){
//
//        this.price = newPrice;
//
//    }
//
//    public void changeName(String newName){
//
//        this.name = newName;
//
//    }
//
//    public void changeBarcode(int newBarcode){
//
//        this.barcode = newBarcode;
//
//    }
//
//}

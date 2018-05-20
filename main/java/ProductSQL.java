package main.java;

import main.java.Hibernate.Connection.DBConnection;
import main.java.Hibernate.Entities.Products;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSQL {


    public static void insert(int barcode, int amount, int price, String name) {
        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {
                Products product = new Products(barcode, name, amount, price);
                obj.save(product);
            }
        });
    }

    public static List<Products> getByName(String name){

        List<Products> output = new ArrayList<Products>();

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {

                List<?> results = obj.createQuery("from Products where product_name like '" + name + "%'").list();
                for (Object result : results) {
                    output.add((Products) result);
                }
            }
        });

        return output;
    }

    public static List<Products> getByBarcode(String barcode){

        List<Products> output = new ArrayList<Products>();

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {

                List<?> results = obj.createQuery("from Products where product_barcode = " + barcode).list();
                for (Object result : results) {
                    output.add((Products) result);
                }

            }
        });

        return output;
    }

    public static List<Products> getByPrice(String price){

        List<Products> output = new ArrayList<Products>();

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {

                List<?> results = obj.createQuery("from Products where product_price = " + price).list();
                for (Object result : results) {
                    output.add((Products) result);
                }
            }
        });

        return output;
    }

    public static List<Products> getByAmount(String amount){

        List<Products> output = new ArrayList<Products>();

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {

                System.out.println("from Products where product_amount = " + amount);

                List<?> results = obj.createQuery("from Products where product_amount = " + amount).list();
                for (Object result : results) {
                    output.add((Products) result);
                }
            }
        });

        return output;
    }

    public static void delete(String id){

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {

                Query query = obj.createQuery("delete from products where id =" + id);

                query.executeUpdate();

            }
        });

    }

    public static List<Products> getAllProducts(){

        List<Products> output = new ArrayList<Products>();

        DBConnection.getInstance().execute(new ActionManager() {
            @Override
            public void execute(Session obj) {
                List<?> results = obj.createQuery("from Products").list();
                for(Object result : results) {
                    output.add((Products) result);
                }
            }
        });

        return output;

    }

    public static void setName(){

    }

    public static void setPrice(){

    }

    public static void setAmount(){

    }

}

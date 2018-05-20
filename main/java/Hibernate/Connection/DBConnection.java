package main.java.Hibernate.Connection;

import main.java.Utility;

public class DBConnection extends Utility {

    private static DBConnection instance = null;

    protected DBConnection(){

    }

    public static DBConnection getInstance(){
        if(instance==null){
            instance = new DBConnection();
        }
        return instance;
    }
}

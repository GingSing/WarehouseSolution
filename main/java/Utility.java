package main.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class Utility {

    protected static Session sessionObj;
    protected static SessionFactory sessionFactoryObj;

    public Utility(){
        if(sessionFactoryObj == null) {
            sessionFactoryObj = buildSessionFactory();
        }
    }

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("main/java/Hibernate/Connection/hibernate.cfg.xml");

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory();
        return sessionFactoryObj;
    }

    public void execute(ActionManager action) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            //naturally uses prepared statements
            action.execute(sessionObj);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }


}
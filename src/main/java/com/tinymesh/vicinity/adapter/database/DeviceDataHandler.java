package com.tinymesh.vicinity.adapter.database;

import com.tinymesh.vicinity.adapter.model.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class DeviceDataHandler {

    Session session;
    Transaction transaction;
    //private Device device1 = new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");
   // private Device device2 = new Device("Device2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com");


    public void setData(List<Device> deviceList){


        SessionFactory sessionFactory = new Configuration()
                .configure("database.xml")
                .buildSessionFactory();

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for(Device device : deviceList) {
                session.save(device);
                session.getTransaction().commit(); }

            System.out.println("Device name is set!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //closing the session
            session.close();
        }
        //closing the sessionFactory
        sessionFactory.close();
    }
    public void retrieveData(){
        SessionFactory sessionFactory = new Configuration()
                .configure("database.xml")
                .buildSessionFactory();
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM device");

        }catch (Exception e){

        }finally {
            session.close();
        }

    }
}

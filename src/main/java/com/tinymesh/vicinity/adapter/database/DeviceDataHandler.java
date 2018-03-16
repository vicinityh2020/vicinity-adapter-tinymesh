package com.tinymesh.vicinity.adapter.database;

import com.tinymesh.vicinity.adapter.model.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class DeviceDataHandler {
    private static DeviceDataHandler deviceDataHandler;

    private Session session;
    private Transaction transaction;
    private DeviceDataHandler(){}

    private SessionFactory sessionFactory = new Configuration()
            .configure("database.xml")
            .buildSessionFactory();
    //private Device device1 = new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");
   // private Device device2 = new Device("Device2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com");



    public void setData(List<Device> deviceList){

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for(Device device : deviceList) {
                session.save(device); }
            session.getTransaction().commit();

            System.out.println("Device name is set!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //closing the session
            session.close();
        }
        //closing the sessionFactory
    }
    public List<Device> retrieveData(){

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Device");
            List<Device> list = query.list();
            transaction.commit();
            System.out.println(list);

           session.close();
            return list;

    }
    public static DeviceDataHandler getInstance(){

        if (deviceDataHandler == null) {
            deviceDataHandler = new DeviceDataHandler();
        }
        return deviceDataHandler;
    }
}

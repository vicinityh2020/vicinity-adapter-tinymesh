package com.tinymesh.vicinity.adapter.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class DeviceUtilDataHandler {
    private static DeviceUtilDataHandler deviceUtilDataHandler;

    private Session session;
    private Transaction transaction;
    private DeviceUtilDataHandler(){}

    private SessionFactory sessionFactory = new Configuration()
            .configure("database.xml")
            .buildSessionFactory();


    public void setData(List<DeviceUtilization> deviceUtilList){

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for(DeviceUtilization deviceUtil : deviceUtilList) {
            session.save(deviceUtil); }
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //closing the session
            session.close();
        }
    }
    public List<DeviceUtilization> retrieveData(){

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM DEVICEUTILIZATION");
        List<DeviceUtilization> list = query.list();
        transaction.commit();
        System.out.println(list);


        session.close();
        return list;

    }
    public static DeviceUtilDataHandler getInstance(){

        if (deviceUtilDataHandler == null) {
            deviceUtilDataHandler = new DeviceUtilDataHandler();
        }
        return deviceUtilDataHandler;
    }
}

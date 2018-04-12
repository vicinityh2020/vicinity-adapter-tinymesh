package com.tinymesh.vicinity.adapter.repository;

import com.tinymesh.vicinity.adapter.entity.Device;
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
    private SessionFactory sessionFactory = new Configuration()
            .configure("database.xml")
            .buildSessionFactory();

    private DeviceDataHandler() {
    }

    public static DeviceDataHandler getInstance() {

        if (deviceDataHandler == null) {
            deviceDataHandler = new DeviceDataHandler();
        }
        return deviceDataHandler;
    }

    public void setData(List<Device> deviceList) {

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for (Device device : deviceList) {
                session.save(device);
            }
            session.getTransaction().commit();

            System.out.println("Device name is set!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //closing the session
            session.close();
        }
    }

    public List<Device> retrieveData() {

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Device");
        List<Device> list = query.list();
        transaction.commit();
        System.out.println(list);


        session.close();
        return list;

    }
}

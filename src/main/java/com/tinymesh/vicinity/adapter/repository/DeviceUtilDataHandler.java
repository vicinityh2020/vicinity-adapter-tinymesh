package com.tinymesh.vicinity.adapter.repository;

import com.tinymesh.vicinity.adapter.entity.DeviceUtilization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DeviceUtilDataHandler {
    private static DeviceUtilDataHandler deviceUtilDataHandler;

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory = new Configuration()
            .configure("database.xml")
            .buildSessionFactory();

    private DeviceUtilDataHandler() {
    }

    public static DeviceUtilDataHandler getInstance() {

        if (deviceUtilDataHandler == null) {
            deviceUtilDataHandler = new DeviceUtilDataHandler();
        }
        return deviceUtilDataHandler;
    }

    public void setData(List<DeviceUtilization> deviceUtilList) {

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            for (DeviceUtilization deviceUtil : deviceUtilList) {
                session.save(deviceUtil);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //closing the session
            session.close();
        }
    }

    public List<DeviceUtilization> retrieveData() {

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("FROM DeviceUtilization");
        List<DeviceUtilization> list = query.list();
        transaction.commit();
        System.out.println(list);


        session.close();
        return list;

    }
}

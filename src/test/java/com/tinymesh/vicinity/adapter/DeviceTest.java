package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.database.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;
public class DeviceTest {


    @Before
    public void setup() {
    }

    @Test
    public void getAllObjects() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("database.xml")
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        try {
            Device device = new Device();
            device.setDeviceType("Device 1");
            device.setState(true);

            session.persist(device);
            transaction.commit();

            System.out.println("Device name is set!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

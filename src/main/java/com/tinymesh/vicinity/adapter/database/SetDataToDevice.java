package com.tinymesh.vicinity.adapter.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.UUID;


public class SetDataToDevice {

    private UUID uuid = UUID.randomUUID();
    private LocalDateTime dateTime = LocalDateTime.now();
  //  private Device device1 = new Device(dateTime,"Device1", true, "www.test.com");
    //private Device device2 = new Device(dateTime,"Device2", false, "www.test2.com");


    public void setData(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("database.xml")
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
       //     session.persist(device1);
         //   session.persist(device2);
            transaction.commit();

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
}

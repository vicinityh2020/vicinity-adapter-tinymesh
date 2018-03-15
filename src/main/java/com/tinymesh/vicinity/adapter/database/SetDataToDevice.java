package com.tinymesh.vicinity.adapter.database;

import com.tinymesh.vicinity.adapter.model.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


public class SetDataToDevice {

    Session session;
    Transaction transaction;
    private Device device1 = new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");
    private Device device2 = new Device("Device2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com");

    @ElementCollection
    Collection<Device> deviceList = new ArrayList<>();

    public void setData(){
        deviceList.add(new Device("Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));


        SessionFactory sessionFactory = new Configuration()
                .configure("database.xml")
                .buildSessionFactory();
//        session.beginTransaction();

    //    for(Device device : deviceList) {
            session.save(deviceList);
            session.getTransaction().commit();
      //  }
        /*

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("database.xml")
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();



        try {

            session.save(deviceList);
            transaction.commit();


      //     session.persist(deviceList);
        //   session.persist(device2);

*/
        try{

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
/*
    public void retrive(){

        Configuration config = new Configuration();
        config.configure("database.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Device");
        List list = query.list();
        System.out.println(list);
        transaction.commit();
        session.close();


    }
    */
}

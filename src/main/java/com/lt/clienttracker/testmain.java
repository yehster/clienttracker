/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lt.clienttracker;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author yehster
 */
public class testmain {
        public static void main( String[] args )
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        List<client> events=sess.createQuery("from client").list();
        System.out.println(events.size());

        try
        {
            System.out.println(events.get(events.size()-1).toString());
            sess.beginTransaction();
            com.lt.clienttracker.client obj=new client();
            obj.setId("Kevin2");
            obj.setAdmissionAge(10, 2);
            obj.setAdmissionYear(2014);
            obj.setAdmissionMonth(11);
            obj.setLBGTQ("T");

            
//            sess.save(obj);
            diagnosis test=new diagnosis();
            events.get(2).getAssessments().add(test);
            test.setDescription("This is a code!");
            test.setClient(events.get(2));
//            sess.save(events.get(0));
            sess.save(events.get(2));
            sess.getTransaction().commit();
            sess.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            sess.getTransaction().rollback();
            sess.close();
        }
        System.out.println( "Hello World!" );
        sessFact.close();
    }

}

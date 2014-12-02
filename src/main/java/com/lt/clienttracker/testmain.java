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
            obj.setLGBTQ("T");

            
            client cur=events.get(0);
//            sess.save(obj);
            diagnosis test=new diagnosis();
            test.setDescription("This is a code!");
            test.setCode("123.40");
            test.setCodeType("DSM-IV");
            test.setMonth(12);
            test.setYear(2014);
            test.setClient(cur);
            sess.save(test);
            
            SASSI sassi=new SASSI();
            sassi.setRiskLow();
            sassi.setClient(cur);
            sassi.setMonth(12);
            sassi.setYear(2014);
            sess.save(sassi);

            ACE ace=new ACE();
            ace.setScore(1);
            ace.setClient(cur);
            ace.setMonth(12);
            ace.setYear(2014);
            sess.save(ace);
            
//            sess.save(events.get(0));
            sess.save(cur);
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

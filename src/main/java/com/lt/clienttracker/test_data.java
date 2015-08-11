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
public class test_data {
        public static void main( String[] args )
        {
            SessionFactory sessFact= HibernateManager.getSessionFactory();
            Session sess=sessFact.openSession();
            List<client> clients=sess.createQuery("from client").list();
            System.out.println(clients.size());

            try
            {
                client client = clients.get(0);
                System.out.println(client.assessments.size());
            }
            catch(Exception e)
            {

            }
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lt.clienttracker;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 *
 * @author yehster
 */
public class HibernateManager {
    private static final SessionFactory sessionFactory=buildSessionFactory();
    private static StandardServiceRegistryBuilder serviceRegistryBuilder;
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new Configuration();
            cfg.configure();
            serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(cfg
                            .getProperties());
            return cfg.buildSessionFactory(serviceRegistryBuilder.build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }    
    
}
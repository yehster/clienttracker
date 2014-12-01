/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lt.clienttracker;


import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.util.List;

@RestController
@RequestMapping("/cli")
public class clientsController {


    @RequestMapping("/get.do")
    public String getClients(@RequestParam(value="name", defaultValue="World") String name) {
        
//        SessionFactory sessFact= HibernateManager.getSessionFactory();
//        Session sess=sessFact.openSession();
//        List<client> events=sess.createQuery("from client").list();
        return (String)name+"Hello World!";
    }
}

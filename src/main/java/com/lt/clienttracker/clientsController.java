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
@RequestMapping("/client")
public class clientsController {


    @RequestMapping("/list.do")
    public List<client> getClients(@RequestParam(value="name", defaultValue="World") String name) {
        
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        List<client> clients=sess.createQuery("from client").list();

        return clients;
    }
    
    @RequestMapping("/new.do")
    public client newClient(@RequestParam(value="id") String id,
                            @RequestParam(value="agem") int agem,
                            @RequestParam(value="agey") int agey,
                            @RequestParam(value="admy") int admy,
                            @RequestParam(value="admm") int admm,
                            @RequestParam(value="lgbtq") String lgbtq,
                            @RequestParam(value="mommy_and_me") String mommy_and_me,
                            @RequestParam(value="trauma") String trauma,
                            @RequestParam(value="medical") String medical
                            
                            )
    {
        
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        sess.beginTransaction();
        client retval= new client();
        retval.setId(id);
        retval.setAdmissionAge(agey, agem);
        retval.setAdmissionYear(admy);
        retval.setAdmissionMonth(admm);
        retval.setLGBTQ(lgbtq);
        retval.setmommy_and_me(mommy_and_me);
        retval.settrauma(trauma);
        retval.setmedical(medical);
        sess.save(retval);
        sess.getTransaction().commit();
        return retval;
    }
    
    @RequestMapping("/get.do")
    public client getClient(@RequestParam(value="id") String id)
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        client retval= (client)sess.get(client.class, id);
        
        return retval;
    }
    @RequestMapping("/edit.do")
    public client editClient(@RequestParam(value="id") String id,
                            @RequestParam(value="agem") int agem,
                            @RequestParam(value="agey") int agey,
                            @RequestParam(value="admy") int admy,
                            @RequestParam(value="admm") int admm,
                            @RequestParam(value="lgbtq") String lgbtq,
                            @RequestParam(value="mommy_and_me") String mommy_and_me,
                            @RequestParam(value="trauma") String trauma,
                            @RequestParam(value="medical") String medical
                            )
    {
        
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        sess.beginTransaction();
        client retval= (client)sess.get(client.class, id);
        retval.setAdmissionAge(agey, agem);
        retval.setAdmissionYear(admy);
        retval.setAdmissionMonth(admm);
        retval.setLGBTQ(lgbtq);
        retval.setmommy_and_me(mommy_and_me);
        retval.settrauma(trauma);
        retval.setmedical(medical);
        sess.save(retval);
        sess.getTransaction().commit();
        return retval;
    }

}

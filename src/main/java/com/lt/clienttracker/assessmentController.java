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
import java.util.Calendar;

@RestController
@RequestMapping("/assessment")
public class assessmentController {

    @RequestMapping("new.do")
    public assessment newAssessment(@RequestParam(value="clientID") String clientID,
                                    @RequestParam(value="assessmentName") String assessmentName)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        sess.beginTransaction();
        client curClient= (client)sess.get(client.class, clientID);
        
        assessment retval;
        if(assessmentName.equals("diagnosis"))
        {
            retval=new diagnosis();
        }
        else if(assessmentName.equals("ACE"))
        {
            retval=new ACE();
        }
        else if(assessmentName.equals("SASSI"))
        {
            retval=new SASSI();
        }
        else
        {
            throw new Exception("Unknown Assessment Type");
        }
        retval.setYear(Calendar.getInstance().get(Calendar.YEAR));
        retval.setMonth(Calendar.getInstance().get(Calendar.MONTH));
        retval.setClient(curClient);
        sess.save(retval);
        sess.getTransaction().commit();
        return retval;
    }
    
    @RequestMapping("getdiagnosis.do")
    public diagnosis getdiagnosis(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        diagnosis retval= (diagnosis)sess.get(diagnosis.class, assessmentID);
        return retval;
    }

    @RequestMapping("getACE.do")
    public ACE getACE(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        ACE retval= (ACE)sess.get(ACE.class, assessmentID);
        return retval;
    }
    
    
    @RequestMapping("getSASSI.do")
    public SASSI getSASSI(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        SASSI retval= (SASSI)sess.get(SASSI.class, assessmentID);
        return retval;
    }    
}

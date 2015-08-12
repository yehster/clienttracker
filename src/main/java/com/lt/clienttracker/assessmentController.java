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
            diagnosis newDiagnosis =(diagnosis)retval;
            newDiagnosis.setCodeType("DSM-IV");
            newDiagnosis.setCode("");
            newDiagnosis.setDescription("");
        }
        else if(assessmentName.equals("ACE"))
        {
            retval=new ACE();
            ACE newACE= (ACE)retval;
            newACE.setScore(0);
        }
        else if(assessmentName.equals("SASSI"))
        {
            retval=new SASSI();
            SASSI newSASSI=(SASSI)retval;
            newSASSI.setRiskHigh();
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
    
    @RequestMapping("updatediagnosis.do")
    public String updatediagnosis(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month,
                                  @RequestParam(value="code")String code,
                                  @RequestParam(value="codeType")String codeType,
                                  @RequestParam(value="description")String description
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        diagnosis updatedAssessment= (diagnosis)sess.get(diagnosis.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        updatedAssessment.setCode(code);
        updatedAssessment.setCodeType(codeType);
        updatedAssessment.setDescription(description);
        
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
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

    @RequestMapping("updateACE.do")
    public String updateACE(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month,
                                  @RequestParam(value="score") int score
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        ACE updatedAssessment= (ACE)sess.get(ACE.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        updatedAssessment.setScore(score);        
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
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
    
    @RequestMapping("updateSASSI.do")
    public String updateSASSI(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month,
                                  @RequestParam(value="risk") String risk
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        SASSI updatedAssessment= (SASSI)sess.get(SASSI.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        updatedAssessment.setRisk(risk);        
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }        
    
    @RequestMapping("delete.do")
    public void deleteAssessment(@RequestParam(value="assessmentID") String assessmentID)
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();
        sess.beginTransaction();
        
        assessment toDelete= (assessment)sess.get(assessment.class, assessmentID);
        sess.delete(toDelete);
        sess.getTransaction().commit();
    }
}

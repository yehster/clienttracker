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
        else if(assessmentName.equals("Beck"))
        {
            retval=new Beck();
            Beck newBeck=(Beck)retval;
            newBeck.setBSCI_Y(0);
            newBeck.setBAI_Y(0);
            newBeck.setBDI_Y(0);
            newBeck.setBANI_Y(0);
            newBeck.setBDBI_Y(0);
        }
        else if(assessmentName.equals("CAFAS"))
        {
            retval=new CAFAS();
            CAFAS newCAFAS=(CAFAS)retval;
            newCAFAS.setschool_work_rp(0);
            newCAFAS.sethome_rp(0);
            newCAFAS.setcommunity_rp(0);
            newCAFAS.setbehavior_toward_others(0);
            newCAFAS.setmood_emotions(0);
            newCAFAS.setself_harmful_behavior(0);
            newCAFAS.setsubstance_use(0);
            newCAFAS.setthinking(0);
        }        
        else if(assessmentName.equals("discharge"))
        {
            retval=new discharge();
            discharge newDischarge=(discharge)retval;
            newDischarge.setPlacement("unknown");
        }
        else if(assessmentName.equals("CAFAS"))
        {
            retval=new CAFAS();
            CAFAS newCAFAS=(CAFAS)retval;
        }                    
        else if(assessmentName.equals("employment"))
        {
            retval=new employment();
            employment newDischarge=(employment)retval;
        }            
        else if(assessmentName.equals("graduation"))
        {
            retval=new graduation();
            graduation newGraduation=(graduation)retval;
        }
        else
        {
            throw new Exception("Unknown Assessment Type");
        }
        retval.setYear(Calendar.getInstance().get(Calendar.YEAR));
        retval.setMonth(Calendar.getInstance().get(Calendar.MONTH)+1); // Turn zero based java month to standard month
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

    
    @RequestMapping("getBeck.do")
    public Beck getBeck(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        Beck retval= (Beck)sess.get(Beck.class, assessmentID);
        return retval;
    }

    @RequestMapping("updateBeck.do")
    public String updateBeck(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month,
                                  @RequestParam(value="bsci_Y") int BSCI_Y,
                                  @RequestParam(value="bai_Y") int BAI_Y,
                                  @RequestParam(value="bdi_Y") int BDI_Y,
                                  @RequestParam(value="bani_Y") int BANI_Y,
                                  @RequestParam(value="bdbi_Y") int BDBI_Y
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        Beck updatedAssessment= (Beck)sess.get(Beck.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        updatedAssessment.setBSCI_Y(BSCI_Y);
        updatedAssessment.setBAI_Y(BAI_Y);
        updatedAssessment.setBDI_Y(BDI_Y);
        updatedAssessment.setBANI_Y(BANI_Y);
        updatedAssessment.setBDBI_Y(BDBI_Y);
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }    

    @RequestMapping("getCAFAS.do")
    public CAFAS getCAFAS(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        CAFAS retval= (CAFAS)sess.get(CAFAS.class, assessmentID);
        return retval;
    }

    @RequestMapping("updateCAFAS.do")
    public String updateCAFAS(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month,
                                  @RequestParam(value="school_work_rp") int school_work_rp,
                                  @RequestParam(value="home_rp") int home_rp,
                                  @RequestParam(value="community_rp") int community_rp,
                                  @RequestParam(value="behavior_toward_others") int behavior_toward_others,
                                  @RequestParam(value="mood_emotions") int mood_emotions,
                                  @RequestParam(value="self_harmful_behavior") int self_harmful_behavior,
                                  @RequestParam(value="substance_use") int substance_use,
                                  @RequestParam(value="thinking") int thinking
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        CAFAS updatedAssessment= (CAFAS)sess.get(CAFAS.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        updatedAssessment.setschool_work_rp(school_work_rp);
        updatedAssessment.sethome_rp(home_rp);
        updatedAssessment.setcommunity_rp(community_rp);
        updatedAssessment.setbehavior_toward_others(behavior_toward_others);
        updatedAssessment.setmood_emotions(mood_emotions);
        updatedAssessment.setself_harmful_behavior(self_harmful_behavior);
        updatedAssessment.setsubstance_use(substance_use);
        updatedAssessment.setthinking(thinking);
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }    
    
    @RequestMapping("getdischarge.do")
    public discharge getDischarge(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        discharge retval= (discharge)sess.get(discharge.class, assessmentID);
        return retval;
    }

    @RequestMapping("updatedischarge.do")
    public String updateDischarge(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        discharge updatedAssessment= (discharge)sess.get(discharge.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        

        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }
    
    @RequestMapping("getemployment.do")
    public employment getEmployment(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        employment retval= (employment)sess.get(employment.class, assessmentID);
        return retval;
    }

    @RequestMapping("updateemployment.do")
    public String updateemployment(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        employment updatedAssessment= (employment)sess.get(employment.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }        

    @RequestMapping("getgraduation.do")
    public graduation getGraduation(@RequestParam(value="assessmentID") String assessmentID)
            throws Exception
    {
        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        graduation retval= (graduation)sess.get(graduation.class, assessmentID);
        return retval;
    }

    @RequestMapping("updategraduation.do")
    public String updategraduation(@RequestParam(value="id") String id,
                                  @RequestParam(value="year") int year,
                                  @RequestParam(value="month") int month
                                  )
    {

        SessionFactory sessFact= HibernateManager.getSessionFactory();
        Session sess=sessFact.openSession();

        sess.beginTransaction();
        graduation updatedAssessment= (graduation)sess.get(graduation.class, id);
        
        updatedAssessment.setYear(year);
        updatedAssessment.setMonth(month);
        
        // Class specific fields
        
        sess.save(updatedAssessment);
        sess.getTransaction().commit();
        return id;
    }         
 /****************************/
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lt.clienttracker;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
/**
 *
 * @author yehster
 */
@Entity  
@Table(name="cafas")  
@PrimaryKeyJoinColumn(name="id")  
public class CAFAS  extends assessment implements java.io.Serializable {
    
    protected int school_work_rp;
    
    @Column(name="school_work_rp")
    public int getschool_work_rp()
    {
        return this.school_work_rp;
    }
    
    public void setschool_work_rp(int value)
    {
        this.school_work_rp=value;
    }
    
    protected int home_rp;
    
    @Column(name="home_rp")
    public int gethome_rp()
    {
        return this.home_rp;
    }
    
    public void sethome_rp(int value)
    {
        this.home_rp=value;
    } 
    
    protected int community_rp;
    
    @Column(name="community_rp")
    public int getcommunity_rp()
    {
        return this.community_rp;
    }
    
    public void setcommunity_rp(int value)
    {
        this.community_rp=value;
    }        

    
    protected int behavior_toward_others;
    
    @Column(name="behavior_toward_others")
    public int getbehavior_toward_others()
    {
        return this.behavior_toward_others;
    }
    
    public void setbehavior_toward_others(int value)
    {
        this.behavior_toward_others=value;
    }
    
    protected int mood_emotions;
    
    @Column(name="mood_emotions")
    public int getmood_emotions()
    {
        return this.mood_emotions;
    }
    
    public void setmood_emotions(int value)
    {
        this.mood_emotions=value;
    }
    
    protected int self_harmful_behavior;
    
    @Column(name="self_harmful_behavior")
    public int getself_harmful_behavior()
    {
        return this.self_harmful_behavior;
    }
    
    public void setself_harmful_behavior(int value)
    {
        this.self_harmful_behavior=value;
    }           

    
    protected int substance_use;
    
    @Column(name="substance_use")
    public int getsubstance_use()
    {
        return this.substance_use;
    }
    
    public void setsubstance_use(int value)
    {
        this.substance_use=value;
    }
    
    protected int thinking;
    
    @Column(name="thinking")
    public int getthinking()
    {
        return this.thinking;
    }
    
    public void setthinking(int value)
    {
        this.thinking=value;
    }
    
    @Override
    public String AssessmentName()
    {
        return "CAFAS";
    }
    
     
    public int total()
    {
        return this.school_work_rp+this.home_rp+this.community_rp+this.behavior_toward_others
                +this.mood_emotions+this.self_harmful_behavior+this.substance_use+this.thinking;
    }
    @Override
    public String DisplayInfo()
    {
        return "TOTAL:"+ this.total() + " "
              + "S/W:"+this.school_work_rp + " "
              +"HOME:"+this.home_rp + " "
              +"COM:"+this.community_rp + " "
              +"BTO:"+this.behavior_toward_others + " "
              +"MOOD:"+this.mood_emotions + " "
              +"SHB:"+this.self_harmful_behavior + " "
              +"SUB:"+this.substance_use + " "
              +"THK:"+this.thinking;
        
    }         
}

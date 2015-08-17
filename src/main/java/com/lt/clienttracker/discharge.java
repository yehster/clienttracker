/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lt.clienttracker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author yehster
 */
@Entity  
@Table(name="discharge")  
@PrimaryKeyJoinColumn(name="id") 
public class discharge extends assessment implements java.io.Serializable {
    /*
    they leave us and go 1. to a higher level of care, called RTC (rehab, detention, lockdown bootcamp, psych hospital) 2. to a similar level of care (other group home or the like) 3. to a lower level of care (transitional or independent living 4. back with their families
    */
    
    protected String placement;

    @Column(name="placement")
    public String getPlacement()
    {
        return this.placement;
    }
    
    public void setPlacement(String placement)
    {
        this.placement=placement;
    }
    
    @Override
    public String AssessmentName()
    {
        return "discharge";
    }
    
    @Override
    public String DisplayInfo()
    {
        return this.placement;
        
    }     
}

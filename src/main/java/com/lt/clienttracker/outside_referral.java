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
@Table(name="outside_referral")  
@PrimaryKeyJoinColumn(name="id") 
public class outside_referral extends assessment implements java.io.Serializable {

    
    protected String location;

    @Column(name="location")
    public String getLocation()
    {
        return this.location;
    }
    
    public void setLocation(String location)
    {
        this.location=location;
    }
    
    @Override
    public String AssessmentName()
    {
        return "outside_referral";
    }
    
    @Override
    public String DisplayInfo()
    {
        return this.location;
        
    }     
}

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
@Table(name="graduation")  
@PrimaryKeyJoinColumn(name="id") 
public class graduation extends assessment implements java.io.Serializable {

    
    @Override
    public String AssessmentName()
    {
        return "graduation";
    }
    
    @Override
    public String DisplayInfo()
    {
        return "";
        
    }     
}
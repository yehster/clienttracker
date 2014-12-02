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
@Table(name="diagnoses")  
@PrimaryKeyJoinColumn(name="id")  
public class diagnosis extends assessment {
    
    String Description;
    @Column(name="description")
    public String getDescription()
    {
        return this.Description;
    }
    
    public void setDescription(String description)
    {
        this.Description = description;
    }
    
    String code_type;
    @Column(name="code_type")
    public String getCodeType()
    {
        return this.code_type;
    }
    
    public void setCodeType(String code_type)
    {
        this.code_type = code_type;
    }    

    String code;
    @Column(name="code")
    public String getCode()
    {
        return this.code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }    
    
    @Override
    public String AssessmentName()
    {
        return "diagnosis";
    }    
    
    @Override
    public String DisplayInfo()
    {
        return this.Description+":"+this.code+":"+this.code_type;
    }
    
}

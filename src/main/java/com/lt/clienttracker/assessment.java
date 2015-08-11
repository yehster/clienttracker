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

        
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author yehster
 */
@Entity
@Table(name="assessment")
@Inheritance(strategy=InheritanceType.JOINED)
public class assessment implements java.io.Serializable  {
    
    protected String id;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId()
    {
        return this.id;
    }
    
    public void setId(String id)
    {
        this.id=id;
    }
    
    protected int Month;
    
    @Column(name="assessment_month")
    public int getMonth()
    {
        return this.Month;
    }
    
    public void setMonth(int Month)
    {
        this.Month= Month;
    }

    
    protected int Year;
    @Column(name="assessment_year")
    public int getYear()
    {
        return this.Year;
    }
    
    public void setYear(int Year)
    {
        this.Year = Year;
    }
    
    
    @JsonProperty
    public String AssessmentName()
    {
        return "assessment";
    }

    @JsonProperty
    public String DisplayInfo()
    {
        return "assessment";
    }

    protected client client;
    
    @JsonBackReference
    @ManyToOne
    public client getClient()
    {
        return this.client;
    }
    
    public void setClient(client client)
    {
        this.client=client;
    }
}

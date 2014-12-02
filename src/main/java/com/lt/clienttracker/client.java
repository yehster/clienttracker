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
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

/**
 *
 * @author yehster
 */
@Entity
@Table(name="clients")
public class client implements java.io.Serializable {
    protected String id;

    @Id
    public String getId()
    {
        return this.id;
    }
    
    public void setId(String id)
    {
        this.id=id;
    }

    protected int AdmissionAgeInMonths;
    
    @Column(name="admissionageinmonths")
    public int getAdmissionAgeInMonths()
    {
        return AdmissionAgeInMonths;
    }

    public void setAdmissionAgeInMonths(int AdmissionAgeInMonths)
    {
        this.AdmissionAgeInMonths=AdmissionAgeInMonths;
    }
        
    
    public void setAdmissionAge(int years, int months)
    {
        this.AdmissionAgeInMonths=12*years + months;
    }

    protected int AdmissionYear;
    
    @Column(name="admissionyear")
    public int getAdmissionYear()
    {
        return this.AdmissionYear;
    }
    
    public void setAdmissionYear(int AdmissionYear)
    {
        this.AdmissionYear=AdmissionYear;
    }
    
    
    protected int AdmissionMonth;
    
    @Column(name="admissionmonth")
    public int getAdmissionMonth()
    {
        return this.AdmissionMonth;
    }
    
    public void setAdmissionMonth(int AdmissionYear)
    {
        this.AdmissionMonth=AdmissionYear;
    }

    protected String LGBTQ;

    @Column(name="LGBTQ")
    public String getLGBTQ()
    {
        return this.LGBTQ;
    }
    public void setLGBTQ(String LGBTQ)
    {
        this.LGBTQ=LGBTQ;
    }
    
    @JsonManagedReference
    Set<assessment> assessments;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    public Set<assessment> getAssessments()
    {
        return this.assessments;
    }
    
    public void setAssessments(Set<assessment> assessments)
    {
        this.assessments = assessments;
    }
    
    @Override
    public String toString()
    {
        return this.id + ":" + this.AdmissionAgeInMonths+":"+LGBTQ+":"+this.AdmissionYear;
    }
    
}

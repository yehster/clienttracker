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
@Table(name="beck")  
@PrimaryKeyJoinColumn(name="id")  
public class Beck extends assessment implements java.io.Serializable{
    /*  becks T scores (there are five separate scales) are out of 100 
    BSCI-Y, BAI-Y, BDI-Y, BANI-Y, BDBI-Y
    http://counselingyouthwithdepression.weebly.com/beck-youth-inventories-byi.html
    */
    
    /* 0-100 */
    protected int BSCI_Y;
    
    @Column(name="BSCI_Y")
    public int getBSCI_Y()
    {
        return this.BSCI_Y;
    }
    
    public void setBSCI_Y(int value)
    {
        this.BSCI_Y=value;
    }

    /* 0-100 */
    protected int BAI_Y;
    
    @Column(name="BAI_Y")
    public int getBAI_Y()
    {
        return this.BAI_Y;
    }
    
    public void setBAI_Y(int value)
    {
        this.BAI_Y=value;
    }

    /* 0-100 */
    protected int BDI_Y;
    
    @Column(name="BDI_Y")
    public int getBDI_Y()
    {
        return this.BDI_Y;
    }
    
    public void setBDI_Y(int value)
    {
        this.BDI_Y=value;
    }

    /* 0-100 */
    protected int BANI_Y;
    
    @Column(name="BANI_Y")
    public int getBANI_Y()
    {
        return this.BANI_Y;
    }
    
    public void setBANI_Y(int value)
    {
        this.BANI_Y=value;
    }

    /* 0-100 */
    protected int BDBI_Y;
    
    @Column(name="BDBI_Y")
    public int getBDBI_Y()
    {
        return this.BDBI_Y;
    }
    
    public void setBDBI_Y(int value)
    {
        this.BDBI_Y=value;
    }
    
    @Override
    public String AssessmentName()
    {
        return "Beck";
    }
    
    @Override
    public String DisplayInfo()
    {
        return "BSCI-Y:"+Integer.toString(BSCI_Y)+ " " 
               + "BAI-Y:"+Integer.toString(BAI_Y)+ " " 
               + "BDI-Y:"+Integer.toString(BDI_Y)+ " " 
               + "BANI-Y:"+Integer.toString(BANI_Y)+ " " 
               + "BDBI-Y:"+Integer.toString(BDBI_Y)+ " " 
                ;
        
    }       
}

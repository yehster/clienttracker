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
@Table(name="sassi")  
@PrimaryKeyJoinColumn(name="id")  
public class SASSI extends assessment implements java.io.Serializable {
    /* oh, for the SASSI score, it is just  reports high, moderate or low probability for a substance use disorder so it isn't a number */
    
}

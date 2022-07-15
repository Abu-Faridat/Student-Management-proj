/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author EKUNSAJUOLA
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TransactionData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    long id;
    double schoolFees;
    double amountPaid;
    LocalDate datePaid;
    boolean isOwing;
    double balance;
    
    @ManyToOne
    StudentData student;

    public TransactionData(StudentData student,double amountPaid) {
        this.amountPaid = amountPaid;
        this.datePaid = LocalDate.now();
        this.student = student;
    }
    
    
}

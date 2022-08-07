/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.StudentData;
import com.student.model.TransactionData;
import javax.persistence.EntityManager;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Transaction_repository {
    
    EntityManager em = ConnectionDatabase.getEntityManager();
    
    public TransactionData createTransaction(long studentId, double amountPaid) {
        
        StudentData student = em.find(StudentData.class, studentId);
        
        TransactionData trans = new TransactionData(student, amountPaid);
        
        em.persist(trans);
        
        return trans;
    }
}

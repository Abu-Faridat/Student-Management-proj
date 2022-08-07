/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.Subjects;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Subject_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public Subjects createSubject(String subjectName, String student) {

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        Subjects sb = new Subjects();
        
       // sb.setStudent(student);
        sb.setSubjectName(subjectName);
        
        em.persist(sb);
        txn.commit();
        return sb;
    }
    
//    public Subjects getSubjectByName (String subjectName){
//        
//        Query myQuery = em.createNamedQuery();
//    }
    public Subjects deleteSubjects (String subjectName){
        
         Subjects sb = em.find(Subjects.class, subjectName);

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        em.remove(sb);

        txn.commit();

        return sb;
    }
}

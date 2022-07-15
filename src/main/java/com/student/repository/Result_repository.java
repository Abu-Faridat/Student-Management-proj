/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.Results;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Result_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public Results createStudentResults(long id, String student, String subject) {

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        Results rs = new Results();

        rs.setId(id);
//        rs.setStudent(student);
//        rs.setSubjects(subjects);

        em.persist(rs);
        txn.commit();
        return rs;

    }

    public Results findStudentResultByName(String student) {

        Query myQuery = em.createNamedQuery("SELECT * FROM Results WHERE Results = " + student);

        Results rs = (Results) myQuery.getSingleResult();

        return rs;

    }

    public Results updateResultByName(String student) {

        Query myQuery = em.createNamedQuery("UPDATE RESULTS WHERE Results =" + student);

        Results rs = (Results) myQuery.getSingleResult();

        return rs;
    }

    public Results updateResultBySubject(String subject) {

        Query myQuery = em.createNamedQuery("UPDATE RESULTS WHERE Results =" + subject);

        Results rs = (Results) myQuery.getSingleResult();

        return rs;
    }

    public Results deleteResultById(long id) {

        Results rs = em.find(Results.class, id);

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        em.remove(rs);

        txn.commit();

        return rs;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.Results;
import com.student.model.StudentData;
import com.student.model.Subjects;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Result_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public Results createStudentResults(long studentId, long subjectId, int score) {

        EntityTransaction txn = em.getTransaction();

        StudentData student = em.find(StudentData.class, studentId);

        Subjects subject = em.find(Subjects.class, subjectId);

        txn.begin();

        Results rs = new Results(student, subject, score);

        em.persist(rs);
        txn.commit();
        return rs;

    }

    public List<Results> findStudentResultById(long id) {

        Query myQuery = em.createNamedQuery("SELECT * FROM Results WHERE Results = " + id);

        List<Results> rs = myQuery.getResultList();

        return rs;

    }

    public Results updateResultByid(long id, int score, long subjectId) {

        Results results = em.find(Results.class, id);

        results.setScore(score);

        return results;
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

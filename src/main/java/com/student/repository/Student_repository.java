/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.enums.Gender;
import com.student.enums.Sections;
import com.student.model.StudentData;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 * Remember to update the logic in the addStudent method and updateStudent
 * method
 *
 * @author EKUNSAJUOLA
 */
public class Student_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public StudentData addStudent(String firstName, String lastName, String dateOfBirth, int age,
            String classpath, String phoneNumber, String email) {

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        StudentData sd = new StudentData();
        sd.setFirstName(firstName);
        sd.setLastName(lastName);
        sd.setGender(Gender.Male);
        sd.setDateOfBirth(dateOfBirth);
        sd.setAge(age);
        sd.setSection(Sections.juniorSection);
        sd.setClasspath(classpath);
        //sd.setDepartment(department);
        // sd.setSubject(subject);
        sd.setEmail(email);
        sd.setPhoneNumber(phoneNumber);
        em.persist(sd);

        txn.commit();
        return sd;
    }

    public StudentData updateStudentById(long id) {

        StudentData sd = em.find(StudentData.class, id);

        EntityTransaction txn = em.getTransaction();
        txn.begin();

        sd.setFirstName("firstName");
        sd.setLastName("lastName");
        em.merge(sd);

        txn.commit();

        return sd;
    }

    public StudentData findStudentById(long id) {

        StudentData sd = em.find(StudentData.class, id);

        return sd;
    }

    public StudentData findStudentByEmail(String email) {
        Query myQuery = em.createNamedQuery("SELECT * FROM STUDENT WHERE email = " + email);

        StudentData sd = (StudentData) myQuery.getSingleResult();

        return sd;
    }

    public void deleteStudentById(long id) {

        StudentData sd = em.find(StudentData.class, id);

        EntityTransaction txn = em.getTransaction();
        txn.begin();
        em.remove(sd);
        txn.commit();
    }

    public void deleteAllStudent() {

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.student.database;

import com.student.model.StudentData;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author EKUNSAJUOLA
 */
public class ConnectionDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Student_Management_System");

        EntityManager em = emf.createEntityManager();

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        StudentData students = new StudentData();

        students.setFirstName("Faridat");
        students.setLastName("Abu");
        students.setAddress("Ikorodu");
        students.setAge(18);
        students.setPhoneNumber("09013787931");
        students.setEmail("faridatabu@yahoo.com");

        em.persist(students);

        txn.commit();

    }

    public static EntityManager getEntityManager() {

        return Persistence.createEntityManagerFactory("Student_Management_System").createEntityManager();
    }

}

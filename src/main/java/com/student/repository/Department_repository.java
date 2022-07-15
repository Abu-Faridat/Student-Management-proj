/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.Department;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Department_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public Department createDepartment(String departmentName, String students) {
        EntityTransaction txn = em.getTransaction();

        txn.begin();

        Department dt = new Department();
        dt.setDepartmentName(departmentName);
        //dt.setStudents(students);
         em.persist(dt);
        txn.commit();
        return dt;

    }

    public Department updateDepartmentBYName(String departmentName) {

        Query myQuery = em.createNamedQuery("UPDATE DEPARTMENT WHERE Department = " + departmentName);

        Department dt = (Department) myQuery.getSingleResult();

        return dt;

    }

    public Department findDepartmentByName(String departmentName) {
        Query myQuery = em.createNamedQuery("SELECT * FROM DEPARTMENT WHERE Department = " + departmentName);

        Department dt = (Department) myQuery.getSingleResult();

        return dt;
    }

    public Department deleteDepartmentById(long id) {

        Department dt = em.find(Department.class, id);

        EntityTransaction txn = em.getTransaction();

        txn.begin();

        em.remove(dt);

        txn.commit();

        return dt;

    }
    
//    @Override
//    public <StudentData>  findAllStudentByDepartmentName(){
//        
//        List<StudentData> students = new ArrayList<>();
//        
//        
//        return students;
//    }
}

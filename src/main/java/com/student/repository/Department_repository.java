/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.repository;

import com.student.database.ConnectionDatabase;
import com.student.model.Department;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author EKUNSAJUOLA
 */
public class Department_repository {

    EntityManager em = ConnectionDatabase.getEntityManager();

    public Department createDepartment(String departmentName) {
        EntityTransaction txn = em.getTransaction();

        txn.begin();

        Department dt = new Department();
        dt.setDepartmentName(departmentName);
        //dt.setStudents(students);
         em.persist(dt);
        txn.commit();
        return dt;

    }
    
    public Department updateDepartmentBYName(String departmentName,long id) {

       Department dt = em.find(Department.class, id);

        dt.setDepartmentName(departmentName);

        return dt;

    }

    public Department findDepartmentByName(long id) {
        
       Department dt = em.find(Department.class, id);

      
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.controller;

import com.student.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.student.model.Subjects;
import java.util.HashSet;
import java.util.Set;
import com.student.model.Department;
import com.student.model.StudentData;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author EKUNSAJUOLA
 */
public class StudentDataJpaController implements Serializable {

    public StudentDataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentData studentData) {
        if (studentData.getSubject() == null) {
            studentData.setSubject(new HashSet<Subjects>());
        }
        if (studentData.getDepartment() == null) {
            studentData.setDepartment(new HashSet<Department>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Subjects> attachedSubject = new HashSet<Subjects>();
            for (Subjects subjectSubjectsToAttach : studentData.getSubject()) {
                subjectSubjectsToAttach = em.getReference(subjectSubjectsToAttach.getClass(), subjectSubjectsToAttach.getId());
                attachedSubject.add(subjectSubjectsToAttach);
            }
            studentData.setSubject(attachedSubject);
            Set<Department> attachedDepartment = new HashSet<Department>();
            for (Department departmentDepartmentToAttach : studentData.getDepartment()) {
                departmentDepartmentToAttach = em.getReference(departmentDepartmentToAttach.getClass(), departmentDepartmentToAttach.getId());
                attachedDepartment.add(departmentDepartmentToAttach);
            }
            studentData.setDepartment(attachedDepartment);
            em.persist(studentData);
            for (Subjects subjectSubjects : studentData.getSubject()) {
                subjectSubjects.getStudent().add(studentData);
                subjectSubjects = em.merge(subjectSubjects);
            }
            for (Department departmentDepartment : studentData.getDepartment()) {
                departmentDepartment.getStudents().add(studentData);
                departmentDepartment = em.merge(departmentDepartment);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentData studentData) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentData persistentStudentData = em.find(StudentData.class, studentData.getId());
            Set<Subjects> subjectOld = persistentStudentData.getSubject();
            Set<Subjects> subjectNew = studentData.getSubject();
            Set<Department> departmentOld = persistentStudentData.getDepartment();
            Set<Department> departmentNew = studentData.getDepartment();
            Set<Subjects> attachedSubjectNew = new HashSet<Subjects>();
            for (Subjects subjectNewSubjectsToAttach : subjectNew) {
                subjectNewSubjectsToAttach = em.getReference(subjectNewSubjectsToAttach.getClass(), subjectNewSubjectsToAttach.getId());
                attachedSubjectNew.add(subjectNewSubjectsToAttach);
            }
            subjectNew = attachedSubjectNew;
            studentData.setSubject(subjectNew);
            Set<Department> attachedDepartmentNew = new HashSet<Department>();
            for (Department departmentNewDepartmentToAttach : departmentNew) {
                departmentNewDepartmentToAttach = em.getReference(departmentNewDepartmentToAttach.getClass(), departmentNewDepartmentToAttach.getId());
                attachedDepartmentNew.add(departmentNewDepartmentToAttach);
            }
            departmentNew = attachedDepartmentNew;
            studentData.setDepartment(departmentNew);
            studentData = em.merge(studentData);
            for (Subjects subjectOldSubjects : subjectOld) {
                if (!subjectNew.contains(subjectOldSubjects)) {
                    subjectOldSubjects.getStudent().remove(studentData);
                    subjectOldSubjects = em.merge(subjectOldSubjects);
                }
            }
            for (Subjects subjectNewSubjects : subjectNew) {
                if (!subjectOld.contains(subjectNewSubjects)) {
                    subjectNewSubjects.getStudent().add(studentData);
                    subjectNewSubjects = em.merge(subjectNewSubjects);
                }
            }
            for (Department departmentOldDepartment : departmentOld) {
                if (!departmentNew.contains(departmentOldDepartment)) {
                    departmentOldDepartment.getStudents().remove(studentData);
                    departmentOldDepartment = em.merge(departmentOldDepartment);
                }
            }
            for (Department departmentNewDepartment : departmentNew) {
                if (!departmentOld.contains(departmentNewDepartment)) {
                    departmentNewDepartment.getStudents().add(studentData);
                    departmentNewDepartment = em.merge(departmentNewDepartment);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = studentData.getId();
                if (findStudentData(id) == null) {
                    throw new NonexistentEntityException("The studentData with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentData studentData;
            try {
                studentData = em.getReference(StudentData.class, id);
                studentData.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentData with id " + id + " no longer exists.", enfe);
            }
            Set<Subjects> subject = studentData.getSubject();
            for (Subjects subjectSubjects : subject) {
                subjectSubjects.getStudent().remove(studentData);
                subjectSubjects = em.merge(subjectSubjects);
            }
            Set<Department> department = studentData.getDepartment();
            for (Department departmentDepartment : department) {
                departmentDepartment.getStudents().remove(studentData);
                departmentDepartment = em.merge(departmentDepartment);
            }
            em.remove(studentData);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StudentData> findStudentDataEntities() {
        return findStudentDataEntities(true, -1, -1);
    }

    public List<StudentData> findStudentDataEntities(int maxResults, int firstResult) {
        return findStudentDataEntities(false, maxResults, firstResult);
    }

    private List<StudentData> findStudentDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentData.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StudentData findStudentData(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentData.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentData> rt = cq.from(StudentData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

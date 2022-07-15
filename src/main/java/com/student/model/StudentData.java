/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.model;

import com.student.enums.Sections;
import com.student.enums.Gender;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class StudentData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)        
    long id;
    String firstName;
    String lastName;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String dateOfBirth;
    int age;
    @Enumerated(EnumType.STRING)
    Sections section; //enum
    String classpath; //enum
    String address;
    String phoneNumber;
    String email;
    @ManyToMany
    Set<Subjects> subject;
    @ManyToMany
    Set<Department> department;

       
}

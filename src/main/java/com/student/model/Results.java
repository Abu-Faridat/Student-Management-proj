/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    StudentData student;

    @OneToOne
    Subjects subject;

    int score;

    public Results(StudentData student, Subjects subject, int score) {
        this.student = student;
        this.subject = subject;
        this.score = score;
    }

    public Results() {
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author EKUNSAJUOLA
 */
@Data
@Getter
@Setter
@ToString
public class Student_dto {
    String firstName;
    String lastName;
    String dateOfBirth;
    int age;
    String classpath; //enum
    String address;
    String phoneNumber;
    String email;
}

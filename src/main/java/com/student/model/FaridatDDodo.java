/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.student.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EKUNSAJUOLA
 */
@Entity
@Table(name = "faridat_d_dodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaridatDDodo.findAll", query = "SELECT f FROM FaridatDDodo f"),
    @NamedQuery(name = "FaridatDDodo.findById", query = "SELECT f FROM FaridatDDodo f WHERE f.id = :id"),
    @NamedQuery(name = "FaridatDDodo.findByFirstName", query = "SELECT f FROM FaridatDDodo f WHERE f.firstName = :firstName"),
    @NamedQuery(name = "FaridatDDodo.findByLastname", query = "SELECT f FROM FaridatDDodo f WHERE f.lastname = :lastname")})
public class FaridatDDodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;

    public FaridatDDodo() {
    }

    public FaridatDDodo(Integer id) {
        this.id = id;
    }

    public FaridatDDodo(Integer id, String firstName, String lastname) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaridatDDodo)) {
            return false;
        }
        FaridatDDodo other = (FaridatDDodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.student.model.FaridatDDodo[ id=" + id + " ]";
    }
    
}

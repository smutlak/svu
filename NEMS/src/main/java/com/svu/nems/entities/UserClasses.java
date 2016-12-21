/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sameer
 */
@Entity
@Table(name = "userClasses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserClasses.findAll", query = "SELECT u FROM UserClasses u")
    , @NamedQuery(name = "UserClasses.findById", query = "SELECT u FROM UserClasses u WHERE u.id = :id")
    , @NamedQuery(name = "UserClasses.findByActive", query = "SELECT u FROM UserClasses u WHERE u.active = :active")})
public class UserClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grades gradeId;
    @JoinColumn(name = "classId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolClass classId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public UserClasses() {
    }

    public UserClasses(Integer id) {
        this.id = id;
    }

    public UserClasses(Integer id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Grades getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grades gradeId) {
        this.gradeId = gradeId;
    }

    public SchoolClass getClassId() {
        return classId;
    }

    public void setClassId(SchoolClass classId) {
        this.classId = classId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof UserClasses)) {
            return false;
        }
        UserClasses other = (UserClasses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.UserClasses[ id=" + id + " ]";
    }
    
}

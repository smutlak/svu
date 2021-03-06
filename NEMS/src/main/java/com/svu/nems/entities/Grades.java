/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sameer
 */
@Entity
@Table(name = "Grades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grades.findAll", query = "SELECT g FROM Grades g")
    , @NamedQuery(name = "Grades.findById", query = "SELECT g FROM Grades g WHERE g.id = :id")
    , @NamedQuery(name = "Grades.findByName", query = "SELECT g FROM Grades g WHERE g.name = :name")
    , @NamedQuery(name = "Grades.findBySeq", query = "SELECT g FROM Grades g WHERE g.seq = :seq")
    , @NamedQuery(name = "Grades.findByActive", query = "SELECT g FROM Grades g WHERE g.active = :active")})
public class Grades implements Serializable {

    @JoinColumn(name = "schoolTypeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolTypes schoolTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeId")
    private Collection<Subject> subjectCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeId")
    private Collection<SchoolClass> schoolClassCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gradeId")
    private Collection<UserClasses> userClassesCollection;
    
    

    public Grades() {
    }

    public Grades(Integer id) {
        this.id = id;
    }

    public Grades(Integer id, String name, int seq, boolean active) {
        this.id = id;
        this.name = name;
        this.seq = seq;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<SchoolClass> getSchoolClassCollection() {
        return schoolClassCollection;
    }

    public void setSchoolClassCollection(Collection<SchoolClass> schoolClassCollection) {
        this.schoolClassCollection = schoolClassCollection;
    }

    @XmlTransient
    public Collection<UserClasses> getUserClassesCollection() {
        return userClassesCollection;
    }

    public void setUserClassesCollection(Collection<UserClasses> userClassesCollection) {
        this.userClassesCollection = userClassesCollection;
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
        if (!(object instanceof Grades)) {
            return false;
        }
        Grades other = (Grades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.Grades[ id=" + id + " ]";
    }

    public SchoolTypes getSchoolTypeId() {
        return schoolTypeId;
    }

    public void setSchoolTypeId(SchoolTypes schoolTypeId) {
        this.schoolTypeId = schoolTypeId;
    }

    @XmlTransient
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }

}

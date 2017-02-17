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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sameer
 */
@Entity
@Table(name = "schoolClass")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolClass.findAll", query = "SELECT s FROM SchoolClass s")
    , @NamedQuery(name = "SchoolClass.findById", query = "SELECT s FROM SchoolClass s WHERE s.id = :id")
    , @NamedQuery(name = "SchoolClass.findBySeq", query = "SELECT s FROM SchoolClass s WHERE s.seq = :seq")
    , @NamedQuery(name = "SchoolClass.findByAcademicYear", query = "SELECT s FROM SchoolClass s WHERE s.academicYear = :academicYear")
    , @NamedQuery(name = "SchoolClass.findByActive", query = "SELECT s FROM SchoolClass s WHERE s.active = :active")})
public class SchoolClass implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "academicYear")
    private int academicYear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolClassId")
    private Collection<ClassSubjects> classSubjectsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
  
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<Sessions> sessionsCollection;
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grades gradeId;
    @JoinColumn(name = "schoolId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private School schoolId;
    
    

    public SchoolClass() {
    }

    public SchoolClass(Integer id) {
        this.id = id;
    }

    public SchoolClass(Integer id, int seq, int academicYear, boolean active) {
        this.id = id;
        this.seq = seq;
        this.academicYear = academicYear;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Sessions> getSessionsCollection() {
        return sessionsCollection;
    }

    public void setSessionsCollection(Collection<Sessions> sessionsCollection) {
        this.sessionsCollection = sessionsCollection;
    }

    public Grades getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grades gradeId) {
        this.gradeId = gradeId;
    }

    public School getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(School schoolId) {
        this.schoolId = schoolId;
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
        if (!(object instanceof SchoolClass)) {
            return false;
        }
        SchoolClass other = (SchoolClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.SchoolClass[ id=" + id + " ]";
    }

    
    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
    }
    
}

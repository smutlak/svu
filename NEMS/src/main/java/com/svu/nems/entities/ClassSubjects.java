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
@Table(name = "ClassSubjects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassSubjects.findAll", query = "SELECT c FROM ClassSubjects c")
    , @NamedQuery(name = "ClassSubjects.findById", query = "SELECT c FROM ClassSubjects c WHERE c.id = :id")
    , @NamedQuery(name = "ClassSubjects.findByTeacherId", query = "SELECT c FROM ClassSubjects c WHERE c.teacherId = :teacherId")})
public class ClassSubjects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "teacherId")
    private int teacherId;
    @JoinColumn(name = "schoolClassId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolClass schoolClassId;
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;

    public ClassSubjects() {
    }

    public ClassSubjects(Integer id) {
        this.id = id;
    }

    public ClassSubjects(Integer id, int teacherId) {
        this.id = id;
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public SchoolClass getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(SchoolClass schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
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
        if (!(object instanceof ClassSubjects)) {
            return false;
        }
        ClassSubjects other = (ClassSubjects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.ClassSubjects[ id=" + id + " ]";
    }
    
}

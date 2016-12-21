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
@Table(name = "gradeSubjects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GradeSubjects.findAll", query = "SELECT g FROM GradeSubjects g")
    , @NamedQuery(name = "GradeSubjects.findById", query = "SELECT g FROM GradeSubjects g WHERE g.id = :id")})
public class GradeSubjects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grades gradeId;
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;

    public GradeSubjects() {
    }

    public GradeSubjects(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Grades getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grades gradeId) {
        this.gradeId = gradeId;
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
        if (!(object instanceof GradeSubjects)) {
            return false;
        }
        GradeSubjects other = (GradeSubjects) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.GradeSubjects[ id=" + id + " ]";
    }
    
}

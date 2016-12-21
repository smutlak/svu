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
@Table(name = "teachingPlan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeachingPlan.findAll", query = "SELECT t FROM TeachingPlan t")
    , @NamedQuery(name = "TeachingPlan.findById", query = "SELECT t FROM TeachingPlan t WHERE t.id = :id")
    , @NamedQuery(name = "TeachingPlan.findByName", query = "SELECT t FROM TeachingPlan t WHERE t.name = :name")
    , @NamedQuery(name = "TeachingPlan.findByAcademicYear", query = "SELECT t FROM TeachingPlan t WHERE t.academicYear = :academicYear")
    , @NamedQuery(name = "TeachingPlan.findBySemester", query = "SELECT t FROM TeachingPlan t WHERE t.semester = :semester")
    , @NamedQuery(name = "TeachingPlan.findByActive", query = "SELECT t FROM TeachingPlan t WHERE t.active = :active")})
public class TeachingPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "academicYear")
    private int academicYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teachingPlanId")
    private Collection<PlannedSession> plannedSessionCollection;
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;

    public TeachingPlan() {
    }

    public TeachingPlan(Integer id) {
        this.id = id;
    }

    public TeachingPlan(Integer id, String name, int academicYear, int semester, boolean active) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
        this.semester = semester;
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

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<PlannedSession> getPlannedSessionCollection() {
        return plannedSessionCollection;
    }

    public void setPlannedSessionCollection(Collection<PlannedSession> plannedSessionCollection) {
        this.plannedSessionCollection = plannedSessionCollection;
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
        if (!(object instanceof TeachingPlan)) {
            return false;
        }
        TeachingPlan other = (TeachingPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.TeachingPlan[ id=" + id + " ]";
    }
    
}

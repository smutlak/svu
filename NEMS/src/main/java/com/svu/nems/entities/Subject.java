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
@Table(name = "subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s")
    , @NamedQuery(name = "Subject.findById", query = "SELECT s FROM Subject s WHERE s.id = :id")
    , @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name")
    , @NamedQuery(name = "Subject.findByActive", query = "SELECT s FROM Subject s WHERE s.active = :active")})
public class Subject implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<ClassSubjects> classSubjectsCollection;

    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grades gradeId;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "active")
    private boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<Sessions> sessionsCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<TeachingPlan> teachingPlanCollection;
    
   /* @JoinColumn(name = "dirictorId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users dirictorId;
    */
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<SubjectDirector> subjectDirectorCollection;

    public Subject() {
    }

    public Subject(Integer id) {
        this.id = id;
    }

    public Subject(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public Collection<TeachingPlan> getTeachingPlanCollection() {
        return teachingPlanCollection;
    }

    public void setTeachingPlanCollection(Collection<TeachingPlan> teachingPlanCollection) {
        this.teachingPlanCollection = teachingPlanCollection;
    }
/*
    public Users getDirictorId() {
        return dirictorId;
    }

    public void setDirictorId(Users dirictorId) {
        this.dirictorId = dirictorId;
    }
*/
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.Subject[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<SubjectDirector> getSubjectDirectorCollection() {
        return subjectDirectorCollection;
    }

    public void setSubjectDirectorCollection(Collection<SubjectDirector> subjectDirectorCollection) {
        this.subjectDirectorCollection = subjectDirectorCollection;
    }

    public Grades getGradeId() {
        return gradeId;
    }

    public void setGradeId(Grades gradeId) {
        this.gradeId = gradeId;
    }

    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
    }

}

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
@Table(name = "schoolTypeGrades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolTypeGrades.findAll", query = "SELECT s FROM SchoolTypeGrades s")
    , @NamedQuery(name = "SchoolTypeGrades.findById", query = "SELECT s FROM SchoolTypeGrades s WHERE s.id = :id")})
public class SchoolTypeGrades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "gradeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grades gradeId;
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolTypes typeId;

    public SchoolTypeGrades() {
    }

    public SchoolTypeGrades(Integer id) {
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

    public SchoolTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(SchoolTypes typeId) {
        this.typeId = typeId;
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
        if (!(object instanceof SchoolTypeGrades)) {
            return false;
        }
        SchoolTypeGrades other = (SchoolTypeGrades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.SchoolTypeGrades[ id=" + id + " ]";
    }
    
}

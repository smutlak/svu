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
@Table(name = "schoolTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolTypes.findAll", query = "SELECT s FROM SchoolTypes s")
    , @NamedQuery(name = "SchoolTypes.findById", query = "SELECT s FROM SchoolTypes s WHERE s.id = :id")
    , @NamedQuery(name = "SchoolTypes.findByName", query = "SELECT s FROM SchoolTypes s WHERE s.name = :name")
    , @NamedQuery(name = "SchoolTypes.findByActive", query = "SELECT s FROM SchoolTypes s WHERE s.active = :active")})
public class SchoolTypes implements Serializable {

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
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<School> schoolCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    private Collection<SchoolTypeGrades> schoolTypeGradesCollection;

    public SchoolTypes() {
    }

    public SchoolTypes(Integer id) {
        this.id = id;
    }

    public SchoolTypes(Integer id, String name, boolean active) {
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
    public Collection<School> getSchoolCollection() {
        return schoolCollection;
    }

    public void setSchoolCollection(Collection<School> schoolCollection) {
        this.schoolCollection = schoolCollection;
    }

    @XmlTransient
    public Collection<SchoolTypeGrades> getSchoolTypeGradesCollection() {
        return schoolTypeGradesCollection;
    }

    public void setSchoolTypeGradesCollection(Collection<SchoolTypeGrades> schoolTypeGradesCollection) {
        this.schoolTypeGradesCollection = schoolTypeGradesCollection;
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
        if (!(object instanceof SchoolTypes)) {
            return false;
        }
        SchoolTypes other = (SchoolTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.SchoolTypes[ id=" + id + " ]";
    }
    
}

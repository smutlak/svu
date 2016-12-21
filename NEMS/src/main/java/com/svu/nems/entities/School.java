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
@Table(name = "School")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findById", query = "SELECT s FROM School s WHERE s.id = :id")
    , @NamedQuery(name = "School.findByAbbreviation", query = "SELECT s FROM School s WHERE s.abbreviation = :abbreviation")
    , @NamedQuery(name = "School.findByActive", query = "SELECT s FROM School s WHERE s.active = :active")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "abbreviation")
    private String abbreviation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolId")
    private Collection<SchoolClass> schoolClassCollection;
    @JoinColumn(name = "districtId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private District districtId;
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolTypes typeId;
    @JoinColumn(name = "principalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users principalId;

    public School() {
    }

    public School(Integer id) {
        this.id = id;
    }

    public School(Integer id, String abbreviation, boolean active) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
    }

    public SchoolTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(SchoolTypes typeId) {
        this.typeId = typeId;
    }

    public Users getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Users principalId) {
        this.principalId = principalId;
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
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.School[ id=" + id + " ]";
    }
    
}

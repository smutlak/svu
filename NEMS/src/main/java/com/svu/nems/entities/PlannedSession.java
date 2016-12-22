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
@Table(name = "plannedSession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlannedSession.findAll", query = "SELECT p FROM PlannedSession p")
    , @NamedQuery(name = "PlannedSession.findById", query = "SELECT p FROM PlannedSession p WHERE p.id = :id")
    , @NamedQuery(name = "PlannedSession.findByName", query = "SELECT p FROM PlannedSession p WHERE p.name = :name")
    , @NamedQuery(name = "PlannedSession.findBySeq", query = "SELECT p FROM PlannedSession p WHERE p.seq = :seq")
    , @NamedQuery(name = "PlannedSession.findByDescription", query = "SELECT p FROM PlannedSession p WHERE p.description = :description")
    , @NamedQuery(name = "PlannedSession.findByWeekNo", query = "SELECT p FROM PlannedSession p WHERE p.weekNo = :weekNo")})
public class PlannedSession implements Serializable {

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
    @Column(name = "seq")
    private int seq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weekNo")
    private int weekNo;
    @JoinColumn(name = "teachingPlanId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TeachingPlan teachingPlanId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plannedSessionId")
    private Collection<Sessions> sessionsCollection;

    public PlannedSession() {
    }

    public PlannedSession(Integer id) {
        this.id = id;
    }

    public PlannedSession(Integer id, String name, int seq, String description, int weekNo) {
        this.id = id;
        this.name = name;
        this.seq = seq;
        this.description = description;
        this.weekNo = weekNo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public TeachingPlan getTeachingPlanId() {
        return teachingPlanId;
    }

    public void setTeachingPlanId(TeachingPlan teachingPlanId) {
        this.teachingPlanId = teachingPlanId;
    }

    @XmlTransient
    public Collection<Sessions> getSessionsCollection() {
        return sessionsCollection;
    }

    public void setSessionsCollection(Collection<Sessions> sessionsCollection) {
        this.sessionsCollection = sessionsCollection;
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
        if (!(object instanceof PlannedSession)) {
            return false;
        }
        PlannedSession other = (PlannedSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.PlannedSession[ id=" + id + " ]";
    }
    
}

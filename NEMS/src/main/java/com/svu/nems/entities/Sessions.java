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
@Table(name = "sessions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sessions.findAll", query = "SELECT s FROM Sessions s")
    , @NamedQuery(name = "Sessions.findById", query = "SELECT s FROM Sessions s WHERE s.id = :id")
    , @NamedQuery(name = "Sessions.findByDescription", query = "SELECT s FROM Sessions s WHERE s.description = :description")
    , @NamedQuery(name = "Sessions.findByNote", query = "SELECT s FROM Sessions s WHERE s.note = :note")
    , @NamedQuery(name = "Sessions.findByActive", query = "SELECT s FROM Sessions s WHERE s.active = :active")})
public class Sessions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "plannedSessionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PlannedSession plannedSessionId;
    @JoinColumn(name = "classId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SchoolClass classId;
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subjectId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private Collection<Absence> absenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private Collection<Assignment> assignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionId")
    private Collection<SessionMedia> sessionMediaCollection;

    public Sessions() {
    }

    public Sessions(Integer id) {
        this.id = id;
    }

    public Sessions(Integer id, String description, String note, boolean active) {
        this.id = id;
        this.description = description;
        this.note = note;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PlannedSession getPlannedSessionId() {
        return plannedSessionId;
    }

    public void setPlannedSessionId(PlannedSession plannedSessionId) {
        this.plannedSessionId = plannedSessionId;
    }

    public SchoolClass getClassId() {
        return classId;
    }

    public void setClassId(SchoolClass classId) {
        this.classId = classId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    @XmlTransient
    public Collection<Absence> getAbsenceCollection() {
        return absenceCollection;
    }

    public void setAbsenceCollection(Collection<Absence> absenceCollection) {
        this.absenceCollection = absenceCollection;
    }

    @XmlTransient
    public Collection<Assignment> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(Collection<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<SessionMedia> getSessionMediaCollection() {
        return sessionMediaCollection;
    }

    public void setSessionMediaCollection(Collection<SessionMedia> sessionMediaCollection) {
        this.sessionMediaCollection = sessionMediaCollection;
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
        if (!(object instanceof Sessions)) {
            return false;
        }
        Sessions other = (Sessions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.Sessions[ id=" + id + " ]";
    }
    
}

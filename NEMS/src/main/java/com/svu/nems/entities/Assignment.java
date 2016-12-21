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
import javax.persistence.Lob;
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
@Table(name = "assignment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a")
    , @NamedQuery(name = "Assignment.findById", query = "SELECT a FROM Assignment a WHERE a.id = :id")
    , @NamedQuery(name = "Assignment.findByOrder", query = "SELECT a FROM Assignment a WHERE a.order = :order")
    , @NamedQuery(name = "Assignment.findByContentType", query = "SELECT a FROM Assignment a WHERE a.contentType = :contentType")
    , @NamedQuery(name = "Assignment.findByContentMetaData", query = "SELECT a FROM Assignment a WHERE a.contentMetaData = :contentMetaData")
    , @NamedQuery(name = "Assignment.findByPath", query = "SELECT a FROM Assignment a WHERE a.path = :path")
    , @NamedQuery(name = "Assignment.findByActive", query = "SELECT a FROM Assignment a WHERE a.active = :active")})
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order")
    private int order;
    @Lob
    @Column(name = "content")
    private byte[] content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "contentType")
    private String contentType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "contentMetaData")
    private String contentMetaData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "sessionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sessions sessionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignmentId")
    private Collection<AssignmentMark> assignmentMarkCollection;

    public Assignment() {
    }

    public Assignment(Integer id) {
        this.id = id;
    }

    public Assignment(Integer id, int order, String contentType, String contentMetaData, String path, boolean active) {
        this.id = id;
        this.order = order;
        this.contentType = contentType;
        this.contentMetaData = contentMetaData;
        this.path = path;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentMetaData() {
        return contentMetaData;
    }

    public void setContentMetaData(String contentMetaData) {
        this.contentMetaData = contentMetaData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Sessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(Sessions sessionId) {
        this.sessionId = sessionId;
    }

    @XmlTransient
    public Collection<AssignmentMark> getAssignmentMarkCollection() {
        return assignmentMarkCollection;
    }

    public void setAssignmentMarkCollection(Collection<AssignmentMark> assignmentMarkCollection) {
        this.assignmentMarkCollection = assignmentMarkCollection;
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
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment other = (Assignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.Assignment[ id=" + id + " ]";
    }
    
}

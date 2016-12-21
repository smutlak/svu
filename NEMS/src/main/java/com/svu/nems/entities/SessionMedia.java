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
@Table(name = "sessionMedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SessionMedia.findAll", query = "SELECT s FROM SessionMedia s")
    , @NamedQuery(name = "SessionMedia.findById", query = "SELECT s FROM SessionMedia s WHERE s.id = :id")
    , @NamedQuery(name = "SessionMedia.findByOrder", query = "SELECT s FROM SessionMedia s WHERE s.order = :order")
    , @NamedQuery(name = "SessionMedia.findByContentType", query = "SELECT s FROM SessionMedia s WHERE s.contentType = :contentType")
    , @NamedQuery(name = "SessionMedia.findByContentMetaData", query = "SELECT s FROM SessionMedia s WHERE s.contentMetaData = :contentMetaData")
    , @NamedQuery(name = "SessionMedia.findByPath", query = "SELECT s FROM SessionMedia s WHERE s.path = :path")
    , @NamedQuery(name = "SessionMedia.findByActive", query = "SELECT s FROM SessionMedia s WHERE s.active = :active")})
public class SessionMedia implements Serializable {

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
    @Size(max = 256)
    @Column(name = "contentType")
    private String contentType;
    @Size(max = 256)
    @Column(name = "contentMetaData")
    private String contentMetaData;
    @Size(max = 256)
    @Column(name = "path")
    private String path;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionMediaId")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "sessionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sessions sessionId;

    public SessionMedia() {
    }

    public SessionMedia(Integer id) {
        this.id = id;
    }

    public SessionMedia(Integer id, int order, boolean active) {
        this.id = id;
        this.order = order;
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

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Sessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(Sessions sessionId) {
        this.sessionId = sessionId;
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
        if (!(object instanceof SessionMedia)) {
            return false;
        }
        SessionMedia other = (SessionMedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.SessionMedia[ id=" + id + " ]";
    }
    
}

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
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id")
    , @NamedQuery(name = "Comment.findByType", query = "SELECT c FROM Comment c WHERE c.type = :type")
    , @NamedQuery(name = "Comment.findByText", query = "SELECT c FROM Comment c WHERE c.text = :text")
    , @NamedQuery(name = "Comment.findByActive", query = "SELECT c FROM Comment c WHERE c.active = :active")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentCommentId")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "parentCommentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Comment parentCommentId;
    @JoinColumn(name = "sessionMediaId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SessionMedia sessionMediaId;
    @JoinColumn(name = "sessionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sessions sessionId;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public Comment() {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Comment(Integer id, String type, String text, boolean active) {
        this.id = id;
        this.type = type;
        this.text = text;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Comment getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Comment parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public SessionMedia getSessionMediaId() {
        return sessionMediaId;
    }

    public void setSessionMediaId(SessionMedia sessionMediaId) {
        this.sessionMediaId = sessionMediaId;
    }

    public Sessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(Sessions sessionId) {
        this.sessionId = sessionId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.Comment[ id=" + id + " ]";
    }
    
}

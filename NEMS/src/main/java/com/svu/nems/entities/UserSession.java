/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sameer
 */
@Entity
@Table(name = "userSession")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSession.findAll", query = "SELECT u FROM UserSession u")
    , @NamedQuery(name = "UserSession.findById", query = "SELECT u FROM UserSession u WHERE u.id = :id")
    , @NamedQuery(name = "UserSession.findByStartDateTime", query = "SELECT u FROM UserSession u WHERE u.startDateTime = :startDateTime")
    , @NamedQuery(name = "UserSession.findByEndDateTime", query = "SELECT u FROM UserSession u WHERE u.endDateTime = :endDateTime")
    , @NamedQuery(name = "UserSession.findByLoggedInFrom", query = "SELECT u FROM UserSession u WHERE u.loggedInFrom = :loggedInFrom")})
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "loggedInFrom")
    private String loggedInFrom;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public UserSession() {
    }

    public UserSession(Integer id) {
        this.id = id;
    }

    public UserSession(Integer id, Date startDateTime, Date endDateTime, String loggedInFrom) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.loggedInFrom = loggedInFrom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getLoggedInFrom() {
        return loggedInFrom;
    }

    public void setLoggedInFrom(String loggedInFrom) {
        this.loggedInFrom = loggedInFrom;
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
        if (!(object instanceof UserSession)) {
            return false;
        }
        UserSession other = (UserSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.UserSession[ id=" + id + " ]";
    }
    
}

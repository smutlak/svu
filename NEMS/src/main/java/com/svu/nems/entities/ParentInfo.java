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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sameer
 */
@Entity
@Table(name = "parentInfo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentInfo.findAll", query = "SELECT p FROM ParentInfo p")
    , @NamedQuery(name = "ParentInfo.findById", query = "SELECT p FROM ParentInfo p WHERE p.id = :id")
    , @NamedQuery(name = "ParentInfo.findByFName", query = "SELECT p FROM ParentInfo p WHERE p.fName = :fName")
    , @NamedQuery(name = "ParentInfo.findByMName", query = "SELECT p FROM ParentInfo p WHERE p.mName = :mName")
    , @NamedQuery(name = "ParentInfo.findByLName", query = "SELECT p FROM ParentInfo p WHERE p.lName = :lName")
    , @NamedQuery(name = "ParentInfo.findByAddress", query = "SELECT p FROM ParentInfo p WHERE p.address = :address")
    , @NamedQuery(name = "ParentInfo.findByEmail", query = "SELECT p FROM ParentInfo p WHERE p.email = :email")
    , @NamedQuery(name = "ParentInfo.findByJob", query = "SELECT p FROM ParentInfo p WHERE p.job = :job")
    , @NamedQuery(name = "ParentInfo.findByPhone", query = "SELECT p FROM ParentInfo p WHERE p.phone = :phone")
    , @NamedQuery(name = "ParentInfo.findByMobilePhone", query = "SELECT p FROM ParentInfo p WHERE p.mobilePhone = :mobilePhone")
    , @NamedQuery(name = "ParentInfo.findByActive", query = "SELECT p FROM ParentInfo p WHERE p.active = :active")})
public class ParentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "fName")
    private String fName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "mName")
    private String mName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "lName")
    private String lName;
    @Size(max = 1073741823)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "email")
    private String email;
    @Size(max = 256)
    @Column(name = "job")
    private String job;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "mobilePhone")
    private String mobilePhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;

    public ParentInfo() {
    }

    public ParentInfo(Integer id) {
        this.id = id;
    }

    public ParentInfo(Integer id, String fName, String mName, String lName, String email, String mobilePhone, boolean active) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        if (!(object instanceof ParentInfo)) {
            return false;
        }
        ParentInfo other = (ParentInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.ParentInfo[ id=" + id + " ]";
    }
    
}

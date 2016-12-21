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
@Table(name = "assignmentMark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssignmentMark.findAll", query = "SELECT a FROM AssignmentMark a")
    , @NamedQuery(name = "AssignmentMark.findById", query = "SELECT a FROM AssignmentMark a WHERE a.id = :id")
    , @NamedQuery(name = "AssignmentMark.findByMaek", query = "SELECT a FROM AssignmentMark a WHERE a.maek = :maek")})
public class AssignmentMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "maek")
    private double maek;
    @JoinColumn(name = "assignmentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Assignment assignmentId;
    @JoinColumn(name = "studentId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users studentId;

    public AssignmentMark() {
    }

    public AssignmentMark(Integer id) {
        this.id = id;
    }

    public AssignmentMark(Integer id, double maek) {
        this.id = id;
        this.maek = maek;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMaek() {
        return maek;
    }

    public void setMaek(double maek) {
        this.maek = maek;
    }

    public Assignment getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Assignment assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Users getStudentId() {
        return studentId;
    }

    public void setStudentId(Users studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof AssignmentMark)) {
            return false;
        }
        AssignmentMark other = (AssignmentMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.svu.nems.entities.AssignmentMark[ id=" + id + " ]";
    }
    
}

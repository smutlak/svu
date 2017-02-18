/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.sessionBeans;

import com.svu.nems.entities.Grades;
import com.svu.nems.entities.School;
import com.svu.nems.entities.SchoolClass;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sameer
 */
@Stateless
public class SchoolClassFacade extends AbstractFacade<SchoolClass> {

    @PersistenceContext(unitName = "com.svu_NEMS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SchoolClassFacade() {
        super(SchoolClass.class);
    }

    public Integer findMaxSeq(School school, Grades grade) {
        try {
            // TypedQuery<SchoolClass> q = em.createQuery("SELECT u FROM Users u WHERE u.userName = :userName and u.encryptedPsw = :encryptedPsw", SchoolClass.class);
            Query q = em.createNativeQuery("Select max(seq) from schoolClass where schoolId="
                    + school.getId() + " and gradeId=" + grade.getId());
            return (Integer)q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.sessionBeans;

import com.svu.nems.entities.AssignmentMark;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sameer
 */
@Stateless
public class AssignmentMarkFacade extends AbstractFacade<AssignmentMark> {

    @PersistenceContext(unitName = "com.svu_NEMS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssignmentMarkFacade() {
        super(AssignmentMark.class);
    }
    
}

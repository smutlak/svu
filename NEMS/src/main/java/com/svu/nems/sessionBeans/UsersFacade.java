/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.sessionBeans;

import com.svu.nems.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sameer
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "com.svu_NEMS_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public Users login(String userName, String psw) {
      
        try {
            TypedQuery<Users> q = em.createQuery("SELECT u FROM Users u WHERE u.userName = :userName and u.encryptedPsw = :encryptedPsw", Users.class);
            q.setParameter("userName", userName);
            q.setParameter("encryptedPsw", psw);
            return q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

}

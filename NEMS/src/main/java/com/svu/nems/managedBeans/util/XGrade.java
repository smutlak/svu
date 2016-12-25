/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.managedBeans.util;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sameer
 */
public class XGrade implements Serializable {
    
    private String name;
    private List<XSubject> subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XSubject> getSubject() {
        return subject;
    }

    public void setSubject(List<XSubject> subject) {
        this.subject = subject;
    }
    
    
}

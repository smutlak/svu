/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.managedBeans.util;

import java.io.Serializable;

/**
 *
 * @author Sameer
 */
public class XSubject implements Serializable{
    
    private String name;

    public XSubject() {
    }

    public XSubject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

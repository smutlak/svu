/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svu.nems.managedBeans;

import com.svu.nems.entities.UserRoles;
import com.svu.nems.entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sameer
 */
@Named(value = "mainBean")
@SessionScoped
public class mainBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uname;
    private String password;
    private boolean changePsw;
    private String newPassword;
    private String confirmNewPassword;

    @EJB
    private com.svu.nems.sessionBeans.UsersFacade usersFacade;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public boolean isChangePsw() {
        return changePsw;
    }

    public void setChangePsw(boolean changePsw) {
        this.changePsw = changePsw;
    }

    /**
     * Creates a new instance of mainBean
     */
    public mainBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String loginProject() {
        Users user= usersFacade.login(uname, password);
        
        //boolean result = true;//UserDAO.login(uname, password);
        if (user != null) {
            Collection<UserRoles> userRoles = user.getUserRolesCollection();
            return "home";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Please Try Again!"));
            return "index";
        }
    }
}

package com.svu.nems.managedBeans;

import com.svu.nems.entities.Role;
import com.svu.nems.entities.UserRoles;
import com.svu.nems.entities.Users;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.sessionBeans.UsersFacade;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.PhaseId;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("usersController")
@SessionScoped
public class UsersController implements Serializable {

    @EJB
    private com.svu.nems.sessionBeans.UsersFacade ejbFacade;
    @EJB
    private com.svu.nems.sessionBeans.RoleFacade roleFacade;
    private List<Users> items = null;
    private Users selected;
    private UploadedFile photo;
    private Role selectedRole;

    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public UsersController() {
    }

    public Users getSelected() {
        return selected;
    }

    public void setSelected(Users selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsersFacade getFacade() {
        return ejbFacade;
    }

    public Users prepareCreate() {
        selected = new Users();
        initializeEmbeddableKey();
        return selected;
    }

    public void resetSelected(){
        this.selected = null;
    }
    public void create() throws IOException {
        UserRoles r = new UserRoles();
        r.setRoleId(selectedRole);
        r.setUserId(selected);
        ArrayList<UserRoles> userRoles = new ArrayList();
        userRoles.add(r);
        selected.setUserRolesCollection(userRoles);
        selected.setForcePswChange(true);
        selected.setActive(true);

        long size = photo.getSize();
        InputStream stream = photo.getInputstream();
        byte[] buffer = new byte[(int) size];
        stream.read(buffer, 0, (int) size);
        stream.close();
        selected.setPhoto(buffer);

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        photo = null;
    }

    public void update() throws IOException {
        /*UserRoles r = new UserRoles();
        r.setRoleId(selectedRole);
        r.setUserId(selected);
        ArrayList<UserRoles> userRoles = new ArrayList();
        userRoles.add(r);
        selected.setUserRolesCollection(userRoles);*/

        if (photo != null) {
            long size = photo.getSize();
            InputStream stream = photo.getInputstream();
            byte[] buffer = new byte[(int) size];
            stream.read(buffer, 0, (int) size);
            stream.close();
            selected.setPhoto(buffer);
        }
        
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsersUpdated"));
        photo = null;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsersDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Users> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
                } else if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                {
                    Exception cause = ex.getCausedByException();
                    if (cause instanceof ConstraintViolationException) {
                        @SuppressWarnings("ThrowableResultIgnored")
                        ConstraintViolationException cve = (ConstraintViolationException) ex.getCausedByException();
                        for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                            ConstraintViolation<? extends Object> v = it.next();
                            System.err.println(v);
                            System.err.println("==>>" + v.getMessage());
                        }
                    }
                }
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Users getUsers(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Users> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Users> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Users.class)
    public static class UsersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsersController controller = (UsersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usersController");
            return controller.getUsers(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Users) {
                Users o = (Users) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Users.class.getName()});
                return null;
            }
        }

    }

    public UploadedFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadedFile photo) {
        this.photo = photo;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        photo = event.getFile();
        //selected.setPhoto(photo.getContents());

        /*FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);*/
    }

    public StreamedContent getSelectedImage() throws IOException {
        if (photo != null) {
            return new DefaultStreamedContent(photo.getInputstream(), "image/jpg");
        }
        if (selected.getPhoto() != null) {
            return new DefaultStreamedContent(new ByteArrayInputStream(selected.getPhoto()));
        } else {
            return null;
        }
    }

    public StreamedContent getUserImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            byte[] userPhoto = null;
            Integer id = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("id"));
            for (Users user : items) {
                if (user.getId().equals(id)) {
                    userPhoto = user.getPhoto();
                    break;
                }
            }
            if (userPhoto != null) {
                return new DefaultStreamedContent(new ByteArrayInputStream(userPhoto));
            }

            return new DefaultStreamedContent();
        }
    }

    public List<Role> getRoles() {
        List<Role> roles = roleFacade.findAll();
        return roles;
    }
}

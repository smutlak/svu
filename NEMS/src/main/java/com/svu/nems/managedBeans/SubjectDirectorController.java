package com.svu.nems.managedBeans;

import com.svu.nems.entities.Grades;
import com.svu.nems.entities.Subject;
import com.svu.nems.entities.SubjectDirector;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.sessionBeans.SubjectDirectorFacade;

import java.io.Serializable;
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

@Named("subjectDirectorController")
@SessionScoped
public class SubjectDirectorController implements Serializable {

    @EJB
    private com.svu.nems.sessionBeans.SubjectDirectorFacade ejbFacade;

    private Grades selectedGrade;
    private List<SubjectDirector> items = null;
    private SubjectDirector selected;

    public SubjectDirectorController() {
    }

    public SubjectDirector getSelected() {
        return selected;
    }

    public void setSelected(SubjectDirector selected) {
        this.selected = selected;
        if (this.selected != null) {
            if (this.selected.getSubjectId() != null) {
                this.selectedGrade = this.selected.getSubjectId().getGradeId();
            }
        }
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SubjectDirectorFacade getFacade() {
        return ejbFacade;
    }

    public SubjectDirector prepareCreate() {
        selected = new SubjectDirector();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SubjectCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SubjectUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SubjectDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SubjectDirector> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
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

    public SubjectDirector getSubject(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SubjectDirector> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SubjectDirector> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SubjectDirector.class)
    public static class SubjectDirectorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubjectDirectorController controller = (SubjectDirectorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subjectDirectorController");
            return controller.getSubject(getKey(value));
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
            if (object instanceof SubjectDirector) {
                Subject o = (Subject) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Subject.class.getName()});
                return null;
            }
        }

    }

    public Grades getSelectedGrade() {
        return selectedGrade;
    }

    public void setSelectedGrade(Grades selectedGrade) {
        this.selectedGrade = selectedGrade;
    }

}

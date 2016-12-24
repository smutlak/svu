package com.svu.nems.managedBeans;

import com.svu.nems.entities.Grades;
import com.svu.nems.entities.SchoolTypeGrades;
import com.svu.nems.entities.SchoolTypes;
import com.svu.nems.entities.Subject;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.sessionBeans.SchoolTypesFacade;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("schoolTypesController")
@SessionScoped
public class SchoolTypesController implements Serializable {

    @EJB
    private com.svu.nems.sessionBeans.SchoolTypesFacade ejbFacade;
    private List<SchoolTypes> items = null;
    private SchoolTypes selected;
    private List<SchoolTypeGrades> schoolTypeGrades;
    private SchoolTypeGrades selectedSchoolTypeGrade;
    private Subject selctedSubject;
    private String newGradeName; 

    public String getNewGradeName() {
        return newGradeName;
    }

    public void setNewGradeName(String newGradeName) {
        this.newGradeName = newGradeName;
    }

    
    public SchoolTypeGrades getSelectedSchoolTypeGrade() {
        return selectedSchoolTypeGrade;
    }

    public void setSelectedSchoolTypeGrade(SchoolTypeGrades selectedSchoolTypeGrade) {
        this.selectedSchoolTypeGrade = selectedSchoolTypeGrade;
    }

   

    public Subject getSelctedSubject() {
        return selctedSubject;
    }

    public void setSelctedSubject(Subject selctedSubject) {
        this.selctedSubject = selctedSubject;
    }

    public List<SchoolTypeGrades> getSchoolTypeGrades() {
        return schoolTypeGrades;
    }

    public void setSchoolTypeGrades(List<SchoolTypeGrades> schoolTypeGrades) {
        this.schoolTypeGrades = schoolTypeGrades;
    }
    
            
            
   

    public SchoolTypesController() {
    }

    public SchoolTypes getSelected() {
        return selected;
    }

    public void setSelected(SchoolTypes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SchoolTypesFacade getFacade() {
        return ejbFacade;
    }

    public SchoolTypes prepareCreate() {
        selected = new SchoolTypes();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SchoolTypesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SchoolTypesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SchoolTypesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SchoolTypes> getItems() {
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

    public SchoolTypes getSchoolTypes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SchoolTypes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SchoolTypes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SchoolTypes.class)
    public static class SchoolTypesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SchoolTypesController controller = (SchoolTypesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "schoolTypesController");
            return controller.getSchoolTypes(getKey(value));
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
            if (object instanceof SchoolTypes) {
                SchoolTypes o = (SchoolTypes) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SchoolTypes.class.getName()});
                return null;
            }
        }

    }

    public void addSchoolTypeGrade(){
        if(schoolTypeGrades == null){
            schoolTypeGrades = new ArrayList();
        }
        SchoolTypeGrades sGrade = new SchoolTypeGrades();
        Grades grade = new Grades();
        grade.setName(newGradeName);
        sGrade.setGradeId(grade);
        schoolTypeGrades.add(sGrade);
    }
}

package com.svu.nems.managedBeans;

import com.svu.nems.entities.SchoolTypes;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.managedBeans.util.XGrade;
import com.svu.nems.managedBeans.util.XSubject;
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
    private List<XGrade> xgrades;
    private XGrade selectedXGrade;
    private XSubject selctedXSubject;
    private String newGradeName;
    private String newSchoolTypeName;
    

    public String getNewGradeName() {
        return newGradeName;
    }

    public void setNewGradeName(String newGradeName) {
        this.newGradeName = newGradeName;
    }

    

    @FacesConverter(forClass=XGrade.class)
    public static class SchoolTypeGradesControllerConverterX implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SchoolTypesController controller = (SchoolTypesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "schoolTypesController");
            List<XGrade> sGrades= controller.getXgrades();
            for(XGrade sGrade:sGrades){
                if(sGrade.getName().equals(value)){
                    return sGrade;
                }
            }
            return null;
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof XGrade) {
                XGrade o = (XGrade) object;
                return o.getName();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XGrade.class.getName()});
                return null;
            }
        }

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

    public void addSchoolTypeGrade() {
        if (xgrades == null) {
            xgrades = new ArrayList();
        }
        XGrade grade = new XGrade();
        grade.setName(newGradeName);
        xgrades.add(grade);
    }
    
    public void prepareAddSchoolTypeGrade(){
        this.newGradeName ="";
    }

    public void deleteSchoolTypeGrade() {
        if (this.selectedXGrade != null) {
            for (int i = 0; i < xgrades.size(); i++) {
                XGrade tGrade = xgrades.get(i);
                if (tGrade.getName().equalsIgnoreCase(
                        selectedXGrade.getName())) {
                    xgrades.remove(i);
                    break;
                }
            }
        }
    }

    public List<XGrade> getXgrades() {
        return xgrades;
    }

    public void setXgrades(List<XGrade> xgrades) {
        this.xgrades = xgrades;
    }

    

    public XGrade getSelectedXGrade() {
        return selectedXGrade;
    }

    public void setSelectedXGrade(XGrade selectedXGrade) {
        this.selectedXGrade = selectedXGrade;
    }

    public XSubject getSelctedXSubject() {
        return selctedXSubject;
    }

    public void setSelctedXSubject(XSubject selctedXSubject) {
        this.selctedXSubject = selctedXSubject;
    }

    public String getNewSchoolTypeName() {
        return newSchoolTypeName;
    }

    public void setNewSchoolTypeName(String newSchoolTypeName) {
        this.newSchoolTypeName = newSchoolTypeName;
    }

}

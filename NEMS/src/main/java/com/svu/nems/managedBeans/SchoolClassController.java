package com.svu.nems.managedBeans;

import com.svu.nems.entities.ClassSubjects;
import com.svu.nems.entities.SchoolClass;
import com.svu.nems.entities.Subject;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.sessionBeans.SchoolClassFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("schoolClassController")
@SessionScoped
public class SchoolClassController implements Serializable {

    @EJB
    private com.svu.nems.sessionBeans.SchoolClassFacade ejbFacade;
    private List<SchoolClass> items = null;
    private SchoolClass selected;

    public SchoolClassController() {
    }

    public SchoolClass getSelected() {
        return selected;
    }

    public void setSelected(SchoolClass selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SchoolClassFacade getFacade() {
        return ejbFacade;
    }

    public SchoolClass prepareCreate() {
        selected = new SchoolClass();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

        for (ClassSubjects clsSubject : this.selected.getClassSubjectsCollection()) {
            clsSubject.setSchoolClassId(selected);
        }
        this.selected.setSeq(this.getNextSeq());
        this.selected.setAcademicYear(Integer.parseInt(this.getCurrentAcademicYear().split(Pattern.quote("-"))[0]));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SchoolClassCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SchoolClassUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SchoolClassDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SchoolClass> getItems() {
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

    public SchoolClass getSchoolClass(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SchoolClass> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SchoolClass> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SchoolClass.class)
    public static class SchoolClassControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SchoolClassController controller = (SchoolClassController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "schoolClassController");
            return controller.getSchoolClass(getKey(value));
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
            if (object instanceof SchoolClass) {
                SchoolClass o = (SchoolClass) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SchoolClass.class.getName()});
                return null;
            }
        }

    }

    public void prepareClassSubjects() {
        List<ClassSubjects> classSubjectsCollection = new ArrayList();
        if (selected.getGradeId() != null) {
            for (Subject sub : selected.getGradeId().getSubjectCollection()) {
                ClassSubjects classSubject = new ClassSubjects();
                classSubject.setSubjectId(sub);
                classSubjectsCollection.add(classSubject);
            }
        }
        selected.setClassSubjectsCollection(classSubjectsCollection);
    }

    public String getCurrentAcademicYear() {
        String ret;
        Calendar cal = Calendar.getInstance();
        boolean nextYear = cal.get(Calendar.MONTH) > 4;
        int year = cal.get(Calendar.YEAR);
        if (nextYear) {
            ret = year + "-" + (year + 1);
        } else {
            ret = (year - 1) + "-" + year;
        }
        return ret;
    }

    public Integer getNextSeq() {
        if (this.selected.getSchoolId() != null && this.selected.getGradeId() != null) {
            Integer maxSeq = getFacade().findMaxSeq(this.selected.getSchoolId(), this.selected.getGradeId());
            if (maxSeq != null) {
                return maxSeq + 1;
            }
        }
        return 1;
    }
}

package com.svu.nems.managedBeans;

import com.svu.nems.entities.TeachingPlan;
import com.svu.nems.managedBeans.util.JsfUtil;
import com.svu.nems.managedBeans.util.JsfUtil.PersistAction;
import com.svu.nems.sessionBeans.TeachingPlanFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named("teachingPlanController")
@SessionScoped
public class TeachingPlanController implements Serializable {

    @EJB
    private com.svu.nems.sessionBeans.TeachingPlanFacade ejbFacade;
    private List<TeachingPlan> items = null;
    private TeachingPlan selected;

    private TreeNode fakeRoot;

    public TreeNode getFakeRoot() {
        return fakeRoot;
    }

    public void setFakeRoot(TreeNode fakeRoot) {
        this.fakeRoot = fakeRoot;
    }

   

    public TeachingPlanController() {

    }

    @PostConstruct
    public void init() {
        fakeRoot = new DefaultTreeNode("root", null);
        TreeNode root  = new DefaultTreeNode(this.getCurrentAcademicYear(), fakeRoot);
        root.setExpanded(true);
        //TreeNode August = new DefaultTreeNode("August", root);
        TreeNode September = new DefaultTreeNode("September", root);
        TreeNode SeptemberWeek1 = new DefaultTreeNode("First Week", September);
        TreeNode SeptemberWeek2 = new DefaultTreeNode("Second Week", September);
        TreeNode SeptemberWeek3 = new DefaultTreeNode("Third Week", September);
        TreeNode SeptemberWeek4 = new DefaultTreeNode("Forth Week", September);
        
        TreeNode October = new DefaultTreeNode("October", root);
        TreeNode OctoberWeek1 = new DefaultTreeNode("First Week", October);
        TreeNode OctoberWeek2 = new DefaultTreeNode("Second Week", October);
        TreeNode OctoberWeek3 = new DefaultTreeNode("Third Week", October);
        TreeNode OctoberWeek4 = new DefaultTreeNode("Forth Week", October);
        
        TreeNode November = new DefaultTreeNode("November", root);
        TreeNode NovemberWeek1 = new DefaultTreeNode("First Week", November);
        TreeNode NovemberWeek2 = new DefaultTreeNode("Second Week", November);
        TreeNode NovemberWeek3 = new DefaultTreeNode("Third Week", November);
        TreeNode NovemberWeek4 = new DefaultTreeNode("Forth Week", November);
        
        TreeNode January = new DefaultTreeNode("January", root);
        TreeNode JanuaryWeek1 = new DefaultTreeNode("First Week", January);
        TreeNode JanuaryWeek2 = new DefaultTreeNode("Second Week", January);
        TreeNode JanuaryWeek3 = new DefaultTreeNode("Third Week", January);
        TreeNode JanuaryWeek4 = new DefaultTreeNode("Forth Week", January);
        
        TreeNode February = new DefaultTreeNode("February", root);
        TreeNode FebruaryWeek1 = new DefaultTreeNode("First Week", February);
        TreeNode FebruaryWeek2 = new DefaultTreeNode("Second Week", February);
        TreeNode FebruaryWeek3 = new DefaultTreeNode("Third Week", February);
        TreeNode FebruaryWeek4 = new DefaultTreeNode("Forth Week", February);
        
        TreeNode March = new DefaultTreeNode("March", root);
        TreeNode MarchWeek1 = new DefaultTreeNode("First Week", March);
        TreeNode MarchWeek2 = new DefaultTreeNode("Second Week", March);
        TreeNode MarchWeek3 = new DefaultTreeNode("Third Week", March);
        TreeNode MarchWeek4 = new DefaultTreeNode("Forth Week", March);
        
        TreeNode April = new DefaultTreeNode("April", root);
        TreeNode AprilWeek1 = new DefaultTreeNode("First Week", April);
        TreeNode AprilWeek2 = new DefaultTreeNode("Second Week", April);
        TreeNode AprilWeek3 = new DefaultTreeNode("Third Week", April);
        TreeNode AprilWeek4 = new DefaultTreeNode("Forth Week", April);
        
        TreeNode May = new DefaultTreeNode("May", root);
        TreeNode MayWeek1 = new DefaultTreeNode("First Week", May);
        TreeNode MayWeek2 = new DefaultTreeNode("Second Week", May);
        TreeNode MayWeek3 = new DefaultTreeNode("Third Week", May);
        TreeNode MayWeek4 = new DefaultTreeNode("Forth Week", May);
        
        TreeNode June  = new DefaultTreeNode("June", root);
        TreeNode JuneWeek1 = new DefaultTreeNode("First Week", June);
        TreeNode JuneWeek2 = new DefaultTreeNode("Second Week", June);
        TreeNode JuneWeek3 = new DefaultTreeNode("Third Week", June);
        TreeNode JuneWeek4 = new DefaultTreeNode("Forth Week", June);
        
        expandAll(this.fakeRoot);
    }

   private void expandAll(TreeNode node){
       node.setExpanded(true);
       for(TreeNode n: node.getChildren()){
           expandAll(n);
       }
   }

    public TeachingPlan getSelected() {
        return selected;
    }

    public void setSelected(TeachingPlan selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TeachingPlanFacade getFacade() {
        return ejbFacade;
    }

    public TeachingPlan prepareCreate() {
        selected = new TeachingPlan();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TeachingPlanCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TeachingPlanUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TeachingPlanDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TeachingPlan> getItems() {
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

    public TeachingPlan getTeachingPlan(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TeachingPlan> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TeachingPlan> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TeachingPlan.class)
    public static class TeachingPlanControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TeachingPlanController controller = (TeachingPlanController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "teachingPlanController");
            return controller.getTeachingPlan(getKey(value));
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
            if (object instanceof TeachingPlan) {
                TeachingPlan o = (TeachingPlan) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TeachingPlan.class.getName()});
                return null;
            }
        }
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

}

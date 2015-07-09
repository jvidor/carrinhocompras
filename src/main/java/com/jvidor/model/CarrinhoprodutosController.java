package com.jvidor.model;

import com.jvidor.model.util.JsfUtil;
import com.jvidor.model.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "carrinhoprodutosController")
@SessionScoped
public class CarrinhoprodutosController implements Serializable {

    private Carrinhoprodutos current;
    private DataModel items = null;
    @EJB
    private com.jvidor.model.CarrinhoprodutosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CarrinhoprodutosController() {
    }

    public Carrinhoprodutos getSelected() {
        if (current == null) {
            current = new Carrinhoprodutos();
            current.setCarrinhoprodutosPK(new com.jvidor.model.CarrinhoprodutosPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private CarrinhoprodutosFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Carrinhoprodutos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Carrinhoprodutos();
        current.setCarrinhoprodutosPK(new com.jvidor.model.CarrinhoprodutosPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getCarrinhoprodutosPK().setIdcliente(current.getCarrinho().getCarrinhoPK().getIdcliente());
            current.getCarrinhoprodutosPK().setIdcarrinho(current.getCarrinho().getCarrinhoPK().getIdcarrinho());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarrinhoprodutosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Carrinhoprodutos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getCarrinhoprodutosPK().setIdcliente(current.getCarrinho().getCarrinhoPK().getIdcliente());
            current.getCarrinhoprodutosPK().setIdcarrinho(current.getCarrinho().getCarrinhoPK().getIdcarrinho());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarrinhoprodutosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Carrinhoprodutos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarrinhoprodutosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Carrinhoprodutos.class)
    public static class CarrinhoprodutosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarrinhoprodutosController controller = (CarrinhoprodutosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carrinhoprodutosController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.jvidor.model.CarrinhoprodutosPK getKey(String value) {
            com.jvidor.model.CarrinhoprodutosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.jvidor.model.CarrinhoprodutosPK();
            key.setIdcarrinho(Integer.parseInt(values[0]));
            key.setIdproduto(Integer.parseInt(values[1]));
            key.setIdcliente(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(com.jvidor.model.CarrinhoprodutosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdcarrinho());
            sb.append(SEPARATOR);
            sb.append(value.getIdproduto());
            sb.append(SEPARATOR);
            sb.append(value.getIdcliente());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Carrinhoprodutos) {
                Carrinhoprodutos o = (Carrinhoprodutos) object;
                return getStringKey(o.getCarrinhoprodutosPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Carrinhoprodutos.class.getName());
            }
        }

    }

}

/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Categoria;
import com.spontecorp.futboldata.jpacontroller.extensions.CategoriaJpaExt;
import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@ManagedBean(name = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Categoria categoria;
    private DataModel items = null;
    private final CategoriaJpaExt controllerCategoria;
    private final transient EntityManagerFactory emf = Util.getEmf();    
    
    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);

    public CategoriaBean() {
        controllerCategoria = new CategoriaJpaExt(emf);
    }    

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Categoria getSelected(){
        if(categoria == null){
            categoria = new Categoria();
        }
        return categoria;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(controllerCategoria.findCategoriaEntities());
        }
        return items;
    }
    
    public String gotoCategoriasPage() {
        return "listCategorias";
    }
    
    public String prepareCreate(){
        return "createCategoria";
    }
    
    public String prepareEdit(){
        categoria = (Categoria) getItems().getRowData();
        return "editCategoria";
    }
    
    public String prepareList() {
        return "listCategorias";
    }
    
    public String returnAdminPage() {
        return "adminPage";
    }
    
    public String create(){
        try {
            if(controllerCategoria.findCategoria(categoria.getNombre()) != null){
                Util.addErrorMessage("Categoria ya existente, coloque otro");
                return null;
            } else {
                controllerCategoria.create(categoria);
                Util.addSuccessMessage("Categoría creada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al crear la categoría");
            return null;
        }
    }
    
    public String edit(){
        try {
            if (controllerCategoria.findCategoria(categoria.getNombre()) == null) {
                Util.addErrorMessage("Categoria no existente, hay un error");
                return null;
            } else {
                controllerCategoria.edit(categoria);
                Util.addSuccessMessage("Categoría editada con éxito");
                return prepareCreate();
            }
        } catch (Exception e) {
            Util.addErrorMessage(e, "Error al editar la categoría");
            return null;
        }
    }
}

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@ManagedBean(name = "categoriaBean")
@SessionScoped
public class CategoriaBean implements Serializable{
    
    private Categoria categoria;
    private DataModel items = null;
    private final CategoriaJpaExt controllerCategoria;
    
    
    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);

    public CategoriaBean() {
        controllerCategoria = new CategoriaJpaExt(Util.getEmf());
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
}

/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.utilities.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jgcastillo
 */
@ManagedBean(name = "ligaBean")
@SessionScoped
public class LigaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private final transient EntityManagerFactory emf = Util.getEmf();

    private static final Logger logger = LoggerFactory.getLogger(CategoriaBean.class);
    
    public String gotoLigaPage(){
        return "ligaPage";
    }
}

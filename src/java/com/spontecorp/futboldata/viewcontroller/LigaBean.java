/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jgcastillo
 */
@ManagedBean(name = "ligaBean")
@SessionScoped
public class LigaBean implements Serializable{
    
    public String gotoLigaPage(){
        return "ligaPage";
    }
}

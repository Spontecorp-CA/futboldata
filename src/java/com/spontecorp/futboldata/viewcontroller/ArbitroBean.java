/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.viewcontroller;

import com.spontecorp.futboldata.entity.Arbitro;
import com.spontecorp.futboldata.entity.Persona;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



/**
 *
 * @author jgcastillo
 */
@Named("arbitroBean")
@SessionScoped
public class ArbitroBean implements Serializable{

    private Arbitro current;
    private Persona persona;
    public ArbitroBean() {
    }
    
    
}

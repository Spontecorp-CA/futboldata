/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.test;

import com.spontecorp.futboldata.utilities.StatusPartido;

/**
 *
 * @author jgcastillo
 */
public class TestCodigo {

    public static void main(String[] args) {
        for(StatusPartido sts : StatusPartido.values()){
            System.out.println("Nombre: " + sts.getNombre() + "   valor: " + sts.getValue());
        }
    }

}

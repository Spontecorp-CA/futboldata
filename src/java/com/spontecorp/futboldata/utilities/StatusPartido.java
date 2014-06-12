/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.utilities;

/**
 * Lso diferentes status que tendrá un partido
 * @author jgcastillo
 */
public enum StatusPartido {
    
    NO_COMENZADO("No Comenzado",0),
    FINALIZADO("Finalizado", 1);
    
    private final int value;
    private final String nombre;
    
    StatusPartido(String nombre, int value){
        this.nombre = nombre;
        this.value = value;
    }

    public int getValue(){
        return value;
    }
    
    public String getNombre() {
        return nombre;
    }
}

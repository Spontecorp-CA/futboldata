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
    
    NO_COMENZADO("",0),
    PRIMER_TIEMPO("Primer Tiempo", 1),
    ENTRETIEMPO("Entretiempo", 2),
    SEGUNDO_TIEMPO("Segundo Tiempo", 3),
    TIEMPO_EXTRA("Tiempo Añadido", 4),
    PRORROGA_1("Prorroga 1ra Parte", 5),
    PRORROGA_2("Prorroga 2da Parte", 6),
    PENALES("Penales", 7),
    FINALIZADO("Finalizado", 8),
    INCOMPARECENCIA("Incoparecencia", 9),
    SUSPENDIDO_LLUVIA("Supendido por Lluvia", 10),
    SUSPENDIDO_SIN_SEGURIDAD("Supendido por Falta de Seguridad Policial", 11),
    SUSPENDIDO_DISTURBIOS("Supendido por Disturbios", 12),
    DECICIDO_CNJ_HONOR("Suspendido por Consejo de Honor", 13),
    COMISION_JUSTICIA("En Comisión de Justicia", 14);
    
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

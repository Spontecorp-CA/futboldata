/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.test;

/**
 *
 * @author jgcastillo
 */
public class TestString {
    public static void main(String[] args) {
        String string1 = "Volante de marca";
        String string2 = "Volante de Marca";
        
        if(string1.equals(string2)){
            System.out.println("Son iguales");
        } else {
            System.out.println("Son distintos");
        }
    }
}

/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */

package com.spontecorp.futboldata.reportes.template;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sponte03
 */
public class DynamicColumnTitle {
    private String titulo;
    private List<DynamicColumn> dynamicColumns;

    public DynamicColumnTitle(String titulo) {
        this.titulo = titulo;
        this.dynamicColumns = new ArrayList<DynamicColumn>();
    }

    public void add(DynamicColumn column){
        dynamicColumns.add(column);
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DynamicColumn> getDynamicColumns() {
        return dynamicColumns;
    }

    public void setDynamicColumns(List<DynamicColumn> dynamicColumns) {
        this.dynamicColumns = dynamicColumns;
    }
    
    
    
}

/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2014 Ricardo Mariaca http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.spontecorp.futboldata.reportes.template;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DynamicReport {

    private String title;
    private List<DynamicColumn> columns;
    private List<String> groups;
    private List<String> subtotals;
    private List<String> subTitles;
    private List<DynamicColumnTitle> columnTitles;
    private boolean showPageNumber;
    private boolean showRowNumber;

    public DynamicReport() {
        columns = new ArrayList<DynamicColumn>();
        groups = new ArrayList<String>();
        subtotals = new ArrayList<String>();
        columnTitles = new ArrayList<DynamicColumnTitle>();
    }

    public List<DynamicColumnTitle> getColumnTitles() {
        return columnTitles;
    }

    public void setColumnTitles(List<DynamicColumnTitle> columnTitles) {
        this.columnTitles = columnTitles;
    }

    public boolean isShowRowNumber() {
        return showRowNumber;
    }

    public void setShowRowNumber(boolean showRowNumber) {
        this.showRowNumber = showRowNumber;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DynamicColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DynamicColumn> columns) {
        this.columns = columns;
    }

    public void addColumn(DynamicColumn column) {
        this.columns.add(column);
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public void addGroup(String column) {
        this.groups.add(column);
    }

    public void addColumnsTitulo(DynamicColumnTitle columnTitle){
        this.columnTitles.add(columnTitle);
    }
    public List<String> getSubtotals() {
        return subtotals;
    }

    public void setSubtotals(List<String> subtotals) {
        this.subtotals = subtotals;
    }

    public void addSubtotal(String column) {
        this.subtotals.add(column);
    }

    public boolean isShowPageNumber() {
        return showPageNumber;
    }

    public void setShowPageNumber(boolean showPageNumber) {
        this.showPageNumber = showPageNumber;
    }

    public List<String> getSubTitles() {
        return subTitles;
    }
    

    public void setSubTitles(List<String> subTitles) {
        this.subTitles = subTitles;
    }

    
}

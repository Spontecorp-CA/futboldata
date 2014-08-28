/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.grid;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author sponte03
 */
public class ClasificacionesReport {

    public static JasperReportBuilder crearReporte(Collection<?> collection, String titulo, List<String> subTitulos) {
        DynamicReport dr = crearReporteDinamico();
        dr.setSubTitles(subTitulos);

        JasperReportBuilder builder;
        builder = null;
        try {
            builder = new ReporteDinamico().generarReporteDinamico(dr, titulo, collection);
        } catch (DRException ex) {
            Logger.getLogger(ClasificacionesReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        StyleBuilder textStyle = stl.style(Templates.columnStyle)
                .setBorder(stl.pen1Point());
        builder.setColumnStyle(textStyle);
        ColumnTitleGroupBuilder titleGroup2 = grid.titleGroup("Equipo Local");
        return builder;
    }

    public static DynamicReport crearReporteDinamico() {
        DynamicReport report = new DynamicReport();
        List<DynamicColumn> listColumns = new ArrayList<DynamicColumn>();
        List<DynamicColumn> totalColumns = new ArrayList<DynamicColumn>();

        report.setTitle("Clasificaciones");

        listColumns.add(new DynamicColumn("Equipo", "equipo", "string"));
        listColumns.add(new DynamicColumn("JJ", "jugados", "integer"));
        listColumns.add(new DynamicColumn("JG", "ganados", "integer"));
        listColumns.add(new DynamicColumn("JE", "empatados", "integer"));
        listColumns.add(new DynamicColumn("JP", "perdidos", "integer"));
        listColumns.add(new DynamicColumn("GF", "golFavor", "integer"));
        listColumns.add(new DynamicColumn("GC", "golContra", "integer"));
        listColumns.add(new DynamicColumn("Dif", "golDiferencia", "integer"));
        listColumns.add(new DynamicColumn("PTS", "puntos", "integer"));

        DynamicColumnTitle columnTitle = new DynamicColumnTitle("Total");
        columnTitle.setDynamicColumns(listColumns);
        report.addColumnsTitulo(columnTitle);    
        totalColumns.addAll(listColumns);
        listColumns = new ArrayList<DynamicColumn>();

        listColumns.add(new DynamicColumn("JJ", "jugadosLocal", "integer"));
        listColumns.add(new DynamicColumn("JG", "ganadosLocal", "integer"));
        listColumns.add(new DynamicColumn("JE", "empatadosLocal", "integer"));
        listColumns.add(new DynamicColumn("JP", "perdidosLocal", "integer"));
        listColumns.add(new DynamicColumn("GF", "golFavorLocal", "integer"));
        listColumns.add(new DynamicColumn("GC", "golContraLocal", "integer"));
        listColumns.add(new DynamicColumn("Dif", "golDiferenciaLocal", "integer"));
        listColumns.add(new DynamicColumn("PTS", "puntosLocal", "integer"));

        DynamicColumnTitle columnTitle2 = new DynamicColumnTitle("Local");
        columnTitle.setDynamicColumns(listColumns);
        report.addColumnsTitulo(columnTitle2);
        totalColumns.addAll(listColumns);
        listColumns = new ArrayList<DynamicColumn>();

        listColumns.add(new DynamicColumn("JJ", "jugadosVisitante", "integer"));
        listColumns.add(new DynamicColumn("JG", "ganadosVisitante", "integer"));
        listColumns.add(new DynamicColumn("JE", "empatadosVisitante", "integer"));
        listColumns.add(new DynamicColumn("JP", "perdidosVisitante", "integer"));
        listColumns.add(new DynamicColumn("GF", "golFavorVisitante", "integer"));
        listColumns.add(new DynamicColumn("GC", "golContraVisitante", "integer"));
        listColumns.add(new DynamicColumn("Dif", "golDiferenciaVisitante", "integer"));
        listColumns.add(new DynamicColumn("PTS", "puntosVisitante", "integer"));

        DynamicColumnTitle columnTitle3 = new DynamicColumnTitle("Local");
        columnTitle.setDynamicColumns(listColumns);
        report.addColumnsTitulo(columnTitle3);
        totalColumns.addAll(listColumns);

        report.setColumns(totalColumns);
        report.addGroup("equipo");

//        DynamicColumn column = new DynamicColumn("Order date", "orderdate", "date");
//        column.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        report.addColumn(column);
//        report.addColumn(new DynamicColumn("Quantity", "quantity", "integer"));
//        column = new DynamicColumn("Unit price", "unitprice", "bigDecimal");
//        column.setPattern("#,###.0");
//        report.addColumn(column);
//        report.addGroup("state");
//        report.addSubtotal("quantity");
//        report.addSubtotal("unitprice");
        report.setShowPageNumber(
                true);
        return report;
    }

}

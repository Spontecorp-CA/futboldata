/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author sponte03
 */
public class ClasificacionesReport {

    public static JasperReportBuilder crearReporte(Collection<?> collection, String titulo,List<String> subTitulos)  {
        DynamicReport dr = crearReporteDinamico();
        dr.setSubTitles(subTitulos);

        JasperReportBuilder builder;
        builder = null;
        try {
            builder = new ReporteDinamico().generarReporteDinamico(dr, titulo, collection);
        } catch (DRException ex) {
            Logger.getLogger(ClasificacionesReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder;
    }

    public static DynamicReport crearReporteDinamico() {
        DynamicReport report = new DynamicReport();

        report.setTitle("Clasificaciones");
        report.addColumn(new DynamicColumn("Equipo", "equipo", "string"));
        report.addColumn(new DynamicColumn("JJ", "jugados", "integer"));
        report.addColumn(new DynamicColumn("JG", "ganados", "integer"));
        report.addColumn(new DynamicColumn("JE", "empatados", "integer"));
        report.addColumn(new DynamicColumn("JP", "perdidos", "integer"));
        report.addColumn(new DynamicColumn("GF", "golFavor", "integer"));
        report.addColumn(new DynamicColumn("GC", "golContra", "integer"));
        report.addColumn(new DynamicColumn("Dif", "golDiferencia", "integer"));
        report.addColumn(new DynamicColumn("PTS", "puntos", "integer"));
        
        report.addColumn(new DynamicColumn("JJ", "jugadosLocal", "integer"));
        report.addColumn(new DynamicColumn("JG", "ganadosLocal", "integer"));
        report.addColumn(new DynamicColumn("JE", "empatadosLocal", "integer"));
        report.addColumn(new DynamicColumn("JP", "perdidosLocal", "integer"));
        report.addColumn(new DynamicColumn("GF", "golFavorLocal", "integer"));
        report.addColumn(new DynamicColumn("GC", "golContraLocal", "integer"));
        report.addColumn(new DynamicColumn("Dif", "golDiferenciaLocal", "integer"));
        report.addColumn(new DynamicColumn("PTS", "puntosLocal", "integer"));

        report.addColumn(new DynamicColumn("JJ", "jugadosVisitante", "integer"));
        report.addColumn(new DynamicColumn("JG", "ganadosVisitante", "integer"));
        report.addColumn(new DynamicColumn("JE", "empatadosVisitante", "integer"));
        report.addColumn(new DynamicColumn("JP", "perdidosVisitante", "integer"));
        report.addColumn(new DynamicColumn("GF", "golFavorVisitante", "integer"));
        report.addColumn(new DynamicColumn("GC", "golContraVisitante", "integer"));
        report.addColumn(new DynamicColumn("Dif", "golDiferenciaVisitante", "integer"));
        report.addColumn(new DynamicColumn("PTS", "puntosVisitante", "integer"));
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
    report.setShowPageNumber (
            
    true);
        return report ;
}

}

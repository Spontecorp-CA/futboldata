/*
 * Derechos Reservados Spontecorp, C.A. 2014
 * 
 */
package com.spontecorp.futboldata.reportes.template;

import com.spontecorp.futboldata.entity.Partido;
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
public class PartidosReport {

    public static JasperReportBuilder crearReporte(Collection<?> collection, String titulo, List<String> subTitulos) {
        DynamicReport dr = crearReporteDinamico();
        dr.setSubTitles(subTitulos);

        JasperReportBuilder builder;
        builder = null;
        try {
            builder = new ReporteDinamico().generarReporteDinamico(dr, titulo, collection);
        } catch (DRException ex) {
            Logger.getLogger(PartidosReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder;
    }

    public static DynamicReport crearReporteDinamico() {
        DynamicReport report = new DynamicReport();

        report.setTitle("Partidos");
        report.addColumn(new DynamicColumn("Equipo Local", "equipoLocalId.nombre", "string"));
        report.addColumn(new DynamicColumn("GL", "golesEquipoLocal", "integer"));
        report.addColumn(new DynamicColumn("GV", "golesEquipoVisitante", "integer"));
        report.addColumn(new DynamicColumn("Equipo Visitante", "equipoVisitanteId.nombre", "string"));
        report.addColumn(new DynamicColumn("Categoria", "categoriaId.nombre", "string"));
        report.addColumn(new DynamicColumn("Ciudad", "canchaId.direccionId.ciudadId.ciudad", "string"));
        report.addColumn(new DynamicColumn("Cancha", "canchaId.nombre", "string"));
        DynamicColumn column = new DynamicColumn("Fecha", "fecha", "date");
        column.setPattern("EEE dd/MM/yyyy");
        report.addColumn(column);
        column = new DynamicColumn("Hora", "horaInicio", "date");
        column.setPattern("hh:mm a");
        report.addColumn(column);
        report.addColumn(new DynamicColumn("Status", "statusPartidoId.nombre", "string"));

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
